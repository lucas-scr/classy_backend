package com.Classy.mappers;

import com.Classy.DTO.UsuarioDTO;
import com.Classy.entitys.Permissao;
import com.Classy.entitys.Usuario;

public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getSenha());
        usuarioDTO.setProvedor(usuario.getProvedor());
      //  usuario.getPermissoes().forEach(usuarioDTO::adicionarPermissao);
        return usuarioDTO;
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSenha(usuarioDTO.getSenha());
       // usuario.setPermissoes(usuarioDTO.getPermissoes());
        usuario.setProvedor(usuarioDTO.getProvedor());
        return usuario;
    }
}
