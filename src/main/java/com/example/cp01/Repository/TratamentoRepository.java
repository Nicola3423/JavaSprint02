package com.example.cp01.Repository;

import com.example.cp01.Entidade.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
    List<Tratamento> findByPacienteId(Long pacienteId);
}
