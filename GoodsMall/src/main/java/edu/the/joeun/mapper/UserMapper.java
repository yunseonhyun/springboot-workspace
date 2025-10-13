package edu.the.joeun.mapper;

import edu.the.joeun.model.Goods;
import edu.the.joeun.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAll();

    // 단순 저장 확인용 void(= 반환데이터 없음) 선택

    void insertUser();

    /*
    insert의 경우 void와 int 둘 다 가능
        int - return = 저장된 데이터 수 반환
            여러개의 데이터를 한번에 저장할 때 몇개의 데이터가 저장되었고,
            몇 개의 데이터가 저장되지 않았는지 클라이언트한테 전달하고자 할 때 사용
        void - 저장결과 유무 확인할 수 있다.
            단일 데이터를 저장하고, 데이터가 몇 개 저장되었는지
            클라이언트한테 전달하지 않을 때 주로 사용 (단순 저장)
    select, update, delete 위 insert와 비슷하게
    상황에 따라 int를 사용하기도 하고 void를 사용한다.

    몇 개의 데이터를 조회(검색) 했는지 확인하고자 할 때 int를 주로 사용
    몇 개의 데이터를 수정, 삭제했는지 확인하고자 할 때 int를 주로 사용
    단순 조회, 수정, 삭제의 경우 void나 User와 같은 자료형을 활용하기도 함
    개발자가 원하는 결과 상황에 따라 자료형은 void, int User와 같은 자료형 사용
     */
}
