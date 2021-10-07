function getJSPath (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    serverpath="/"+ss[3]+"/";
    return serverpath;
}
try{
    if(!ServerPath)
    {
        ServerPath=serverpath;
    };
}
catch(e){
    var ServerPath=getJSPath("jsload.js");

}

document.write('<script src="'+ServerPath+'js/json2.js" type="text/javascript"></script>');
document.write('<script src="'+ServerPath+'js/jquery.cookie.js" type="text/javascript"></script>');
document.write('<script src="'+ServerPath+'webjars/js/develop/tgtools.js" type="text/javascript"></script>');
document.write('<script src="'+ServerPath+'webjars/js/develop/notify/NotifyWebSocket.js" type="text/javascript"></script>');
