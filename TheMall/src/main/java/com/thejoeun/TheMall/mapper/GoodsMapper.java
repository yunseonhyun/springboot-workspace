package com.thejoeun.TheMall.mapper;

import com.thejoeun.TheMall.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
Repository 의 경우
스프링부트 개발자 + 커뮤니티 개발자들이 만들어놓은 기능을
JPA에서 가져와 사용

Mapper의 경우
resource/mappers/ 폴더 아래에
각 회사 개발자들이 작성한 SQL을 기반으로 움직이는 기능명칭들 작성

    mapper.xml 파일에서
    id로 작성한 명칭과 class Mapper.java에서 작성하는 기능 명칭이
    일칯해야지 xml 파일에 작성한 SQL 구문이 동작

    <select id="getAllGoods" resultMap="goodsResultMap">
 */

@Mapper // @Repository랑 동일한 위치
public interface GoodsMapper {

    List<Goods> getAllGoods();
    void insertGoods(Goods goods);
}

