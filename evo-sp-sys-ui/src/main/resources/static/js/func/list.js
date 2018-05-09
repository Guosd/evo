$(function() {
	$(gridSelector).jqGrid({
		url: '/sys/func/page',
		datatype: 'local',
		colModel: [{
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
		sortorder: 'asc'
	});

	query();

	$('#microText').attr('readonly', 'readonly').click(function() {
		$('#dialog').dialog({
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
			sortorder: 'asc',
			loadComplete: function(xhr) {
				$('#grid-table-micro').closest('.ui-jqgrid-bdiv').css({'overflow-x': 'scroll'});
			}
		});

		queryMicro();
	});

	$('button#query').click(function() {
		query();
	});
	$('button#reset').click(function() {
		reset();
	});
	$('button#create').click(function() {
		location.href = '/sys-ui/func/form';
	});
	$('button#update').click(function() {
		var ids = jqGridSelectIds();
		if (ids.length == 1) {
			location.href = '/sys-ui/func/form?id=' + ids[0];
		} else {
			alert('请选择一条记录');
		}
	});
	$('button#delete').click(function() {
		alert('暂未实现');
	});

	resetHeight();
});

function query() {
	jqGridQuery({
		microId: $('#microId').val(),
		name: $('#name').val(),
		code: $('#code').val(),
		prefix: $('#uri').val()
	});
}

function reset() {
	$('#microId').val('');
	$('#microText').val('');
	$('#name').val('');
	$('#code').val('');
	$('#uri').val('');
	query();
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
