package com.Classy.controller;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contrato;
import com.Classy.services.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    // Cadastrar contrato
    @PostMapping
    public ResponseEntity<ContratoDTO> cadastrarContrato(@Valid @RequestBody ContratoDTO contrato){
        ContratoDTO contratoSalvo = contratoService.cadastrarContrato(contrato);
        URI location = URI.create("/contratos/" + contratoSalvo.getId());
        return ResponseEntity.created().body(contrato);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> buscarContrato(){
        List<Contrato> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    // Consultar contrato por id
    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> buscarContrato(@PathVariable Long id){
        return contratoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Atualizar contrato
    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> atualizarContrato(@PathVariable Long id, @Valid @RequestBody ContratoDTO contratoAtualizado){
        return contratoService.atualizarContrato(id, contratoAtualizado).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Deletar contrato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id){
      if(contratoService.deletarContrato(id)){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
    }



}
