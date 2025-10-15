package edu.the.joeun.service;

import edu.the.joeun.mapper.MemberMapper;
import edu.the.joeun.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    // 다른 개발자가 만든 비밀번호 암호화 셍성할 수 있는 객체
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<Member> getAll(){
        return memberMapper.getAll();
    }

    public void insertMember(Member member){

        // 비밀번호 암호화

        // 1단계 : 기존에 유저가 html에서 작성한 비밀번호를 가져온다
        // 가져온 비밀번호를 변수공간에 담아놓는다
        String 유저작성패스워드 = member.getPassword();

        // 유저가 작성한 비밀번호 암호화 처리를 진행한다.
        // 처리된 비밀번호를 특정 변수공간에 담아놓는다
        String 암호화처리패스워드 = bCryptPasswordEncoder.encode(유저작성패스워드);

        // 새로 만든 비밀번호를 member 내부에 있는 password 변수에 다시 담아놓는다.
        member.setPassword(암호화처리패스워드);

        // 위에서 변수 명칭으로 나누어놓은 코드 로직을
        // 한 번에 한 줄로 작성해도 된다
        // 나누는 이유 -> 개발을 진행할 때 System.out.println()
        // 로 코드 로직이 개발자가 원하는 형태로 진행되고 있는지 확인하기 위해
        // 변수명칭으로 나누어서 작성
        // member.setPassword(bCryptoPasswordEncoder.encode(member.getPassword()));

        memberMapper.insertMember(member);
        // 멤버가 명 쳥 저장되었는지 체크하지 않고
        // 멤버 저장 유무만 추후에 sql에서 전달받는 형태
    }
}
