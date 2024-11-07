package com.example.cp01.Repository;

import com.example.cp01.Entidade.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
