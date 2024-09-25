package com.boardproject.model.response;

import com.boardproject.model.DeleteStatus;
import com.boardproject.model.entity.Board;
import com.boardproject.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BoardResponse {

    private Long id;
    private String title;
    private String body;
    private DeleteStatus deleteStatus;
    private List<CommentResponse> commentResponseList;

    // 정적 팩토리 메소드
    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getBody(),
                board.getDeleteStatus(),
                board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList())
        );
    }
}
