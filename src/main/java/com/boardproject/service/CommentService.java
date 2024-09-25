package com.boardproject.service;

import com.boardproject.model.entity.Board;
import com.boardproject.model.request.CommentPostRequest;
import com.boardproject.model.response.BoardResponse;
import com.boardproject.repository.BoardRepository;
import com.boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    @Transactional
    public BoardResponse writeComment(CommentPostRequest request) {

        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardId(request.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다!"));

        //board에 댓글 추가
        board.addComment(request.getCommentBody());
        boardRepository.save(board);

        return BoardResponse.from(board);
    }
}
