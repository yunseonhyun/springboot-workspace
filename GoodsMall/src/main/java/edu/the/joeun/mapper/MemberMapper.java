package edu.the.joeun.mapper;

import edu.the.joeun.model.Member;
import edu.the.joeun.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    // mybatis xml에서 작성한 sql을 가져올 id와 기능명칭 작성
    // resource/mappers/memberMapper.xml 파일에서
    // id 값이 insertMember인 sql 구문을 가져와 보유하고 있는 형태를 띄울 것
    List<Member> getAll();


    void insertMember(Member member);
}
