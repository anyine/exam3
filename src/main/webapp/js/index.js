$(document).ready(function(){
	
	
	//选项卡

	$(".nav_menu ul li").click(function(){
		var sid = $(this).attr("sid");
		$(".nav_menu ul li").removeClass("title_hover");
		$(this).addClass("title_hover");
		$(".block").hide();
		$(".block"+sid).show();
	})


/**考核详情左侧菜单**/
var aMenuOneLi = $(".menu-one > li");
    var aMenuTwo = $(".menu-two");
    $(".menu-one > li > .menu-header").each(function (i) {
        $(this).click(function () {
            if ($(aMenuTwo[i]).css("display") == "block") {
                $(aMenuTwo[i]).slideUp(300);
                $(aMenuOneLi[i]).removeClass("menu-show");
			
            } else {
                for (var j = 0; j < aMenuTwo.length; j++) {
                    $(aMenuTwo[j]).slideUp(300);
                    $(aMenuOneLi[j]).removeClass("menu-show");
                }
                $(aMenuTwo[i]).slideDown(300);
                $(aMenuOneLi[i]).addClass("menu-show");
				
            }
        });
		$(".menu-two li").click(function(){
			var t = $(this)		 				 
			 if(!t.hasClass("menuTwo-current")){   
		      var sid = t.attr("sid");         
		      t.siblings(".menu-two li").removeClass("menuTwo-current");  
			  t.addClass("menuTwo-current");   
			 }
								  
	    })
    })
	
	
})// JavaScript Document