package com.example.cp01.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class PacienteDTO extends RepresentationModel<PacienteDTO> {

    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O e-mail não pode ser vazio.")
    @Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotBlank(message = "O telefone não pode ser vazio.")
    private String telefone;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    private LocalDate dataNascimento;

    public PacienteDTO(Long id, String nome, String email, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public PacienteDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
