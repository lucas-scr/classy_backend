package com.Classy.services;

import com.Classy.DTO.UsuarioDTO;
import com.Classy.entitys.Contrato;
import com.Classy.entitys.Permissao;
import com.Classy.entitys.Usuario;
import com.Classy.mappers.UsuarioMapper;
import com.Classy.repositorys.PermissaoRepository;
import com.Classy.repositorys.UsuarioRepository;
import com.Classy.util.EnumPermissoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PermissaoRepository permissaoRepository;


    public UsuarioDTO cadastrarUsuarioGoogle(UsuarioDTO usuarioDto){

        Optional<Usuario> usuarioExistente = repository.findByEmail(usuarioDto.getEmail());

        if(usuarioExistente.isPresent()){
            return UsuarioMapper.toDTO(usuarioExistente.get());
        }

        Permissao permissao = permissaoRepository.findByPermissao(EnumPermissoes.ROLE_USER).orElseThrow(()-> new RuntimeException("Permissão não encontrada"));
        usuarioDto.adicionarPermissao(permissao);
        Usuario usuarioEntity = UsuarioMapper.toEntity(usuarioDto);
        return UsuarioMapper.toDTO(repository.save(usuarioEntity));
    }

    public Boolean verificarExistenciaUsuario(String email){
       return repository.existsByEmail(email);
    }
}
