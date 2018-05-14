$(function() {
	var id = $.getUrlParam('id');

	if (id) {
		$.ajax({
			url: '/sys/func/id/' + id + '/micro',
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				$('#id').val(result.data.id);
				$('#microId').val(result.data.microId);
				$('#microText').val(result.data.microName);
				$('#name').val(result.data.name);
				$('#code').val(result.data.code);
				$('#uri').val(result.data.uri);
				var method = result.data.method;

				$.ajax({
					url: '/datadict/code/HTTP_METHOD',
					method: 'get',
					success: function(result, textStatus, jqXHR) {
						for (var i = 0; i < result.data.length; i++) {
							$("#method").append('<option value=\"' + result.data[i].key + '\"' + (method == result.data[i].key ? ' selected=\"selected\"' : '') + '>' + result.data[i].value + '</option>');
						}
					}
				});
			}
		});
	} else {
		$.ajax({
			url: '/datadict/code/HTTP_METHOD',
			method: 'get',
			success: function(result, textStatus, jqXHR) {
				for (var i = 0; i < result.data.length; i++) {
					$("#method").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
				}
			}
		});
	}

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
			sortorder: 'asc'
		});

		queryMicro();
	});

	$('button#submit').click(function() {
		$.ajax({
			url: '/sys/func',
			method: id ? 'put' : 'post',
			data: JSON.stringify({
				id: $('#id').val(),
				microId: $('#microId').val(),
				name: $('#name').val(),
				code: $('#code').val(),
				uri: $('#uri').val(),
				method: $('#method').val()
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
	location.href = '/sys-ui/func/list';
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
