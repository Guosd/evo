$(function() {
	$.ajax({
		url: '/datadict/code/DTS_ROLE',
		method: 'get',
		success: function(result, textStatus, jqXHR) {
			for (var i = 0; i < result.data.length; i++) {
				$("#role").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
			}
		}
	});
	$.ajax({
		url: '/datadict/code/DTS_STEP',
		method: 'get',
		success: function(result, textStatus, jqXHR) {
			for (var i = 0; i < result.data.length; i++) {
				$("#step").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
			}
		}
	});

	$(gridSelector).jqGrid({
		url: '/dts/message/page',
		datatype: 'local',
		colModel: [{
			label: '日志消息key',
			name: 'logMessageKey'
		}, {
			label: '事务消息key',
			name: 'messageKey'
		}, {
			label: '事务主键',
			name: 'businessKey'
		}, {
			label: '事务内容',
			name: 'content'
		}, {
			label: '事务生产者',
			name: 'producer'
		}, {
			label: '事务消费者',
			name: 'consumer'
		}, {
			label: '日志消息来源',
			name: 'role'
		}, {
			label: '事务阶段',
			name: 'step'
		}],
		sortname: 'id',
		sortorder: 'desc'
	});

	query();

	$('button#query').click(function() {
		query();
	});
	$('button#reset').click(function() {
		reset();
	});

	resetHeight();
});

function query() {
	jqGridQuery({
		logMessageKey: $('#logMessageKey').val(),
		messageKey: $('#messageKey').val(),
		businessKey: $('#businessKey').val(),
		content: $('#content').val(),
		producer: $('#producer').val(),
		consumer: $('#consumer').val(),
		role: $('#role').val(),
		step: $('#step').val()
	});
}

function reset() {
	$('#logMessageKey').val('');
	$('#messageKey').val('');
	$('#businessKey').val('');
	$('#content').val('');
	$('#producer').val('');
	$('#consumer').val('');
	$('#role').val('');
	$('#step').val('');
	query();
}
