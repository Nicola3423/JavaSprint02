package com.example.cp01.Entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull
    private Paciente paciente;

    @NotNull
    private String nome;

    @NotNull
    @Size(max = 15, message = "O telefone não pode exceder 15 caracteres.")
    private String telefone;

    @NotNull
    @Email(message = "Email deve ser válido.")
    private String email;

    @NotNull
    @Size(min = 5, max = 15, message = "CRM deve ter entre 5 e 15 caracteres.")
    private String crm;

    public Medico(Long id,Paciente paciente, String nome, String telefone, String email, String crm) {
        this.id = id;
        this.paciente = paciente;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.crm = crm;
    }

    public Medico() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
}
