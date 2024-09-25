package com.boardproject.model.request;

import lombok.Data;

@Data
public class CommentPostRequest {

    private Long boardId;
    private String commentBody;

}
