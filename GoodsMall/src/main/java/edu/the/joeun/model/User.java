package edu.the.joeun.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Data : 가장 많이 사용되는 어노테이션 <br/>
 *         여러개의 어노테이션을 한 번에 적용해주는 복합 어노테이션 <br>
 * @Getter, @Setter, @ToString, @EqualAndHashCode, @RequiredArgsConstructor
 *
 * 이런 모든 어노테이션을 포함하고 있는 어노테이션
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String role;
    private String create_at; // SQL에서 가입일자나 유저정보 수정일자를
    private String update_at; // 관리자가 확인하고자 할 때 사용하기 위해 넣어놓은 변수명칭
}
