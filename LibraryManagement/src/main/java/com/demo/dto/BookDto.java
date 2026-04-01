package com.demo.dto;

import lombok.Data;

@Data
public class BookDto {

	private String name;
    private Double price;
    private Integer quantity;
    private String rating;
    private Integer authorId;
}
