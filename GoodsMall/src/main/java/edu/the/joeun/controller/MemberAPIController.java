package edu.the.joeun.controller;

import edu.the.joeun.model.Member;
import edu.the.joeun.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {

    @Autowired
    private MemberService memberService;

    /**
     *
     * const response = await fetch('/api/member/add', { <br/>
     *             method: 'POST', <br/>
     *             headers: { <br/>
     *                 'Content-Type': 'application/json', <br/>
     *             }, <br/>
     *             body: JSON.stringify(memberData), <br/>
     *                   json 형태의 데이터를 문자열로 변환해서 자바로 전송! <br/>
     *          }); <br/>
     *
     * 클라이언트가 /api/member/add로 데이터 저장요청을 하러 <br/>
     * 위 주소로 들어오면 <br/>
     *
     * js로 들고온 문자열형태 키-값 데이터를 <br/>
     * 자바 모델에 걸맞는 클래스 변수명칭에 <br/>
     * 각 키-데이터를 객체 형태로 대입 <br/>
     *
     * @param member js에서 가져온 문자열 데이터를
     * @RequestBody 이용하여 Member에 걸맞는 객체 형태로 변환해서 값 대입해놓기
     *
     * 저장된 데이터를 기반으로 service 기능 시작 후
     * sevice 기능에 대한 겨로가 유무를 클라이언트한테 전달
     */
    // postMapping 데이터 저장 시작
    @PostMapping("/api/member/add")
    public void insertMember(@RequestBody Member member){
        memberService.insertMember(member);
    }
}
