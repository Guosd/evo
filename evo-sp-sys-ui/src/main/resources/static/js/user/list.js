$(function() {
	$('button#query').click(function() {
		query();
	});
	$('button#reset').click(function() {
		reset();
	});
	$('button#create').click(function() {
		location.href = '/sys-ui/user/form';
	});
	$('button#update').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 0) {
			location.href = '/sys-ui/user/form?id=' + ids[0];
		} else {
			alert('请选择一条记录');
		}
	});
	$('button#delete').click(function() {
		alert('暂未实现');
	});

	$(gridSelector).jqGrid({
		url: '/sys/user/page',
		datatype: 'local',
		colModel: [{
			label: '用户名称',
			name: 'username'
		}, {
			label: '姓名',
			name: 'name'
		}, {
			label: '编码',
			name: 'code'
		}, {
			label: '邮箱',
			name: 'email'
		}, {
			label: '手机号码',
			name: 'mobileNumber'
		}, {
			label: '冻结',
			name: 'freeze'
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
