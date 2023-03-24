
var loginCheck= document.querySelector(".login-sessionCheck").value;
var loginCheckSuccess= document.querySelectorAll(".login-sessionCheck-exist");
var loginCheckFail=document.querySelectorAll(".login-sessionCheck-notExist");


if(loginCheck===("")){
	
	loginCheckSuccess.forEach(item => item.style.display="none")
		
}else{
	loginCheckFail.forEach(item => item.style.display="none")

}


//시작: 사이드바에 들어갈 게시판 리스트
$.ajax({
    url: '/board/list?category=recruit',
    success: function (x) {
        $('.sidebar-board1').html(x);
    }

});

$.ajax({
    url: '/board/list?category=apply',
    success: function (x) {
        $('.sidebar-board2').html(x);
    }

});

$.ajax({
    url: '/board/list',
    success: function (x) {
        $('.sidebar-board3').html(x);
    }

});


//끝: 사이드바에 들어갈 게시판 리스트
