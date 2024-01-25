package com.igroup.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "usuario", schema = "blog")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "password", nullable = false)
    private String password;

}
