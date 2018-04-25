var baseurl = "http://127.0.0.1:8081/bankcourt";

function getUserToken() {
    if (document.cookie.length > 0) {
        var c_name = "userInfo";
        var c_key = "userToken";

        var c_index = document.cookie.indexOf(c_name + "=")
        if (c_index != -1) {
            // 开始位置
            var c_start = c_index + (c_name+"=").length;
            // 结束位置
            var c_end = document.cookie.length;
            if (c_end == -1) c_end = document.cookie.length;
            // 截取字符串
            var cookie = document.cookie.substring(c_start, c_end);
            // 转JSON对象
            var jsonObj = JSON.parse(JSON.parse(cookie));
            // 返回token
            return jsonObj.userToken;
        }
    }
    return ""
}