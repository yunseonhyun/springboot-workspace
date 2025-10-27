package edu.thejoeun.common.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// Service나 Controller와 같이 특정 명칭을 지정해주지 않는 파일
// 객체처럼 활용해야하는 파일은 @component를 작성해서
// SpringBoot에 @Bean으로 등록
@Component 
@Slf4j // lod.info()를 사용하게 해주는 어노테이션
public class BoardDeleteScheduling {

}
