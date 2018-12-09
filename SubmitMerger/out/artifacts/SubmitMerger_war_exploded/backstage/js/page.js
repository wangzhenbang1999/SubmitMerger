var Pagination = {

    code: '',

    Extend: function(e,data) {
    	Pagination.e = e;
        data = data || {};
        Pagination.pageTotal = data.pageTotal || 1;
        Pagination.currentPage = data.currentPage || 1;
        Pagination.step = data.step || 3;
        if(data.url.indexOf("&page=")<0)
        	Pagination.url = data.url || "";
        else
        	Pagination.url = data.url.substring(0,data.url.indexOf("&page=")) || "";
    },

    Add: function(s, f) {
        for (var i = s; i < f; i++) {
        	if(i==Pagination.currentPage) Pagination.code += '<a class="cur"  href="'+Pagination.url+'&page='+i+'"><span class="Bg"><b>' + i + '</b></span></a>';
        	else Pagination.code += '<a href="'+Pagination.url+'&page='+i+'"><span class="Bg"><b>' + i + '</b></span></a>';
        }
    },

    Last: function() {
        Pagination.code += '<i>...</i><a href="'+Pagination.url+'&page='+Pagination.pageTotal+'"><span class="Bg"><b>' + Pagination.pageTotal + '</b></span></a>';
    },

    First: function() {
        Pagination.code += '<a href="'+Pagination.url+'&page='+1+'"><span class="Bg"><b>1</b></span></a><i>...</i>';
    },

    Finish: function() {
    	var pre = "";
    	if(Pagination.currentPage<=1)
    		pre = ' href="'+Pagination.url+'&page='+1+'"';
    	else pre = ' href="'+Pagination.url+'&page='+(Pagination.currentPage-1)+'"';
    	
    	var next = "";
    	if(Pagination.currentPage==Pagination.pageTotal)
    		next = ' href="'+Pagination.url+'&page='+Pagination.pageTotal+'"';
    	else next = ' href="'+Pagination.url+'&page='+(Pagination.currentPage+1)+'"';
    	
    	Pagination.code = '<a class="previous"'+pre+' title="上一页"><span class="Bg"><b>&nbsp;</b></span></a>'
    						+Pagination.code
    						+'<a class="nextpage"'+next+'title="下一页"><span class="Bg"><b>&nbsp;</b></span></a>';
    	
        Pagination.e.innerHTML = Pagination.code;
        Pagination.code = '';
    },

    // find pagination type
    Start: function() {
        if (Pagination.pageTotal < Pagination.step * 2 + 4) {
            Pagination.Add(1, Pagination.pageTotal + 1);
        }
        else if (Pagination.currentPage < Pagination.step * 2 + 1) {
            Pagination.Add(1, Pagination.step * 2 + 2);
            Pagination.Last();
        }
        else if (Pagination.currentPage > Pagination.pageTotal - Pagination.step * 2) {
            Pagination.First();
            Pagination.Add(Pagination.pageTotal - Pagination.step * 2, Pagination.pageTotal + 1);
        }
        else {
            Pagination.First();
            Pagination.Add(Pagination.currentPage - Pagination.step, Pagination.currentPage + Pagination.step + 1);
            Pagination.Last();
        }
        Pagination.Finish();
    },

    // init
    Init: function(e, data) {
        Pagination.Extend(e,data);
        Pagination.Start();
    }
};



