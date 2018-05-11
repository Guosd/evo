$(function() {
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
		sortname: 'id',
		sortorder: 'asc'
	});

	query();

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
		if (ids.length == 1) {
			location.href = '/sys-ui/micro/form?id=' + ids[0];
		} else {
			alert('请选择一条记录');
		}
	});
	$('button#delete').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 1) {
			if (confirm('此操作会级联删除功能、角色功能关系、功能相关菜单。是否继续？')) {
				$.ajax({
					url: '/sys/micro/' + ids[0],
					method: 'delete',
					success: function(result, textStatus, jqXHR) {
						alert('删除成功');
						query();
					}
				});
			}
		} else {
			alert('请选择一条记录');
		}
	});

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
