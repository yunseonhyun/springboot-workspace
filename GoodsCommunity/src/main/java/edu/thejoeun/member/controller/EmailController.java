package edu.thejoeun.member.controller;

import edu.thejoeun.member.model.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SessionAttributes("{authKey}")
@RestController
@RequestMapping("email") // 아래 작성하는 모든 주소 앞에 email을 붙이겠다
@RequiredArgsConstructor // @Autowired 생성자 방식을 모두 자동으로 완성
// 하나하나 @Autowired 작성 안해도됨
public class EmailController {
    // @Autowired -> @RequiredArgsConstructor 작성했기 때문에 생략 가능
    private final EmailService emailService; // 다른사람들이 조작하지 못하도록 final 상수 설정
    @PostMapping("signup") // api : email/signup
    public int signup(@RequestBody String email){
        String authKey = emailService.sendEmail("signup", email);
        if(authKey != null){ // 인증번호가 반환돼서 돌아옴
            // 이메일 보내기 성공
            return 1;
        }
        // 이메일 보내기 실패
        return 0;
    }
    /**
     * 입력된 인증번호와 Session에 저장되어 있는 인증번호 비교
     * @param map 전달받은 JSON에 -> Map 형태로 변경해서 저장
     * @return 인증여부 일치 여부가 0, 1로 반환될 것
     */
    @PostMapping("checkAuthKey") // api : email/checkAuthKet
    public int checkAuthKey(@RequestBody Map<String, Object> map) {
        // 입력받은 이메일, 인증번호가 있는지 조회
        // 이메일 있고, 인증번호 일치 == 1 일치하지 않으면 0이 나올것
        return emailService.checkAuthKey(map);
    }
}
