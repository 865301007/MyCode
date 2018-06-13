function chatResponse(request){
	var springUrl=$("#hiddenSpringUrl").val();
	var response = "客服机器人开了个小差";
	$.ajax({
        url: springUrl+"/cart/delete",
        async: false,
        cache: false,
        type: 'post',
        dataType: "json",
        contentType:"application/json",
        data:JSON.stringify(request),
        success: function (data)
        {
        	response = data.message;
        },
	 });
	return response;
}