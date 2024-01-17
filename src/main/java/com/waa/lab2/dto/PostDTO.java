package com.waa.lab2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {
    long id;
    String title;
    String content;
    String author;
}
