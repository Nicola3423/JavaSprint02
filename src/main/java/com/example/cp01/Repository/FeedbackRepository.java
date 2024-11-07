package com.example.cp01.Repository;

import com.example.cp01.Entidade.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByData(LocalDate data);
    List<Feedback> findByNota(int nota);

    List<Feedback> findByPaciente_Id(Long pacienteId);
}
