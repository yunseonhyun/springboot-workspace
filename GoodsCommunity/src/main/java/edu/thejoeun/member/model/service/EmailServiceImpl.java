package edu.thejoeun.member.model.service;


import edu.thejoeun.member.model.mapper.EmailMapper;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional // 예외 발생하면 롤백할게 (기본값으로 커맛)
public class EmailServiceImpl implements EmailService {

    @Autowired // EmailConfig에 설정된 메일보내기 기능과 관련 환경설정 사용
    private JavaMailSender mailSender;

    @Autowired
    private EmailMapper emailMapper;

    @Autowired // 템플릿엔진 활용하여
    private SpringTemplateEngine templateEngine;

    // 이메일 보내기
    @Override
    public String sendEmail(String htmlName, String email) {

        // 6자리 난수 코드 생성하는 기능 불러오기
        String authKey = createAuthKey();

        try {
            // 제목
            String 제목 = null;

            switch (htmlName) {
                case "signup":
                    제목 = "[나의 프로젝트 명칭 / 회사 명칭] 회원가입 인증번호 입니다.";
                    break;
            }

            // 인증 메일 보내기
            // MimeMessage : Java에서 메일을 보내기 위해 메세지를 준비하는 객체
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //                                                메세지내용, 파일전송사용?, 문자인코딩지정
            // mailSender 객체를 이용해서 이메일 본문을 쉽게 설정할 수 있도록 도와줌
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);        // 이메일 받을 사람 지정
            helper.setSubject(제목);    // 이메일 제목 지정
            helper.setText(loadHtml(authKey, htmlName), true);
            // 이메일 본문 인증키 + 회사에서 인증키를 보낶다는 신뢰의 본문 내용 true는 html 형식이 맞다
            // 메일에 이미지 첨부 파일 첨부랑은 살짝 다름
            //          로고이미지를 보낼 때 보낼 이미지와 이미지를 보낼 때 담을 id 명칭 작성
            //           이미지를 담고갈 바구니 명칭,   이미지 본문 내용
            helper.addInline("logo", new ClassPathResource("static/images/logo.jpg"));
            // 모든 준비가 끝나면 진짜로 메일 보내기
            mailSender.send(mimeMessage); // 이메일 발송
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // map 이름에 key -value 형태로 "인증키" : 6자리, "email" : 인증번호 받은 사람의 이메일
        // 형태로 잠시 자바에서 보관
        Map<String, String> map = new HashMap<>();
        map.put("authKey", authKey);
        map.put("email", email);

        //  이전에 해당 이메일로 인증키를 발송한 기록이 있다면 키를 갱신
        int result = emailMapper.updateAuthKey(map); // 발송한 기록이 없으면 0이 result 담겨질 것


        if(result == 0){ // 이전에 인증키를 보낸적이 없음
            result = emailMapper.insertAuthKey(map); // 새로운 인증키 기록을 삽입하는 매퍼 메소드 호출
            // 이메일에서 인증키를 무사히 보냈다면 result에는 1이 담길 것
        }
        // 둘다 실패하여 최종 result가 0이라면 null 반환
        if(result == 0){
            return null;
        }
        /*
        필요한 타입:Map<String,Object>
        제공된 타입: Map<String,String>

        Map<String, String> map = new HashMap<String, String>()

        int updateAuthKey(Map<String, Object>map);

        emailMapper.updateAuthKey() 내부는 String, Object 형태이고,
        매개변수에 들어갈 데이터는 String, String 형태로 매개변수 내부 자료형 데이터와
        매개변수 내부에 들어갈 데이터 자료형이 일치하지 않아 발생한 빨간줄
         */
        // 이메일 발송 & 인증키 잠시 보관을 모두 성공하면 생성된 인증키 반환
        return authKey;
    }





    // HTML 파일을 읽어와 String으로 변환(타임리프 템플릿 사용해서 html 가져오기)
    // import org.thymeleaf.context.Context;
    public String loadHtml(String authKey, String htmlName) {
        Context context = new Context();

        // 타임리프가 적용된 html에서 사용할 값 추가
        context.setVariable("authKey", authKey);

        // templates/pages/auth 폴더에서 htmlName과 같은
        // ,html 파일 내용을 읽어와 String으로 변환
        return templateEngine.process("pages/auth" + htmlName, context);
    }

    /**
     * 인증번호 생성(영어 대문자 + 소문자 + 숫자6자리)
     * @return authKey
     */
    public String createAuthKey(){
        String key = ""; // 6자리 숫자 문자 혼합을 담을 빈 문자열 공간 생성

        for(int i=0; i<6; i++){ // key의 내부가 6자리로 채워지기 위해 0 ~ 5까지 5번 반복

            /**********
           * 만약에 우리팀이 숫자만 랜덤으로 출력하겠다!
             * for 문안에
             * int num = (int)(Math.random()*10);
             * key += num; 만 살려두기
             *
             * 우리팀이 영어만 랜덤으로 출력하겠다
             *  char ch = (char)(Math.random()*26 + 65); // A~Z(65 ~ 65 + 26)
             *
             * int sel2 = (int)(Math.random()*2);
             * if(sel2==0){
             * ch = (char)(ch + ('a' - 'A'));
             * }
             *
             * 영어중에서 대문자만 랜덤으로 6자리 출력하겠다.
             *  ch = (char)(ch + ('a' - 'A'));
             *  key += ch;
             *  만 살려두기
             *
             *  영어중에서 소문자만 랜덤으로 6자리 출력하겠다.
           *********/



            // 0, 1, 2 중 하나의 무작위 정수 뽑기 뽑힌 숫자는 sel1안에 저장
            // 만약에 0이 뽑히면 숫자 1,2 영어(대문자 or 소문자)
            int sel1 = (int)(Math.random()*3); // 0 : 숫자 / 1,2 영어

            if(sel1==0) { // sel1 뽑힌 숫자가 0이면 숫자 당첨 ~!
                int num = (int)(Math.random()*10); // 0~9
                key += num; // key 내부에 랜덤으로 뽑힌 숫자 하나 들어감
            } else { // 0이 아니면 영어 (대 소문자이기 때문에 else if가 아니라 else로 처리)
                // 우선은 무작위로 A부터 Z까지 대문자를 생성해서 ch 저장
                char ch = (char)(Math.random()*26 + 65); // A~Z(65 ~ 65 + 26)

                // 또 랜덤뽑기를 해서 0이나오면 소문자 ~ 1이 나오면 대문자
                int sel2 = (int)(Math.random()*2); // 0 : 소문자 / 1 : 대문자
                if(sel2==0){
                    ch = (char)(ch + ('a' - 'A')); // 대문자를 소문자로 변경
                }

                key += ch; // 위에서 랜덤의 랜덤으로 생성된 소문자나 대문자를 key 추가
            }
        }
        return key;
    }

    // 이메일, 인증번호 확인
    @Override
    public int checkAuthKey(Map<String, Object> map) {
        return emailMapper.checkAuthKey(map);
    }
}
