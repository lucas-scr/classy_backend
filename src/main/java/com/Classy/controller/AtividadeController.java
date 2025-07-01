package com.Classy.controller;

import com.Classy.DTO.AtividadeDTO;
import com.Classy.entitys.Atividade;
import com.Classy.mappers.AtividadeMapper;
import com.Classy.services.ServiceAtividade;
import com.Classy.util.ArquivoProcessador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    private ArquivoProcessador processadorArquivo;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postAtividade(
            @RequestPart("dados") @Valid AtividadeDTO atividadeDTO,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo) throws IOException {

        if(arquivo != null && !arquivo.isEmpty()){
            atividadeDTO.setArquivo(processadorArquivo.converterMultipartParaBytes(arquivo));
            atividadeDTO.setExtensao(processadorArquivo.extrairExtensao(arquivo.getContentType()));
        }
        AtividadeDTO atividadeSalva = serviceAtividade.cadastrarAtividade(atividadeDTO);
        URI location = URI.create("/api/atividades/" + atividadeSalva.getId());
        return ResponseEntity.created(location).body("{\"mensagem\": \"Atividade salva com sucesso.\"}");
    }

    @GetMapping
    public List<AtividadeDTO> getAtividades(){
        return serviceAtividade.findAll();
    }

    @GetMapping("/{id}")
    public AtividadeDTO getAtividade(@PathVariable Long id){
        return serviceAtividade.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAtividade(@PathVariable Long id){

        try{
            serviceAtividade.deletarAtividade(id);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar atividade" + e.getMessage());
        }
        return ResponseEntity.ok("{\"mensagem\": \"Atividade removida com sucesso.\"}");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAtividade(
            @PathVariable Long id,
            @RequestPart("dados") @Valid AtividadeDTO atividadeDTO,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo) throws IOException {

        if(arquivo != null && !arquivo.isEmpty()){
            atividadeDTO.setArquivo(processadorArquivo.converterMultipartParaBytes(arquivo));
            atividadeDTO.setExtensao(processadorArquivo.extrairExtensao(arquivo.getContentType()));
        }

        serviceAtividade.atualizarAtividade(id, atividadeDTO);
            return ResponseEntity.ok("{\"mensagem\": \"Atividade atualizada com sucesso!\"}");
    }

    @GetMapping("/{id}/arquivo")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable Long id){
        Atividade atividade = AtividadeMapper.toEntity(serviceAtividade.findById(id));
        System.out.println(processadorArquivo.gerarContentType(atividade.getExtensao()));
        System.out.println("Arquivo null? " + (atividade.getArquivo() == null));
        System.out.println("Tamanho do arquivo: " + (atividade.getArquivo() != null ? atividade.getArquivo().length : "nulo"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(processadorArquivo.gerarContentType(atividade.getExtensao())))
                .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\"" + atividade.getCodigo() + "\"")
                .body(atividade.getArquivo());
    }
}
