var tmpRoleRowDatas = [];

$(function() {
	var id = $.getUrlParam('id');

	if (id) {
		$('#password').attr('disabled', 'disabled');
		$('#confirmPassword').attr('disabled', 'disabled');

		$.ajax({
			url: '/sys/user/id/' + id + '/role',
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				$('#id').val(result.data.id);
				$('#username').val(result.data.username);
				$('#name').val(result.data.name);
				$('#code').val(result.data.code);
				$('#email').val(result.data.email);
				$('#mobileNumber').val(result.data.mobileNumber);
				var freeze = result.data.freeze;

				$.ajax({
					url: '/datadict/code/COMM_YN',
					method: 'get',
					success: function(result, textStatus, jqXHR) {
						for (var i = 0; i < result.data.length; i++) {
							$("#freeze").append('<option value=\"' + result.data[i].key + '\"' + (freeze == result.data[i].key ? ' selected=\"selected\"' : '') + '>' + result.data[i].value + '</option>');
						}
					}
				});

				for (var i = 0; i < result.data.roleDtoList.length; i++) {
					tmpRoleRowDatas.push(result.data.roleDtoList[i]);
				}

				setRoleIdAndText();
			}
		});
	} else {
		$.ajax({
			url: '/datadict/code/COMM_YN',
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				for (var i = 0; i < result.data.length; i++) {
					$("#freeze").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
				}
			}
		});
	}

	$('#roleTexts').attr('readonly', 'readonly').click(function() {
		$('#dialog').dialog({
			width: 850,
			height: 530,
			buttons: [{
				text: "确定",
				click: function() {
					setRoleIdAndText();

					$(this).dialog('close');
				}
			}, {
				text: "取消",
				click: function() {
					$(this).dialog('close');
				}
			}]
		});

		$('button#roleQuery').unbind("click");
		$('button#roleReset').unbind("click");

		$('button#roleQuery').click(function() {
			queryRole();
		});
		$('button#roleReset').click(function() {
			resetRole();
		});

		$('#grid-table-role').jqGrid({
			url: '/sys/role/page',
			datatype: 'local',
			rowNum: 10,
			pager: '#grid-pager-role',
			colModel: [{
				name: 'id',
				hidden: true
			}, {
				label: '名称',
				name: 'name'
			}, {
				label: '编码',
				name: 'code'
			}],
			sortname: 'id',
			sortorder: 'asc',
			onSelectRow: function(id, status) {
				if (status) {
					var exist = false;
					for (var i = 0; i < tmpRoleRowDatas.length; i++) {
						if (tmpRoleRowDatas[i].id == id) {
							exist = true;
							break;
						}
					}

					if (!exist) {
						tmpRoleRowDatas.push(jqGridSelectRowData(id, '#grid-table-role'))
					}
				} else {
					var delIndex = -1;
					for (var i = 0; i < tmpRoleRowDatas.length; i++) {
						if (tmpRoleRowDatas[i].id == id) {
							delIndex = i;
							break;
						}
					}

					if (delIndex > -1) {
						tmpRoleRowDatas.splice(delIndex, 1);
					}
				}
			},
			loadComplete: function(xhr) {
				if (tmpRoleRowDatas.length > 0) {
					var dataIds = $('#grid-table-role').jqGrid('getDataIDs');
					for (var i = 0; i < dataIds.length; i++) {
						for (var j = 0; j < tmpRoleRowDatas.length; j++) {
							if (dataIds[i] == tmpRoleRowDatas[j].id) {
								$('#grid-table-role').jqGrid('setSelection', dataIds[i]);
								break;
							}
						}
					}
				}
			}
		});

		queryRole();
	});

	$('button#submit').click(function() {
		$.ajax({
			url: id ? '/sys/user/with-role' : '/sys/user/all',
			method: id ? 'put' : 'post',
			data: JSON.stringify({
				id: $('#id').val(),
				username: $('#username').val(),
				password: $('#password').val(),
				name: $('#name').val(),
				code: $('#code').val(),
				email: $('#email').val(),
				mobileNumber: $('#mobileNumber').val(),
				freeze: $('#freeze').val(),
				roleIds: $('#roleIds').val() ? $('#roleIds').val().split(',') : undefined
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

	resetHeight();
});

function setRoleIdAndText() {
	var roleIds = [];
	var roleTexts = [];
	for (var i = 0; i < tmpRoleRowDatas.length; i++) {
		roleIds.push(tmpRoleRowDatas[i].id);
		roleTexts.push(tmpRoleRowDatas[i].name + ', ' + tmpRoleRowDatas[i].code);
	}
	$('#roleIds').val(roleIds.join(','));
	$('#roleTexts').val(roleTexts.join('\r\n'));
}

function back() {
	location.href = '/sys-ui/user/list';
}

function queryRole() {
	jqGridQuery({
		name: $('#roleName').val(),
		code: $('#roleCode').val(),
	}, '#grid-table-role');
}

function resetRole() {
	$('#roleName').val('');
	$('#roleCode').val('');
	queryRole();
}
