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
