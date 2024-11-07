package com.example.cp01.Repository;

import com.example.cp01.Entidade.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByPacienteId(Long pacienteId);
}
