var tmpFuncRowDatas = [];

$(function() {
	var id = $.getUrlParam('id');

	if (id) {
		$.ajax({
			url: '/sys/role/id/' + id + '/func',
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				$('#id').val(result.data.id);
				$('#name').val(result.data.name);
				$('#code').val(result.data.code);

				for (var i = 0; i < result.data.funcDtoList.length; i++) {
					tmpFuncRowDatas.push(result.data.funcDtoList[i]);
				}

				setFuncIdAndText();
			}
		});
	}

	$('#funcTexts').attr('readonly', 'readonly').click(function() {
		$('#dialog').dialog({
			width: 850,
			height: 530,
			buttons: [{
				text: "确定",
				click: function() {
					setFuncIdAndText();

					$(this).dialog('close');
				}
			}, {
				text: "取消",
				click: function() {
					$(this).dialog('close');
				}
			}]
		});

		$('button#funcQuery').unbind("click");
		$('button#funcReset').unbind("click");

		$('button#funcQuery').click(function() {
			queryFunc();
		});
		$('button#funcReset').click(function() {
			resetFunc();
		});

		$('#grid-table-func').jqGrid({
			url: '/sys/func/page',
			datatype: 'local',
			rowNum: 10,
			pager: '#grid-pager-func',
			colModel: [{
				name: 'id',
				hidden: true
			}, {
				label: '服务',
				name: 'microId'
			}, {
				label: '名称',
				name: 'name'
			}, {
				label: '编码',
				name: 'code'
			}, {
				label: 'URI',
				name: 'uri'
			}, {
				label: '方法',
				name: 'method'
			}],
			sortname: 'id',
			sortorder: 'asc',
			onSelectRow: function(id, status) {
				if (status) {
					var exist = false;
					for (var i = 0; i < tmpFuncRowDatas.length; i++) {
						if (tmpFuncRowDatas[i].id == id) {
							exist = true;
							break;
						}
					}

					if (!exist) {
						tmpFuncRowDatas.push(jqGridSelectRowData(id, '#grid-table-func'))
					}
				} else {
					var delIndex = -1;
					for (var i = 0; i < tmpFuncRowDatas.length; i++) {
						if (tmpFuncRowDatas[i].id == id) {
							delIndex = i;
							break;
						}
					}

					if (delIndex > -1) {
						tmpFuncRowDatas.splice(delIndex, 1);
					}
				}
			},
			loadComplete: function(xhr) {
				if (tmpFuncRowDatas.length > 0) {
					var dataIds = $('#grid-table-func').jqGrid('getDataIDs');
					for (var i = 0; i < dataIds.length; i++) {
						for (var j = 0; j < tmpFuncRowDatas.length; j++) {
							if (dataIds[i] == tmpFuncRowDatas[j].id) {
								$('#grid-table-func').jqGrid('setSelection', dataIds[i]);
								break;
							}
						}
					}
				}
			}
		});

		queryFunc();
	});

	$('button#submit').click(function() {
		$.ajax({
			url: '/sys/role',
			method: id ? 'put' : 'post',
			data: JSON.stringify({
				id: $('#id').val(),
				name: $('#name').val(),
				code: $('#code').val(),
				funcIds: $('#funcIds').val() ? $('#funcIds').val().split(',') : undefined
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

function setFuncIdAndText() {
	var funcIds = [];
	var funcTexts = [];
	for (var i = 0; i < tmpFuncRowDatas.length; i++) {
		funcIds.push(tmpFuncRowDatas[i].id);
		funcTexts.push(tmpFuncRowDatas[i].microId + ', ' + tmpFuncRowDatas[i].name + ', ' + tmpFuncRowDatas[i].code + ', ' + tmpFuncRowDatas[i].uri + ', ' + tmpFuncRowDatas[i].method);
	}
	$('#funcIds').val(funcIds.join(','));
	$('#funcTexts').val(funcTexts.join('\r\n'));
}

function back() {
	location.href = '/sys-ui/role/list';
}

function queryFunc() {
	jqGridQuery({
		microId: $('#microId').val(),
		name: $('#funcName').val(),
		code: $('#funcCode').val(),
		uri: $('#funcUri').val(),
		method: $('#funcMethod').val()
	}, '#grid-table-func');
}

function resetFunc() {
	$('#microId').val('');
	$('#funcName').val('');
	$('#funcCode').val('');
	$('#funcUri').val('');
	$('#funcMethod').val('');
	queryFunc();
}
