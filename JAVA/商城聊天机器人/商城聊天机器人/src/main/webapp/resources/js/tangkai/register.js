function getLength(str){
    return str.replace(/[^\x00-xff]/g,"xx").length;  //\x00-xff 此区间是单子节 ，除了此区间都是双字节。
}
function findStr(str,n){
    var tmp=0;
    for(var i=0;i<str.length;i++){
        if(str.charAt(i)==n){
            tmp++;
        }
    }
    return tmp;
}

//获取包含中文的字符串长度
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
       var length = val.charCodeAt(i);
       if(length>=0&&length<=128)
        {
            len += 1;
        }
        else
        {
            len += 2;
        }
    }
    return len;
}

var inputCheck = new Array();
inputCheck[0] = false;
inputCheck[1] = false;
inputCheck[2] = false;
inputCheck[3] = false;

window.onload=function(){
    /*var aInput=document.getElementsByTagName('input');
    var oName=aInput[0];
    var dx=aInput[1];
    var pwd=aInput[3];
    var pwd2=aInput[4];*/
	var oName = document.getElementById("mobile_phone");
	var dx = document.getElementById("user_name");
	var pwd = document.getElementById("psw");
	var pwd2 = document.getElementById("psw2");
    
    var aP=document.getElementsByTagName('p');
    var name_msg=aP[0];
    var dx_msg=aP[0];
    var pwd_msg=aP[0];
    var pwd2_msg=aP[0];
    var name_length=0;
    var send=document.getElementById('send'),
        
	    times=60,
	    timer=null;
	    send.onclick=function(){      
	      // 计时开始 
            timer = setInterval(djs,1000);
	    } 
        function djs(){
    		send.value = times+"秒后重试";
			send.setAttribute('disabled','disabled');
            send.style.background='#ccc';
            send.style.border='1px solid #ccc';
			times--;
			if(times <= 0){
				send.value = "发送验证码";
				send.removeAttribute('disabled');
				times = 60;
				clearInterval(timer);
			}
		}
    
    
    //手机号检测
    
    oName.onfocus=function(){
        name_msg.style.display='block';
        oName.removeAttribute("placeholder");
        oName.style.border='1px solid #fff';
    }
       
    oName.onblur=function(){
        // 含有非法字符 ，不能为空 ，长度少于5个字符 ，长度大于25个字符
        var tel = /^1[3|4|5|7|8][0-9]\d{8}$/;
        if(!tel.test(this.value)){
            name_msg.innerHTML='<i>手机号不正确</i>';
			inputCheck[0] = false;
            oName.style.border='1px solid red';
        }
        else{
			name_msg.innerHTML='<i></i>';
			inputCheck[0] = true;
            oName.style.border='1px solid #fff';
        }
    }
    
    //用户名验证码检测
    
    dx.onfocus=function(){
        dx_msg.style.display='block';
        dx.removeAttribute("placeholder");
        dx.style.border='1px solid #fff';
    }
    dx.onblur=function(){
		inputCheck[1] = false;
        if(this.value==""){
            dx_msg.innerHTML='<i>用户名不可为空</i>';
            dx.style.border='1px solid red';
        }else if(getByteLen(this.value)<4 || getByteLen(this.value)>20){
            dx_msg.innerHTML='<i>用户名长度应为4到20个字符</i>';
            dx.style.border='1px solid red';
        }else{
			dx_msg.innerHTML='<i></i>';
			inputCheck[1] = true;
            dx.style.border='1px solid #fff';
        }
	}
    
    //密码检测
    pwd.onfocus=function(){
        pwd_msg.style.display='block';
        pwd.removeAttribute("placeholder");
        pwd.style.border='1px solid #fff';
    }
    pwd.onblur=function(){
        var m=findStr(pwd.value,pwd.value[0]);
        var re_n=/[^\d]/g;
        var re_t=/[^a-zA-Z]/g;
		inputCheck[2] = false;
        if(this.value==""){
            pwd_msg.innerHTML='<i>密码不可为空</i>';
            pwd.style.border='1px solid red';
        }else if(m==this.value.length){
            pwd_msg.innerHTML='<i>密码不可使用相同的字符</i>';
            pwd.style.border='1px solid red';
        }else if(this.value.length<6 || this.value.length>16){
            pwd_msg.innerHTML='<i>密码长度应为6到16个字符</i>';
            pwd.style.border='1px solid red';
        }else if(!re_n.test(this.value)){
            pwd_msg.innerHTML='<i>密码不能全为数字</i>';
            pwd.style.border='1px solid red';
        }else if(!re_t.test(this.value)){
            pwd_msg.innerHTML='<i>密码不能全为字母</i>';
            pwd.style.border='1px solid red';
        }else{
			pwd_msg.innerHTML='<i></i>';
			inputCheck[2] = true;
            pwd.style.border='1px solid #fff';
        }
    }
    
    //确认密码
    pwd2.onblur=function(){
		inputCheck[3] = false;
        if(this.value!=pwd.value){
            pwd2_msg.innerHTML='<i></i>两次密码输入到不一致';
            pwd.style.border='1px solid red';
        }else if(this.value==""){
            pwd2_msg.innerHTML='<i></i>请输入密码';
            pwd.style.border='1px solid red';
        }else{
			pwd2_msg.innerHTML='<i></i>';
			inputCheck[3] = true;
            pwd2.style.border='1px solid #fff';
        }
    }
        
}

function inputIsOk(){
    for(var i=0;i<inputCheck.length;i++){
        if(!inputCheck[i]){
            return false;
        }
    }
    return true;
}

