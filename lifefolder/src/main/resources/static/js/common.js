/**
 * 判断是否为空
 * @param str
 * @returns {boolean}
 */
function strNotNull(str) {
    return undefined != str && null != str && '' !== str && 'undefined' != str;
}