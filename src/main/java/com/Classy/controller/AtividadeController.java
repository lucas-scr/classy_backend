package com.Classy.controller;

import com.Classy.DTO.AtividadeDTO;
import com.Classy.entitys.Atividade;
import com.Classy.mappers.AtividadeMapper;
import com.Classy.services.ServiceAtividade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/atividades")
@CrossOrigin(origins = "http://localhost:4200")
public class AtividadeController {

    @Autowired
    private ServiceAtividade serviceAtividade;

    @PostMapping
    public ResponseEntity<AtividadeDTO> postAtividade(@Valid @RequestBody AtividadeDTO atividadeDTO){
        AtividadeDTO atividadeSalva = serviceAtividade.cadastrarAtividade(atividadeDTO);
        URI location = URI.create("/api/atividades/" + atividadeSalva.getId());
        return ResponseEntity.created(location).body(atividadeSalva);
    }

    @GetMapping
    public List<AtividadeDTO> getAtividades(){
        return serviceAtividade.findAll();
    }

    @GetMapping("/{id}")
    public AtividadeDTO getAtividade(@PathVariable Long id){
        return serviceAtividade.findById(id);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Map<String, String>> deleteAtividade(@PathVariable Long id){
        serviceAtividade.deletarAtividade(id);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("Mensagem","Atividade removida com sucesso.");
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeDTO> updateAtividade(@PathVariable Long id, @Valid @RequestBody AtividadeDTO atividadeDTO){
        AtividadeDTO atividadeAtualizada = serviceAtividade.atualizarAtividade(id, atividadeDTO);
        return ResponseEntity.ok(atividadeAtualizada);
    }

}
