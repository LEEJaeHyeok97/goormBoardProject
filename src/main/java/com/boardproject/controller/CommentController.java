package com.boardproject.controller;

import com.boardproject.model.request.CommentPostRequest;
import com.boardproject.model.response.BoardResponse;
import com.boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // 댓글 등록
    @PostMapping("/comment")
    public BoardResponse writeComment(
            @RequestBody CommentPostRequest commentPostRequest
    ) {
        return commentService.writeComment(commentPostRequest);
    }
}
