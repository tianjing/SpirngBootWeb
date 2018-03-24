//依赖jquery  json2
/** @namespace tgtools */
var tgtools = {};

/** @namespace tgtools.util */
tgtools.util = {};
/** @namespace tgtools.util.StringUtil */
tgtools.util.StringUtil = {};

/**
 * 字符串是否有值
 * @function isNotEmpty
 * @memberof tgtools.util.StringUtil
 * @static
 * @param {string} str 待验证的字符串
 * @returns {boolean} true：有值
 */
tgtools.util.StringUtil.isNotEmpty = function (str) {
    return undefined != str && null != str && "" != str;
};
/** @namespace tgtools.util.net */
tgtools.util.net = {};
/**
 * 获取当前项目路径
 * @function getURL
 * @memberof tgtools.util.net
 * @static
 * @returns {string} 如：http://www.abc.com/EmptyProject
 */
tgtools.util.net.getURL = function () {

    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： cis/website/meun.htm
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8080
    var localhostPaht = curWwwPath.substring(0, pos); //获取带"/"的项目名，如：/cis
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    var rootPath = localhostPaht + projectName;
    return rootPath;
};
/** @namespace tgtools.util.URL */
tgtools.util.URL = {};
/**
 * 通过输入的参数名称获取URL的参数
 * @function getQueryString
 * @memberof tgtools.util.URL
 * @static
 * @param {String} name 参数名称
 * @returns {string} 返回参数值，如果没有则返回null
 */
tgtools.util.URL.getQueryString = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
};
/** @namespace tgtools.net */
tgtools.net = {};
/**
 * 异步ajax请求
 * @function asyncAjaxData
 * @memberof tgtools.net
 * @static
 * @param {String} url 请求地址
 * @param {json} data1 请求的json数据
 * @param {function}success 成功后回调函数
 */
tgtools.net.asyncAjaxData = function (method,url, data1,success) {
    var res = null;
    var messageid=null;
    if('undefined'!=typeof mini){
        messageid = mini.loading("处理中请等待", "Loading");
    }
    tgtools.net.rest.ajaxJson(method,url, data1, function (data) {
        if('undefined'!=typeof mini) {
            mini.hideMessageBox(messageid);
        }
        if ("function" == typeof (success)) {
            success(data);
        }
    }, function (error) {
        if('undefined'!=typeof mini) {
            mini.hideMessageBox(messageid);
        }
        if (error.statusText) {
            if ("parsererror" == error.statusText) {
                alert("数据转换出错");
            } else {
                alert(error.statusText);
            }
            return;
        }
        if (error.status && error.status != 200) {
            alert("访问出错；错误码：" + error.status + "内容：" + error.responseText);
            return;
        }
        alert(error);
    });
};
/**
 * 同步ajax请求
 * @function asyncAjaxData
 * @memberof tgtools.net
 * @static
 * @param {string} url 请求的url
 * @param {json} data1 请求的json数据
 * @returns {*}服务器返回的数据
 */
tgtools.net.ajaxData = function (method,url, data1) {
    var res = null;
    var messageid=null;
    if('undefined'!=typeof mini) {
        messageid = mini.loading("处理中请等待", "Loading");
    }
    tgtools.net.rest.syncajaxJson(method,url, data1, function (data) {
        res = data;
    }, function (error) {
        if (error.statusText) {
            if ("parsererror" == error.statusText) {
                alert("数据转换出错");
            } else {
                alert(error.statusText);
            }
            if('undefined'!=typeof mini) {
                mini.hideMessageBox(messageid);
            }
            return;
        }
        if (error.status && error.status != 200) {
            alert("访问出错；错误码：" + error.status + "内容：" + error.responseText);
            if('undefined'!=typeof mini) {
                mini.hideMessageBox(messageid);
            }
            return;
        }
        alert(error);
    });
    if('undefined'!=typeof mini) {
        mini.hideMessageBox(messageid);
    }
    return res;
};
/** @namespace tgtools.net.rest */
tgtools.net.rest = {};
/**
 * post异步请求
 * @function jsonpost
 * @memberof tgtools.net.rest
 * @static
 * @param {string} url 请求的地址
 * @param {json} data 请求的json数据
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.net.rest.ajaxJson = function (method,url, data, successcall, errorcall) {
    this.invoke(url, method, 'json', ("GET"==method.toUpperCase())?data:JSON.stringify(data), true, successcall, errorcall);
};

/**
 * post同步请求
 * @function syncjsonpost
 * @memberof tgtools.net.rest
 * @static
 * @param {string} url 请求的地址
 * @param {json} data 请求的json数据
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.net.rest.syncajaxJson = function (method,url, data, successcall, errorcall) {
    this.invoke(url, method, 'json',("GET"==method.toUpperCase())?data:JSON.stringify(data), false, successcall, errorcall);
};
/**
 * 通用请求方法
 * @function invoke
 * @memberof tgtools.net.rest
 * @static
 * @param {string} url 请求的地址
 * @param {string} method 请求类型 如 post get
 * @param {string} datatype 数据类型 参看jquery.ajax的dataType
 * @param {string} data 请求的数据
 * @param {bool} async 是否异步
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.net.rest.invoke = function (url, method, datatype, data, async, successcall, errorcall) {
    jQuery.ajax({
        "type": method,//'POST',
        "contentType": "application/" + datatype,
        "url": url,
        "data": data,
        "async": async,
        "dataType": datatype,//'json',
        "success": function (data) {
            if(data.success==undefined)
            {
                successcall(data);
            }
            else if (data.success) {
                var result;
                try {
                    result = eval("(" + data.data + ")");
                }
                catch (e) {
                    result = data.data;
                }
                if ("function" == typeof (successcall)) {
                    successcall(result);
                }
            } else {
                if ("function" == typeof (errorcall)) {
                    errorcall(data.error);
                }
            }
        },
        "error": function (e) {
            var error=e.responseText;
            if(error.indexOf("window.top.location.href=")>=0)
            {
                var start=error.indexOf("window.top.location.href=");
                var end=error.indexOf("</script>");
                eval(error.substring(start,end));
            }
            else if ("function" == typeof (errorcall)) {
                errorcall(e);
            }
        }
    });

};



//tgtools.plugins 插件操作
/** @namespace tgtools.plugins */
tgtools.plugins = {};
/**
 * 获取所有插件信息
 * @function list
 * @memberof tgtools.plugins
 * @static
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.plugins.list = function (successcall, errorcall) {
    var request = {User: ''};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/plugins/list', request, successcall, errorcall);
};
/**
 * 添加插件
 * @function add
 * @memberof tgtools.plugins
 * @static
 * @param {string} path 插件路径
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.plugins.add = function (path, successcall, errorcall) {
    var request = {User: '', Data: path};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/plugins/add', request, successcall, errorcall);
};
/**
 * 删除插件
 * @function del
 * @memberof tgtools.plugins
 * @static
 * @param {string} name 插件名称
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.plugins.del = function (name, successcall, errorcall) {
    var request = {User: '', Data: name};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/plugins/del', request, successcall, errorcall);
};

tgtools.plugins.unload = function (name, successcall, errorcall) {
    var request = {User: '', Data: name};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/plugins/unload', request, successcall, errorcall);
};

tgtools.plugins.load = function (name, successcall, errorcall) {
    var request = {User: '', Data: name};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/plugins/load', request, successcall, errorcall);
};
//tgtools.service  服务相关操作
/** @namespace tgtools.service */
tgtools.service = {};
/**
 * 运行服务
 * @function run
 * @memberof tgtools.service
 * @static
 * @param {string} id 服务的ID
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.service.run = function (id, successcall, errorcall) {
    var request = {User: '', 'Data': id};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/servicestable/run', request, successcall, errorcall);
};
/**
 * 暂停服务
 * @function stop
 * @memberof tgtools.service
 * @static
 * @param {string} id 服务ID
 * @param {function} successcall 成功回调方法
 * @param {function} errorcall 失败回调方法
 */
tgtools.service.stop = function (id, successcall, errorcall) {

    var request = {User: '', 'Data': id};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/servicestable/stop', request, successcall, errorcall);
};
/**
 * 删除服务
 * @function del
 * @memberof tgtools.service
 * @static
 * @param {string} id
 * @param {function} successcall
 * @param {function} errorcall
 */
tgtools.service.del = function (id, successcall, errorcall) {
    var request = {User: '', 'Data': id};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/servicestable/del', request, successcall, errorcall);
};
/**
 * 卸载服务
 * @function unload
 * @memberof tgtools.service
 * @static
 * @param {string} id 服务ID
 * @param {function} successcall
 * @param {function} errorcall
 */
tgtools.service.unload = function (id, successcall, errorcall) {
    var request = {User: '', 'Data': id};
    tgtools.net.rest.jsonpost(tgtools.util.net.getURL() + '/myrest/servicestable/unload', request, successcall, errorcall);
};
