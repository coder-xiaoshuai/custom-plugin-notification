
    var exec = require('cordova/exec');  
    var myFunc = function(){};  
      
      
   
    myFunc.prototype.showDefaultNotification=function(success, error) {  
        exec(success, error, "NotificationPlugin", "show_notification_test", []);  
    };  
       // arg1：成功回调  
    // arg2：失败回调  
    // arg3：通知的标题
    // arg4：通知的内容  
    // arg5：第一次弹通知的提示 
      
    myFunc.prototype.showCustomNotification=function(success, error,title,content,firstTip) {  
        exec(success, error, "NotificationPlugin", "show_notification_custom", [title,content,firstTip]);  
    };  
    
    var showt = new myFunc();  
    module.exports = showt;  

