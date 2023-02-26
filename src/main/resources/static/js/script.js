



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
