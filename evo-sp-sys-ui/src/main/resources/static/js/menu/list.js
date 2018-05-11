$(function() {
	$('button#query').click(function() {
		query();
	});
	$('button#reset').click(function() {
		reset();
	});
	$('button#create').click(function() {
		location.href = '/sys-ui/menu/form';
	});
	$('button#update').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 0) {
			location.href = '/sys-ui/menu/form?id=' + ids[0];
		} else {
			alert('请选择一条记录');
		}
	});
	$('button#delete').click(function() {
		alert('暂未实现');
	});

	$(gridSelector).jqGrid({
		url: '/sys/menu/page',
		datatype: 'local',
		colModel: [{
			label: '上级菜单',
			name: 'parentId'
		}, {
			label: '名称',
			name: 'name'
		}, {
			label: '编码',
			name: 'code'
		}, {
			label: '功能',
			name: 'funcId'
		}],
		sortname: 'sort',
		sortorder: 'asc'
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