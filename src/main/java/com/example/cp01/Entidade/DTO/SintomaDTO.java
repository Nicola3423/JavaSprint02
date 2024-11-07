package com.example.cp01.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate; // Alterado para LocalDate

public class SintomaDTO {

    private Long id;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;

    @NotBlank(message = "A gravidade não pode ser vazia.")
    private String gravidade;

    @NotNull(message = "O ID do paciente não pode ser nulo.")
    private Long pacienteId;

    public SintomaDTO(Long id, LocalDate data, String descricao, String gravidade, Long pacienteId) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.gravidade = gravidade;
        this.pacienteId = pacienteId;
    }

    public SintomaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
