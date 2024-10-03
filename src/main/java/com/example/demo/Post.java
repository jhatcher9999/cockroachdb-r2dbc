package com.example.demo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")
class Post {

    @Id
    @Column("id")
    private BigInteger id;

    @Column("title")
    private String title;

    @Column("content")
    private String content;

}