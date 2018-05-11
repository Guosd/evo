var token = localStorage.getItem('X-Authorization') || undefined;
var gridSelector = "#grid-table";
var pagerSelector = "#grid-pager";

$.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
};

$.ajaxSetup({
	contentType: 'application/json',
	dataType: 'json',
	cache: false,
	headers: {
		'X-Authorization': token
	},
	xhrFields: {
		withCredentials: true
	},
	error: function(jqXHR, textStatus, errorThrown) {
		if (401 === jqXHR.status) {
			top.location.href = '/ui/comm/login';
		} else {
			alert((jqXHR.responseJSON.code + (jqXHR.responseJSON.message || '')) || jqXHR.responseText);
		}
	},
	complete: function(jqXHR, textStatus) {
		var token = jqXHR.getResponseHeader('X-Authorization');
		if (token) {
			if (window.localStorage) {
				localStorage.setItem('X-Authorization', token);
			}

			$.cookie('X-Authorization', token, {path: '/' });
		}
	}
});

$.extend($.jgrid.defaults, {
	mtype: 'GET',
	datatype: "json",
	rowNum: 20,
	rowList: [10, 20, 30, 50, 100, 500],
	rownumbers: true,
	altRows: true,
	multiselect: true,
	multiboxonly: true,
	viewrecords: true,
	autowidth: true,
	height: 'auto',
	shrinkToFit: false,
	autoScroll: false,
	pager: pagerSelector,
	jsonReader: {
		root: 'data.dataList',
		page: 'data.pageNo',
		records: 'data.totalRecord',
		total: 'data.totalPage'
	},
	prmNames: {
		page: 'pageNo',
		rows: 'pageSize',
		sort: 'pageSort',
		order: 'pageOrder',
		search: null,
		nd: 'nd',
		npage: null
	},
	loadComplete: function(xhr) {
		$(gridSelector).closest('.ui-jqgrid-bdiv').css({'overflow-x': 'scroll'});
		resetHeight();
	}
});

function resetHeight() {
//	parent.document.getElementById('mainFrame').height = document.body.scrollHeight;
	parent.document.getElementById('mainFrame').height = $(parent).height() - 5 - parent.$('#navbar').height() - parent.$('#sidebar-shortcuts').height();
}

function jqGridQuery(postData, customGridSelector) {
	$(customGridSelector || gridSelector).setGridParam({
		datatype: 'json',
		pageNo: 1,
		postData: postData
	}).trigger('reloadGrid');
}

function jqGridSelectIds(customGridSelector) {
	return $(customGridSelector || gridSelector).jqGrid('getGridParam', 'selarrrow');
}

function jqGridSelectRowData(id, customGridSelector) {
	return $(customGridSelector || gridSelector).jqGrid('getRowData', id);
}

function jqGridSelectRowDatas(customGridSelector) {
	var ids = jqGridSelectIds(customGridSelector);

	var rowDatas = [];
	for (var i = 0; i < ids.length; i++) {
		rowDatas.push(jqGridSelectRowData(ids[i], customGridSelector));
	}

	return rowDatas;
}

function getAllPropertyNames(obj) {
	var props = [];
	do {
		props = props.concat(Object.getOwnPropertyNames(obj));
	} while (obj = Object.getPrototypeOf(obj));
	return props.join('\r\n');
}
