package com.boardproject.service;

import com.boardproject.model.DeleteStatus;
import com.boardproject.model.entity.Board;
import com.boardproject.model.request.BoardDeleteRequest;
import com.boardproject.model.request.BoardPostRequest;
import com.boardproject.model.response.BoardListResponse;
import com.boardproject.model.response.BoardResponse;
import com.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse writeBoard(BoardPostRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setBody(request.getBody());
        board.setDeleteStatus(DeleteStatus.ACTIVE);


        return BoardResponse.from(boardRepository.save(board));
    }

    public List<BoardListResponse> searchBoardList(int page, int pageSize) {
        return boardRepository.findAllByDeleteStatus(DeleteStatus.ACTIVE, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        ).map(BoardListResponse::from).toList();
    }

    public BoardResponse searchBoard(Long boardId) {
        return boardRepository.findBoardWithCommentsByBoardId(boardId)
                .map(BoardResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    @Transactional
    public String deleteBoard(BoardDeleteRequest request) {
        // 예외
        Optional<Board> boardOptional = boardRepository.findById(request.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시!"));

        boardRepository.delete(board);

        // 게시물 삭제 시 코멘트 삭제도 필요

        return "OK";
    }
}
