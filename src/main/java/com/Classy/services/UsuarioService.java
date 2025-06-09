package com.Classy.services;

import com.Classy.DTO.UsuarioDTO;
import com.Classy.entitys.Permissao;
import com.Classy.entitys.Usuario;
import com.Classy.mappers.UsuarioMapper;
import com.Classy.repositorys.PermissaoRepository;
import com.Classy.repositorys.UsuarioRepository;
import com.Classy.util.EnumPermissoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

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

    public UserDetails carregarUsuarioPorEmail(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + email));

        String senha = usuario.getSenha() == null ? "": usuario.getSenha();
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                senha,
                getAuthorizes(usuario)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return carregarUsuarioPorEmail(username);
    }

    private Collection<? extends GrantedAuthority> getAuthorizes(Usuario usuario){
        return usuario.getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getPermissao().name()))
                .collect(Collectors.toList());
    }

}
