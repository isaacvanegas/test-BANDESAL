package com.igroup.blog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "blogs_readers", schema = "blog")
public class BlogsReader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "readers_id")
    private Reader readers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogs_id")
    private Blog blogs;

}