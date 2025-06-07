package com.Classy.entitys;


import com.Classy.util.EnumPermissoes;
import jakarta.persistence.*;

@Entity
@Table(name = "permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permissao", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumPermissoes permissao;


    public Long getId() {
        return id;
    }

    public EnumPermissoes getPermissao() {
        return permissao;
    }

    public void setPermissao(EnumPermissoes permissao) {
        this.permissao = permissao;
    }
}
