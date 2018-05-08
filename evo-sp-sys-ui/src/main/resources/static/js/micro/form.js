$(function() {
	var id = $.getUrlParam('id');

	$('button#submit').click(function() {
		$.ajax({
			url: '/sys/micro',
			method: id ? 'put' : 'post',
			data: JSON.stringify({
				id: $('#id').val(),
				name: $('#name').val(),
				code: $('#code').val(),
				prefix: $('#prefix').val(),
				sort: $('#sort').val()
			}),
			success: function(result, textStatus, jqXHR) {
				alert('提交成功');
				back();
			}
		});
	});
	$('button#back').click(function() {
		back();
	});

	if (id) {
		$.ajax({
			url: '/sys/micro/id/' + id,
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				$('#id').val(result.data.id);
				$('#name').val(result.data.name);
				$('#code').val(result.data.code);
				$('#prefix').val(result.data.prefix);
				$('#sort').val(result.data.sort);
			}
		});
	}

	resetHeight();
});

function back() {
	location.href = '/sys-ui/micro/list';
}
