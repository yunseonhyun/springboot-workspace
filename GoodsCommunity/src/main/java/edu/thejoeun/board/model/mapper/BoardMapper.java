package edu.thejoeun.board.model.mapper;

import edu.thejoeun.board.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

        // mapper.xml 에 작성한
        // id와 메서드명칭은 일치
    List<Board> getAllBoard();

    Board getBoardById(int id);

}
