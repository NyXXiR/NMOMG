package com.study.springboot.service;


import java.io.File;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.study.springboot.dao.MemberDao;
import com.study.springboot.vo.Member;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

  MemberDao memberDao;

  @Autowired
  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public HashMap<String, String> censorId(Member member, String loginIdCheck) {
    // 반환 해줄 데이터 형식
    HashMap<String, String> censorship = new HashMap<>();

    // 중복된 아이디가 존재하는지 체크
    int checkId = memberDao.memberIdCheck(loginIdCheck);

    // 화원가입할 정보 db에 삽입
    if (member.getLoginId() != null && member.getNickname() != null
        && member.getPassword() != null) {
      int joinInfo = memberDao.memberInsert(member);
    }

    // 조건문으로 아이디 중복 검열기능
    if (checkId == 0 && loginIdCheck != null && loginIdCheck != "") {
      censorship.put("idCheck", "사용가능한 아이디 입니다.");
      censorship.put("loginIdCheck", loginIdCheck);
      return censorship;
    } else {
      censorship.put("idCheck", "이미 존재하는 아이디 입니다.");
      return censorship;
    }
  }

  // 닉네임 찾기
  @Override
  public String memberNickname(String loginId) {
    System.out.println("impl로 날라오 로그인된 아이디 " + loginId);
    String myNickname = memberDao.findByNicknameByLoginId(loginId);
    return myNickname;
  }

  @Override
  public Member updateMember(Member member, MultipartFile file, HttpSession session) {
    int updateByMemberNum = memberDao.updateByMemberNum(member);
    // 파일 저장 경로(서버에 저장)
    String uploadFolder = "/home/ubuntu/nmomg/assets/profile";


    log.info("upload file name: " + file.getOriginalFilename());
    log.info("upload file size: " + file.getSize());

    // 이용자의 memberNum을 이용하여 파일 이름 명명
    int fileNum = (int) session.getAttribute("memberNum");
    File saveFile = new File(uploadFolder, file.getOriginalFilename());

    try {
      file.transferTo(saveFile);
      // MultipartFile은 생성하자마자 파일을 바로 업로드하므로 업로드 후 파일명을 변경한다.
      File renamedFile = new File(uploadFolder, (Integer.toString(fileNum) + ".png"));
      saveFile.renameTo(renamedFile);
    } catch (Exception e) {
      log.error("파일 전송 에러: " + e.getMessage());
    }
    // 파일업로드 끝


    Member memberInfo = memberDao.selectAllByMemberNum(fileNum);
    System.out.println("memberinfo : " + memberInfo);

    // memberNum으로 업데이트 하는 쿼리

    return memberInfo;
  }

}
