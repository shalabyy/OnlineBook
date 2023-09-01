package com.task.OnlineBook.dto;

import lombok.Data;

@Data
public class Bookdto {

    private Integer id;
    private String title;
    private String author;
    private String category;
    private boolean stock;

    public Bookdto() {
    }

    public Bookdto(Integer id, String title, String author, String category, boolean stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }
}
