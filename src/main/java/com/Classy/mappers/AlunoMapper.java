package com.Classy.mappers;

import com.Classy.DTO.AlunoDTO;
import com.Classy.entitys.Aluno;
import org.checkerframework.checker.units.qual.A;

public class AlunoMapper {


    public static AlunoDTO toDTO (Aluno aluno){
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setNome(aluno.getNome());
        return dto;
    }


    public static Aluno toEntity (AlunoDTO dto){
        Aluno aluno = new Aluno();
        if(dto.getId() != null){
            aluno.setId(dto.getId());
        }
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());
        return aluno;
    }
}
