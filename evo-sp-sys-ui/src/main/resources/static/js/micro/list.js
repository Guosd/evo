$(function() {
	$('button#query').click(function() {
		query();
	});
	$('button#reset').click(function() {
		reset();
	});
	$('button#create').click(function() {
		location.href = '/sys-ui/micro/form';
	});
	$('button#update').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 0) {
			alert('请选择一条记录');
		} else {
			location.href = '/sys-ui/micro/form?id=' + ids[0];
		}
	});
	$('button#delete').click(function() {
		alert('暂未实现');
	});

	$(gridSelector).jqGrid({
		url: '/sys/micro/page',
		datatype: 'local',
		colModel: [{
			label: '名称',
			name: 'name'
		}, {
			label: '编码',
			name: 'code'
		}, {
			label: '前缀',
			name: 'prefix'
		}],
		sortname: 'sort',
		sortorder: "asc"
	});

	query();

	resetHeight();
});

function query() {
	jqGridQuery({
		name: $('#name').val(),
		code: $('#code').val(),
		prefix: $('#prefix').val()
	});
}

function reset() {
	$('#name').val('');
	$('#code').val('');
	$('#prefix').val('');
	query();
}
