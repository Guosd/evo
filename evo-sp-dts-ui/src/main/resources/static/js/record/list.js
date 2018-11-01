$(function() {
	$.ajax({
		url: '/datadict/code/DTS_STEP',
		method: 'get',
		success: function(result, textStatus, jqXHR) {
			for (var i = 0; i < result.data.length; i++) {
				$("#producerStep").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
				$("#consumerStep").append('<option value=\"' + result.data[i].key + '\">' + result.data[i].value + '</option>');
			}
		}
	});

	$(gridSelector).jqGrid({
		url: '/dts/record/page',
		datatype: 'local',
		colModel: [{
			label: '事务主键',
			name: 'businessKey'
		}, {
			label: '生产者阶段',
			name: 'producerStep'
		}, {
			label: '消费者阶段',
			name: 'consumerStep'
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
	$('button#message').click(function() {
		location.href = '/dts-ui/message/list';
	});

	resetHeight();
});

function query() {
	jqGridQuery({
		businessKey: $('#businessKey').val(),
		producerStep: $('#producerStep').val(),
		consumerStep: $('#consumerStep').val()
	});
}

function reset() {
	$('#businessKey').val('');
	$('#producerStep').val('');
	$('#consumerStep').val('');
	query();
}
