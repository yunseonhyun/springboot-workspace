package edu.thejoeun.board.model.service;

import edu.thejoeun.board.model.dto.Board;
import edu.thejoeun.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    // @Autowired
    // Autowired 보다 RequiredArgsConstructor 처리해주는 것이
    // 상수화 하여 Mapper를 사용할 수 있으므로 안전 -> 내부 메서드나 데이터 변경 불가

    private final BoardMapper boardMapper;
    @Override
    public List<Board> getAllBoard(){
        return boardMapper.getAllBoard();
    }

    @Override
    public Board getBoardById(int id){
        Board b = boardMapper.getBoardById(id);
        // 게시물 상세조회를 위해 id를 입력하고, 입력한 id에 해당하는 게시물이
        // 존재할 경우에는 조회된 데이터 전달
        // 존재하지 않을 겨웅에는 null 전달
        return b != null ? b : null;
    }
}
