package com.boardproject.model.response;

import com.boardproject.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private String body;

    //정적 팩토리 메소드
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getBody());
    }
}
