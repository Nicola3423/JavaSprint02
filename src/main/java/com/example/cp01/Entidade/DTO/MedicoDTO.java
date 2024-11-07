package com.example.cp01.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicoDTO {

    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O telefone não pode ser vazio.")
    private String telefone;

    @NotBlank(message = "O e-mail não pode ser vazio.")
    @Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotBlank(message = "O CRM não pode ser vazio.")
    private String crm;

    @NotNull(message = "O ID do paciente não pode ser nulo.")
    private Long pacienteId;

    public MedicoDTO(Long id, String nome, String telefone, String email, String crm, Long pacienteId) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.crm = crm;
        this.pacienteId = pacienteId;
    }

    public MedicoDTO() {}

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
