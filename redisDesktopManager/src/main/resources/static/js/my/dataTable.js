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
    draw:function (pageNum) {
        var _data = this.data;
        if (strNotNull(pageNum)) {
            _data["pageNum"] = pageNum;
        }
        var data2 = this.beforeAjax();
        if (null != data2) {
            for (var key in data2) {
                _data[key] = data2[key];
            }
        }
        loading();
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
                    // 加载数据行
                    if (!strNotNull(res.data) || res.data.list.length <= 0) {
                        var row = '<tbody><tr><td colspan="'+dataTable.column.length+'">暂无数据</td></tr></tbody>';
                        $(dataTable.target).children("tbody").remove();
                        $(dataTable.target).append(row);
                        dataTable.nowIndex = 1;
                        return;
                    }

                    let html = '';
                    $.each(res.data.list,function (i,temp) {
                        html += '<tr>';
                        $(dataTable.column).each(function (coli,col) {
                            var _tempColData = '';
                            if (strNotNull(col.res) && typeof col.res == 'function') {
                                _tempColData = col.res(i,temp,temp[col.name]);
                            }
                            if (strNotNull(_tempColData)) {
                                html += '<td>'+_tempColData+'</td>';
                            } else if (strNotNull(temp[col.name])) {
                                html += '<td>'+temp[col.name]+'</td>';
                            } else {
                                html += '<td></td>';
                            }
                        });
                        html += '</tr>';
                    });
                    $(dataTable.target).children("thead").after("<tbody>"+html+"</tbody>");
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
        var row = '<tfoot><tr><td colspan="'+this.column.length+'">' +
            '<nav aria-label="Page navigation" class="text-right">\n' +
            '       <ul class="pagination">\n';
        if (data.hasPreviousPage) {
            row += '<li><a onclick="dataTable.draw('+data.prePage+')" aria-label="Previous">';
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
                row += '<li><a onclick="dataTable.draw('+temp+')" >'+temp+'</a></li>';
            }
        });

        if (data.hasNextPage) {
            row += '<li><a onclick="dataTable.draw('+data.nextPage+')" aria-label="Next">';
        } else {
            row += '<li class="disabled"><a aria-label="Next">';
        }
        row += '        <span aria-hidden="true">&raquo;</span>\n' +
            '               </a>\n' +
            '           </li>\n' +
            '       </ul>\n' +
            '</nav></td></tr></tfoot>';

        $(this.target).children("tfoot").remove();
        $(this.target).append(row);
        return data.pageNum;
    }
};