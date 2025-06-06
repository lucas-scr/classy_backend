package com.Classy.entitys;


import jakarta.persistence.*;

@Entity
@Table(name = "permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permissao", nullable = false)
    private String permissao;

    public Long getId() {
        return id;
    }

    public String getPermissao() {
        return permissao;
    }
}
