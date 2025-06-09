package com.Classy.repositorys;

import com.Classy.entitys.Permissao;
import com.Classy.util.EnumPermissoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    Optional<Permissao> findByPermissao(EnumPermissoes permissao);

}
