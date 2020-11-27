/**
 * 判断是否为空
 * @param str
 * @returns {boolean}
 */
function strNotNull(str) {
    return undefined != str && null != str && '' !== str && 'undefined' != str;
}
/**
 * 遮罩层
 */
function loading() {
    var i = layer.load();
    window.layerLoadIndex = i;
}
/**
 * 关闭遮罩层
 */
function loadingClose() {
    layer.close(window.layerLoadIndex);
}

/**
 * 提示消息对象
 * @type {{success: message.success, warning: message.warning, getOptions: (function(): {debug: boolean, showMethod: string, extendedTimeOut: string, onclick: null, showDuration: string, timeOut: string, hideEasing: string, positionClass: string, hideDuration: string, preventDuplicates: boolean, closeButton: boolean, showEasing: string, progressBar: boolean, hideMethod: string}), error: message.error}}
 */
let message = {
    getOptions : function() {
        var options = {
            "closeButton": true,
            "debug": false,
            "progressBar": true,
            "preventDuplicates": false,
            "positionClass": "toast-top-center",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "1000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        return options;
    },
    success:function(str,timeout){
        str = (str || "操作成功");
        toastr.options = this.getOptions();
        toastr.options.timeOut = strNotNull(timeout)?timeout:"1000";
        toastr.success(str);
    },
    error:function (str,timeout) {
        str = (str || "操作失败");
        toastr.options = this.getOptions();
        toastr.options.timeOut = strNotNull(timeout)?timeout:"1000";
        toastr.error(str);
    },
    warning:function(str,timeout) {
        str = (str || "警告");
        toastr.options = this.getOptions();
        toastr.options.timeOut = strNotNull(timeout)?timeout:"1000";
        toastr.warning(str);
    }
};

/**
 * 填充表单数据
 * @param target 目标表单
 * @param obj 数据
 */
function loadFormData(obj,callFun){
    var key,value,tagName,type,arr;
    for(x in obj){
        key = x;
        value = obj[x];
        $("[name='"+key+"'],[name='"+key+"[]']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');

            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).attr('checked',$(this).val()==value);
                }else if(type=='checkbox') {
                    arr = (value+"").split(',');
                    for (var i = 0; i < arr.length; i++) {
                        if ($(this).val() == arr[i]) {
                            $(this).attr('checked', true);
                            break;
                        }
                    }
                } else if (type == 'file') {
                    //不做操作
                }else{
                    $(this).val(value);
                }
            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
                $(this).val(value);
            } else {
                $(this).html(value);
            }

        });
    }
    if (strNotNull(callFun) && "function"== typeof callFun) {
        callFun();
    }
}

/**
 * 清空表单数据
 * @param target 目标
 * @param callFun
 */
function clearFormData(target,callFun){
    //清空数据
    $(target).find("input[type=hidden]").val("");
    $(target).find("input[type=text]").val("");
    $(target).find("input[type=number]").val("");
    $(target).find("input[type=checkbox]").attr('checked', false);
    $(target).find("input[type=file]").val();
    $(target).find("TEXTAREA").val("");
    $(target).find("SELECT option").attr("selected","");
    if (strNotNull(callFun) && typeof(callFun) == "function") {
        callFun();
    }
}

/**
 * 获取url中的参数
 * @param name
 * @returns {string|null}
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

/**
 * 分页插件
 * @param target
 * @param data
 * @param loadfun
 */
function pagination(target, data, loadfun) {
    if (strNotNull(data.list) && data.list.length > 0) {
        var html = '<nav aria-label="Page navigation">' +
            '   <ul class="pagination">';
        if (data.prePage == 0) {
            html += '<li class="disabled"><a aria-label="Next">';
        } else {
            html += '<li><a onclick="'+loadfun+'('+data.prePage+')" aria-label="Previous">';
        }
        html += '    <span aria-hidden="true">&laquo;</span></a></li>';

        $.each(data.navigatepageNums,function(i,temp){
            if (data.pageNum == temp) {
                html += '<li class="active"><a>'+temp+'</a></li>';
            } else {
                html += '<li><a onclick="'+loadfun+'('+temp+')" >'+temp+'</a></li>';
            }
        });

        if (data.nextPage == 0) {
            html += '<li class="disabled"><a aria-label="Next">';
        } else {
            html += '<li><a onclick="'+loadfun+'('+data.nextPage+')" aria-label="Next">';
        }
        html += '    <span aria-hidden="true">&raquo;</span></a></li>'
        html += '   </ul></nav>';

        $(target).append(html);
    } else {
        $(target).find("nav").remove();
    }
}

/**
 * 分页插件
 * @param id
 * @param data
 * @param funName
 * @param tr
 * @param dataTableObj
 * @returns {number}
 */
// function loadPageInfo(id,data,funName,tr,dataTableObj) {
//
//     var row = '<tr><td colspan="'+$(id).find("thead tr th").length+'">';
//     row += '<div class="col-sm-4"></div>' +
//         '<div class="pagination col-sm-3">' +
//         '<spn class="text-right" >共'+data.total+'条，每页</spn>' +
//         '       <select name="page_size" onchange="setPageSize(this,'+funName+','+dataTableObj+')">' +
//         '           <option value="30">30条</option>' +
//         '           <option value="50">50条</option>' +
//         '           <option value="100">100条</option>' +
//         '           <option value="150">150条</option>' +
//         '           <option value="200">200条</option>' +
//         '       </select>' +
//         '<span class="text-right">，跳转到<input onkeyup="'+funName+'(this.value)'+'" type="text" style="outline:none;max-width: 20px;border-left: none;border-right: none;border-top: none;" class="text-center"/></span> '+
//         '</div>';
//     row += '<nav aria-label="Page navigation" class="text-right">\n' +
//         '       <ul class="pagination">\n';
//     if (data.prePage == 0) {
//         row += '<li class="disabled"><a aria-label="Next">';
//     } else {
//         row += '<li><a onclick="'+funName+'('+data.prePage+')" aria-label="Previous">';
//     }
//     row += '    <span aria-hidden="true">&laquo;</span>\n' +
//         '               </a>\n' +
//         '           </li>\n';
//     var active = "";
//     $.each(data.navigatepageNums,function(i,temp){
//         if (data.pageNum == temp) {
//             row += '<li class="active"><a>'+temp+'</a></li>';
//         } else {
//             row += '<li><a onclick="'+funName+'('+temp+')" >'+temp+'</a></li>';
//         }
//     });
//
//     if (data.nextPage == 0) {
//         row += '<li class="disabled"><a aria-label="Next">';
//     } else {
//         row += '<li><a onclick="'+funName+'('+data.nextPage+')" aria-label="Next">';
//     }
//     row += '        <span aria-hidden="true">&raquo;</span>\n' +
//         '               </a>\n' +
//         '           </li>\n' +
//         '       </ul>\n' +
//         '</nav></td></tr>';
//
//     $(id+">tfoot").remove();
//     $(id).append('<tfoot>'+row+'</tfoot>');
//     if(strNotNull(tr)){
//         $(id+'>tfoot tr').before('<tr>'+tr+'</tr>');
//     }
//     $('body').find('[name=page_size]').val(DEFAULT_PAGE_SIZE);
//     return data.pageNum;
// }


