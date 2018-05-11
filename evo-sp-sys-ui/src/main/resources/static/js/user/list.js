$(function() {
	$.ajax({
		url: '/datadict/code/COMM_YN',
		method: 'get',
		success: function(result, textStatus, jqXHR) {
			for (var i = 0; i < result.data.length; i++) {
				$("#freeze").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
			}
		}
	});

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
		if (ids.length == 1) {
			location.href = '/sys-ui/user/form?id=' + ids[0];
		} else {
			alert('请选择一条记录');
		}
	});
	$('button#delete').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 1) {
			if (confirm('此操作会级联删除用户角色关系。是否继续？')) {
				$.ajax({
					url: '/sys/user/' + ids[0],
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
		username: $('#username').val(),
		name: $('#name').val(),
		code: $('#code').val(),
		email: $('#email').val(),
		mobileNumber: $('#mobileNumber').val(),
		freeze: $('#freeze').val()
	});
}

function reset() {
	$('#username').val('');
	$('#name').val('');
	$('#code').val('');
	$('#email').val('');
	$('#mobileNumber').val('');
	$('#freeze').val('');
	query();
}
