package com.example.cp01.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FeedbackDTO {

    private Long id;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    @NotBlank(message = "O comentário não pode ser vazio.")
    private String comentario;

    @NotNull(message = "A nota não pode ser nula.")
    @Min(value = 0, message = "A nota deve ser pelo menos 0.")
    @Max(value = 10, message = "A nota não pode ser maior que 10.")
    private int nota;

    @NotNull(message = "O ID do paciente não pode ser nulo.")
    private Long pacienteId;

    public FeedbackDTO() {}

    public FeedbackDTO(Long id, LocalDate data, String comentario, int nota, Long pacienteId) {
        this.id = id;
        this.data = data;
        this.comentario = comentario;
        this.nota = nota;
        this.pacienteId = pacienteId;
    }

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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
