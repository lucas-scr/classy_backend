package com.Classy.DTO;

import com.Classy.entitys.Permissao;
import com.Classy.util.EnumPermissoes;
import com.Classy.util.ProvedorAutenticacao;

import java.util.List;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private ProvedorAutenticacao provedor;
    private List<Permissao> permissoes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ProvedorAutenticacao getProvedor() {
        return provedor;
    }

    public void setProvedor(ProvedorAutenticacao provedor) {
        this.provedor = provedor;
    }

    public void adicionarPermissao(Permissao permissao){
        this.permissoes.add(permissao);
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }
}
