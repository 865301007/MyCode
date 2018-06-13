window.onload=function(){
    var aInput=document.getElementsByTagName('input');
    var oUser=aInput[0];
    var oPwd=aInput[1]
    var aI=document.getElementsByTagName('label')[0];
    
    
    
    //用户名检测
    
    oUser.onfocus=function(){
        aI.innerHTML='';
    }
    
    oUser.onkeyup=function(){
        
    }
    
    oUser.onblur=function(){
		if(this.value==""){
            aI.innerHTML='用户名不可为空';
        }
    }
    
    //密码检测
    
    oPwd.onfocus=function(){
        if(oUser.value==""){
            aI.innerHTML='用户名不可为空';
        }
    }
    oPwd.onblur=function(){
        if(this.value==""){
            aI.innerHTML='密码不可为空';
        }
        oPwd.style.placeholder='请输入确认密码';
    }
    
    
}