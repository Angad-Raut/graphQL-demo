package com.projectx.graphQL.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewBooksDto {
    private Integer srNo;
    private Long bookId;
    private String name;
    private Integer price;
    private String author;
    private String publication;
    private String bookStatus;
}
