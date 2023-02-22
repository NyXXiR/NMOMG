package com.study.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Board;
import com.study.springboot.vo.Stack;

@SpringBootTest
class NmomgApplicationTests {

 
  
  @Autowired
  private BoardDao boardDao;
  
  @Test
  void contextLoads() {
    
    
    //테스트 board 데이터 생성 코드
    /*
    for(int i=0; i<100; i++) {
     String title= "테스트제목";
     String content= "테스트내용";
     int memberNum= 3;
     String category = "recruit";
     String startDate= "2023-02-24";
      
     Board board= new Board(0, title, content, memberNum, "now()", category, startDate, "test");
      this.boardDao.boardWrite(board);
    }
   */

    //테스트 stack 데이터 생성 코드
    
    for(int i=0; i<100; i++) {
      String stack= "java";
   int boardNum=i+21;

 Stack stacks = new Stack(boardNum, stack);
       this.boardDao.stackWrite(stacks);
     }
  }
  
  

}
