package com.example.cp01.Controller;

import com.example.cp01.DTO.PacienteDTO;
import com.example.cp01.Service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO createdPaciente = pacienteService.criarPaciente(pacienteDTO);
        createdPaciente.add(linkTo(methodOn(PacienteController.class).get(createdPaciente.getId())).withSelfRel());
        return new ResponseEntity<>(createdPaciente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> get(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.buscarPorId(id);
        paciente.add(linkTo(methodOn(PacienteController.class).get(id)).withSelfRel());
        paciente.add(linkTo(methodOn(PacienteController.class).list(Pageable.unpaged())).withRel("list"));
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> list(Pageable pageable) {
        Page<PacienteDTO> pacientes = pacienteService.listarPacientes(pageable);
        pacientes.forEach(paciente -> {
            paciente.add(linkTo(methodOn(PacienteController.class).get(paciente.getId())).withSelfRel());
        });
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO updatedPaciente = pacienteService.atualizarPaciente(id, pacienteDTO);
        updatedPaciente.add(linkTo(methodOn(PacienteController.class).get(updatedPaciente.getId())).withSelfRel());
        return ResponseEntity.ok(updatedPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent()
                .header("Link", linkTo(methodOn(PacienteController.class).list(Pageable.unpaged())).withRel("list").toString())
                .header("Link", linkTo(methodOn(PacienteController.class).create(null)).withRel("create").toString())
                .build();
    }
}
