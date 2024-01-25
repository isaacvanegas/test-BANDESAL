package com.igroup.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blogs", schema = "blog")
public class Blog {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

}