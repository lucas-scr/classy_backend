package com.Classy.controller;

import com.Classy.DTO.ContratoDTO;
import com.Classy.services.ServiceContrato;
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
    private ServiceContrato contratoService;

    // Cadastrar contrato
    @PostMapping
    public ResponseEntity<String> cadastrarContrato(@Valid @RequestBody ContratoDTO contratoDto){
        ContratoDTO contratoSalvo = contratoService.cadastrarContrato(contratoDto);
        return ResponseEntity.ok("Contrato cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<ContratoDTO>> buscarContrato(){
        List<ContratoDTO> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    // Consultar contrato por id
    @GetMapping("/{id}")
    public ContratoDTO buscarContrato(@PathVariable Long id){
        return contratoService.buscarPorId(id);
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
