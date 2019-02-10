$(function() {
	$('button#query').click(function() {
		query();
	});
	$('button#reset').click(function() {
		reset();
	});
	$('button#create').click(function() {
		location.href = '/sys-ui/role/form';
	});
	$('button#update').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 1) {
			location.href = '/sys-ui/role/form?id=' + ids[0];
		} else {
			alert('请选择一条记录');
		}
	});
	$('button#delete').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 1) {
			if (confirm('此操作会级联删除角色功能关系、用户角色关系。是否继续？')) {
				$.ajax({
					url: '/sys/role/' + ids[0],
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

	$(gridSelector).jqGrid({
		url: '/sys/role/page',
		datatype: 'local',
		colModel: [{
			label: '名称',
			name: 'name'
		}, {
			label: '编码',
			name: 'code'
		}],
		sortname: 'id',
		sortorder: 'asc'
	});

	query();

	resetHeight();
});

function query() {
	jqGridQuery({
		name: $('#name').val(),
		code: $('#code').val()
	});
}

function reset() {
	$('#name').val('');
	$('#code').val('');
	query();
}
