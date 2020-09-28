let DEFAULT_PAGE_SIZE = 20;
let dataTable = {
    // 目标对象
    target:"",
    // 表格列数组
    column:[{title:"序号",name:"",width:"",class:"",res:function (res){}}],
    // 默认初始化后加载数据
    initDraw:true,
    // 请求路径
    url:"",
    // 请求数据
    data:{
        pageSize:DEFAULT_PAGE_SIZE,
        pageNum:strNotNull(this.nowIndex)?this.nowIndex: this.nowIndex != -1?this.nowIndex :1
    },
    // ajax默认POST
    type: "POST",
    nowIndex:1,
    // 初始化方法
    init:function (data) {
        if (null != data) {
            for (let key in data) {
                this[key] = data[key];
            }
        }
        //初始化头部
        $(this.target).children("thead").remove();
        var html = '<thead><tr>';
        $(this.column).each(function (i,temp) {
            //标题
            var _t = "";
            if (strNotNull(temp.title)) {_t = temp.title;}
            //宽
            var _w = "";
            if (strNotNull(temp.width)) {_w = 'width="'+temp.width+'"';}
            //样式
            var _c = "";
            if (strNotNull(temp.class)) {_c = 'class="'+temp.class+'"';}
            html += '<th '+_w+' '+_c+'>'+_t+'</th>';
        });
        html += '</tr></thead>';
        $(this.target).prepend(html);
        if (this.initDraw) {
            this.draw();
        }
    },
    // 渲染表格数据
    draw:function () {
        var _data = this.data;
        var data2 = this.beforeAjax();
        if (null != data2) {
            for (var key in data2) {
                _data[key] = data2[key];
            }
        }
        loading()
        $.ajax({
            url:this.url,
            data:_data,
            type:this.type,
            success:function (res) {
                loadingClose();
                if (res.code == "200") {
                    $(dataTable.target).children("tbody").remove();
                    // 加载分页
                    dataTable.loadPageInfo(res.data);

                } else {
                    message.error(res.message);
                }
            },
            error:function (res) {
                loadingClose();
                message.error(res.message);
            }
        });
    },
    // 请求前的数据封装
    beforeAjax:function(){return {};},
    loadPageInfo:function (data) {
        var row = '<nav aria-label="Page navigation" class="text-right">\n' +
            '       <ul class="pagination">\n';
        if (data.hasPreviousPage) {
            row += '<li><a onclick="'+funName+'('+data.prePage+')" aria-label="Previous">';
        } else {
            row += '<li class="disabled"><a aria-label="Next">';
        }
        row += '    <span aria-hidden="true">&laquo;</span>\n' +
            '               </a>\n' +
            '           </li>\n';

        $.each(data.navigatepageNums,function(i,temp){
            if (data.pageNum == temp) {
                row += '<li class="active"><a>'+temp+'</a></li>';
            } else {
                row += '<li><a onclick="'+funName+'('+temp+')" >'+temp+'</a></li>';
            }
        });

        if (data.hasNextPage) {
            row += '<li><a onclick="'+funName+'('+data.nextPage+')" aria-label="Next">';
        } else {
            row += '<li class="disabled"><a aria-label="Next">';
        }
        row += '        <span aria-hidden="true">&raquo;</span>\n' +
            '               </a>\n' +
            '           </li>\n' +
            '       </ul>\n' +
            '</nav></td></tr>';
        $(this.target).children("tfoot").remove();
        // $(this.target).append('<tfoot>'+row+'</tfoot>');
        console.info(row);
        return data.pageNum;
    }
};