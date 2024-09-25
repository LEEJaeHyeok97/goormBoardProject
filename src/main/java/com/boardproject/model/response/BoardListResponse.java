package com.boardproject.model.response;

import com.boardproject.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResponse {

    private Long id;
    private String title;

    public static BoardListResponse from(Board board) {
        return new BoardListResponse(
                board.getId(),
                board.getTitle()
        );
    }

}
