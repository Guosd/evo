$(function() {
	var id = $.getUrlParam('id');

	if (id) {
		$.ajax({
			url: '/sys/menu/id/' + id,
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				$('#id').val(result.data.id);
				$('#parentId').val(result.data.parentId);
				$('#parentText').val(result.data.parentName);
				$('#funcId').val(result.data.funcId);
				$('#funcText').val(result.data.funcName);
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
			url: '/sys/menu/page',
			datatype: 'local',
			rowNum: 10,
			pager: '#grid-pager-menu',
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
						$('#funcText').val(rowData.microId + ', ' + rowData.name + ', ' + rowData.code + ', ' + rowData.uri + ', ' + rowData.method);
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
			loadComplete: function(xhr) {
				var funcId = $('#funcId').val();
				if (funcId) {
					$('#grid-table-func').jqGrid('setSelection', funcId);
				}
			}
		});

		queryFunc();
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
