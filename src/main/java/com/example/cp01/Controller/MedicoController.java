package com.example.cp01.Controller;

import com.example.cp01.DTO.MedicoDTO;
import com.example.cp01.Service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@Valid @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO createdMedico = medicoService.criarMedico(medicoDTO);
        return new ResponseEntity<>(createdMedico, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> get(@PathVariable Long id) {
        MedicoDTO medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> list(Pageable pageable) {
        Page<MedicoDTO> medicos = medicoService.listarMedicos(pageable);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @Valid @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO updatedMedico = medicoService.atualizarMedico(id, medicoDTO);
        return ResponseEntity.ok(updatedMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medico/{pacienteId}")
    public ResponseEntity<List<MedicoDTO>> buscarPorPacienteId(@PathVariable Long pacienteId) {
        List<MedicoDTO> medicos = medicoService.buscarPorPacienteId(pacienteId);
        return ResponseEntity.ok(medicos);
    }
}
