$(function() {
	var id = $.getUrlParam('id');

	if (id) {
		$.ajax({
			url: '/sys/menu/id/' + id + '/parent',
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				$('#id').val(result.data.id);
				$('#parentId').val(result.data.parentId);
				$('#parentText').val(result.data.parentName);
				$('#funcId').val(result.data.funcId);
				$('#funcText').val((result.data.funcName || '') + ', ' + (result.data.funcCode || '') + ', ' + result.data.funcUri + ', ' + result.data.funcMethod);
				$('#name').val(result.data.name);
				$('#code').val(result.data.code);
				$('#sort').val(result.data.sort);
			}
		});
	}

	$('#parentText').attr('readonly', 'readonly').click(function() {
		$('#dialogMenu').dialog({
			width: 850,
			height: 530,
			buttons: [{
				text: "确定",
				click: function() {
					var ids = jqGridSelectIds('#grid-table-menu');
					if (ids.length == 1) {
						$('#parentId').val(ids[0]);
						$('#parentText').val(jqGridSelectRowData(ids[0], '#grid-table-menu').name);
					} else {
						$('#parentId').val('');
						$('#parentText').val('');
					}

					$(this).dialog('close');
				}
			}, {
				text: "取消",
				click: function() {
					$(this).dialog('close');
				}
			}]
		});

		$('button#menuQuery').unbind("click");
		$('button#menuReset').unbind("click");

		$('button#menuQuery').click(function() {
			queryMenu();
		});
		$('button#menuReset').click(function() {
			resetMenu();
		});

		$('#grid-table-menu').jqGrid({
			url: '/sys/menu/page/parent',
			datatype: 'local',
			rowNum: 10,
			pager: '#grid-pager-menu',
			colModel: [{
				name: 'parentId',
				hidden: true
			}, {
				name: 'funcId',
				hidden: true
			}, {
				label: '上级菜单',
				name: 'parentName'
			}, {
				label: '名称',
				name: 'name'
			}, {
				label: '编码',
				name: 'code'
			}, {
				label: '功能',
				formatter: function(cellvalue, options, rowdata) {
					return (rowdata.funcName || '') + ', ' + (rowdata.funcCode || '') + ', ' + rowdata.funcUri + ', ' + rowdata.funcMethod;
				}
			}],
			sortname: 'id',
			sortorder: 'asc',
			loadComplete: function(xhr) {
				var parentId = $('#parentId').val();
				if (parentId) {
					$('#grid-table-menu').jqGrid('setSelection', parentId);
				}
			}
		});

		queryMenu();
	});

	$('#funcText').attr('readonly', 'readonly').click(function() {
		$('#dialogFunc').dialog({
			width: 850,
			height: 530,
			buttons: [{
				text: "确定",
				click: function() {
					var ids = jqGridSelectIds('#grid-table-func');
					if (ids.length == 1) {
						$('#funcId').val(ids[0]);

						var rowData = jqGridSelectRowData(ids[0], '#grid-table-func');
						$('#funcText').val(rowData.microName + ', ' + rowData.name + ', ' + rowData.code + ', ' + rowData.uri + ', ' + rowData.method);
					} else {
						$('#funcId').val('');
						$('#funcText').val('');
					}

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
			url: '/sys/func/page/micro',
			datatype: 'local',
			rowNum: 10,
			pager: '#grid-pager-func',
			colModel: [{
				name: 'microId',
				hidden: true
			}, {
				label: '服务',
				name: 'microName'
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
			loadComplete: function(xhr) {
				var funcId = $('#funcId').val();
				if (funcId) {
					$('#grid-table-func').jqGrid('setSelection', funcId);
				}
			}
		});

		queryFunc();
	});

	$('#microText').attr('readonly', 'readonly').click(function() {
		$('#dialogMicro').dialog({
			width: 850,
			height: 510,
			buttons: [{
				text: "确定",
				click: function() {
					var ids = jqGridSelectIds('#grid-table-micro');
					if (ids.length == 1) {
						$('#microId').val(ids[0]);
						$('#microText').val(jqGridSelectRowData(ids[0], '#grid-table-micro').name);
					} else {
						$('#microId').val('');
						$('#microText').val('');
					}

					$(this).dialog('close');
				}
			}, {
				text: "取消",
				click: function() {
					$(this).dialog('close');
				}
			}]
		});

		$('button#microQuery').unbind("click");
		$('button#microReset').unbind("click");

		$('button#microQuery').click(function() {
			queryMicro();
		});
		$('button#microReset').click(function() {
			resetMicro();
		});

		$('#grid-table-micro').jqGrid({
			url: '/sys/micro/page',
			datatype: 'local',
			rowNum: 10,
			pager: '#grid-pager-micro',
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

		queryMicro();
	});

	$('button#submit').click(function() {
		$.ajax({
			url: '/sys/menu',
			method: id ? 'put' : 'post',
			data: JSON.stringify({
				id: $('#id').val(),
				parentId: $('#parentId').val(),
				funcId: $('#funcId').val(),
				name: $('#name').val(),
				code: $('#code').val(),
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

	resetHeight();
});

function back() {
	location.href = '/sys-ui/menu/list';
}

function queryMenu() {
	jqGridQuery({
		parentId: $('#menuParentId').val(),
		funcId: $('#menuFuncId').val(),
		name: $('#menuName').val(),
		code: $('#menuCode').val()
	}, '#grid-table-menu');
}

function resetMenu() {
	$('#menuParentId').val('');
	$('#menuParentText').val('');
	$('#menuFuncId').val('');
	$('#menuFuncText').val('');
	$('#menuName').val('');
	$('#menuCode').val('');
	queryMenu();
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

function queryMicro() {
	jqGridQuery({
		name: $('#microName').val(),
		code: $('#microCode').val(),
		prefix: $('#microPrefix').val()
	}, '#grid-table-micro');
}

function resetMicro() {
	$('#microName').val('');
	$('#microCode').val('');
	$('#microPrefix').val('');
	queryMicro();
}
