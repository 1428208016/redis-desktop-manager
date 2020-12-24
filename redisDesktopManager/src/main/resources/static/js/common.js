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
