package com.boardproject.controller;

import com.boardproject.model.request.BoardDeleteRequest;
import com.boardproject.model.request.BoardPostRequest;
import com.boardproject.model.response.BoardListResponse;
import com.boardproject.model.response.BoardResponse;
import com.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시물 등록
    @PostMapping("/board")
    public BoardResponse writeBoard(
            @RequestBody BoardPostRequest boardRequest
    ) {
        return boardService.writeBoard(boardRequest);
    }

    // 조회
    @GetMapping("/boards")
    public List<BoardListResponse> searchBoardList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ) {
        return boardService.searchBoardList(page, pageSize);
    }

    // 단건 조회
    @GetMapping("board")
    public BoardResponse searchBoard(
            @RequestParam("boardNo") Long boardId
    ) {
        return boardService.searchBoard(boardId);
    }

    // 게시글 삭제
    @DeleteMapping("/board")
    public String deleteBoard(
            @RequestBody BoardDeleteRequest boardDeleteRequest
    ) {
        return boardService.deleteBoard(boardDeleteRequest);
    }
}
