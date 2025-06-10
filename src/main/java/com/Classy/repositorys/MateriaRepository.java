package com.Classy.repositorys;

import com.Classy.entitys.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    boolean existsByNome(String nome);
}
