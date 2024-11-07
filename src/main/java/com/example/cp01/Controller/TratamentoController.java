package com.example.cp01.Controller;

import com.example.cp01.DTO.TratamentoDTO;
import com.example.cp01.Service.TratamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamento")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;

    @PostMapping
    public ResponseEntity<TratamentoDTO> create(@Valid @RequestBody TratamentoDTO tratamentoDTO) {
        TratamentoDTO createdTratamento = tratamentoService.criarTratamento(tratamentoDTO);
        return new ResponseEntity<>(createdTratamento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoDTO> get(@PathVariable Long id) {
        TratamentoDTO tratamento = tratamentoService.buscarPorId(id);
        return ResponseEntity.ok(tratamento);
    }

    @GetMapping
    public ResponseEntity<Page<TratamentoDTO>> list(Pageable pageable) {
        Page<TratamentoDTO> tratamentos = tratamentoService.listarTratamentos(pageable);
        return ResponseEntity.ok(tratamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamentoDTO> update(@PathVariable Long id, @Valid @RequestBody TratamentoDTO tratamentoDTO) {
        TratamentoDTO updatedTratamento = tratamentoService.atualizarTratamento(id, tratamentoDTO);
        return ResponseEntity.ok(updatedTratamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tratamentoService.deletarTratamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TratamentoDTO>> buscarPorPacienteId(@PathVariable Long pacienteId) {
        List<TratamentoDTO> tratamentos = tratamentoService.buscarPorPacienteId(pacienteId);
        return ResponseEntity.ok(tratamentos);
    }
}
