package com.example.cp01.Controller;

import com.example.cp01.DTO.SintomaDTO;
import com.example.cp01.Service.SintomaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sintoma")
public class SintomaController {

    @Autowired
    private SintomaService sintomaService;

    @PostMapping
    public ResponseEntity<SintomaDTO> create(@Valid @RequestBody SintomaDTO sintomaDTO) {
        SintomaDTO createdSintoma = sintomaService.criarSintoma(sintomaDTO);
        return new ResponseEntity<>(createdSintoma, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SintomaDTO> get(@PathVariable Long id) {
        SintomaDTO sintoma = sintomaService.buscarPorId(id);
        return ResponseEntity.ok(sintoma);
    }

    @GetMapping
    public ResponseEntity<Page<SintomaDTO>> list(Pageable pageable) {
        Page<SintomaDTO> sintomas = sintomaService.listarSintomas(pageable);
        return ResponseEntity.ok(sintomas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SintomaDTO> update(@PathVariable Long id, @Valid @RequestBody SintomaDTO sintomaDTO) {
        SintomaDTO updatedSintoma = sintomaService.atualizarSintoma(id, sintomaDTO);
        return ResponseEntity.ok(updatedSintoma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sintomaService.deletarSintoma(id);
        return ResponseEntity.noContent().build();
    }
}
