package com.example.cp01.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class TratamentoDTO {

    private Long id;

    @NotNull(message = "O paciente não pode ser nulo.")
    private Long pacienteId; // Adicionando pacienteId

    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;

    @NotBlank(message = "O tipo não pode ser vazio.")
    private String tipo;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    public TratamentoDTO(Long id, Long pacienteId, LocalDate data, String tipo, String descricao) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public TratamentoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
