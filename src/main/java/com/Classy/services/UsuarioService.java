package com.Classy.services;

import com.Classy.DTO.UsuarioDTO;
import com.Classy.entitys.Contrato;
import com.Classy.entitys.Usuario;
import com.Classy.mappers.UsuarioMapper;
import com.Classy.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDto){
        Usuario usuarioEntity = UsuarioMapper.toEntity(usuarioDto);
        return UsuarioMapper.toDTO(repository.save(usuarioEntity));
    }

    public Boolean verificarExistenciaUsuario(String email){
       return repository.existsByEmail(email);
    }
}
