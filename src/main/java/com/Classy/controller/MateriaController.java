package com.Classy.controller;

import com.Classy.DTO.MateriaDTO;
import com.Classy.services.ServiceMateria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaController {

    @Autowired
    private ServiceMateria serviceMateria;

    @PostMapping
    public ResponseEntity<MateriaDTO> postMateria(@Valid @RequestBody MateriaDTO materiaDTO){
       MateriaDTO materiaSalva = serviceMateria.cadastrarMateria(materiaDTO);
        URI location = URI.create("/api/materias/" + materiaSalva.getId());
        return ResponseEntity.created(location).body(materiaSalva);
    }

    @GetMapping
    public List<MateriaDTO> getMateria(){
        return serviceMateria.findAll();
    }

    @GetMapping("/{id}")
    public MateriaDTO getMateria(@PathVariable Long id){
        return serviceMateria.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMateria(@PathVariable long id){
        serviceMateria.deletarMateria(id);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Matéria deletada com sucesso.");

        return ResponseEntity.ok(resposta);
    }

}
