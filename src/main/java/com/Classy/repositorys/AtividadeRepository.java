package com.Classy.repositorys;

import com.Classy.entitys.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    boolean existsByCodigo(String codigo);
}
