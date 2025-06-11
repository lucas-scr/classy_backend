package com.Classy.controller;

import com.Classy.DTO.AtividadeDTO;
import com.Classy.entitys.Atividade;
import com.Classy.mappers.AtividadeMapper;
import com.Classy.services.ServiceAtividade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postAtividade(
            @RequestPart("dados") @Valid @RequestBody AtividadeDTO atividadeDTO,
            @RequestPart("arquivo") MultipartFile arquivo){


        try {
            byte[] arquivoEmBytes = null;
            if (arquivo != null && arquivo.isEmpty()){
                arquivoEmBytes = arquivo.getBytes();
                atividadeDTO.setArquivo(arquivoEmBytes);
            }
            AtividadeDTO atividadeSalva = serviceAtividade.cadastrarAtividade(atividadeDTO);
            URI location = URI.create("/api/atividades/" + atividadeSalva.getId());
            return ResponseEntity.created(location).body("Atividade salva com sucesso.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao proceso o arquivo.");
        }

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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAtividade(
            @PathVariable Long id,
            @RequestPart("dados") @Valid @RequestBody AtividadeDTO atividadeDTO,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo){


        try {
            byte[] arquivoEmBytes = null;
            if (arquivo != null && arquivo.isEmpty()){
                arquivoEmBytes = arquivo.getBytes();
                atividadeDTO.setArquivo(arquivoEmBytes);
            }
            AtividadeDTO atividadeAtualizada = serviceAtividade.atualizarAtividade(id, atividadeDTO);
            return ResponseEntity.ok("Atividade atualizada com sucesso!");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao proceso o arquivo.");
        }
    }

}
