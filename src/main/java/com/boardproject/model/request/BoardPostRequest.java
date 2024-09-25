package com.boardproject.model.request;

import lombok.Data;

@Data
public class BoardPostRequest {
    private String title;
    private String body;
}
