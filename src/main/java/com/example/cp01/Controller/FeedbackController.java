package com.example.cp01.Controller;

import com.example.cp01.DTO.FeedbackDTO;
import com.example.cp01.Service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> create(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO createdFeedback = feedbackService.criarFeedback(feedbackDTO);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> get(@PathVariable Long id) {
        FeedbackDTO feedback = feedbackService.buscarPorId(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping
    public ResponseEntity<Page<FeedbackDTO>> list(Pageable pageable) {
        Page<FeedbackDTO> feedbacks = feedbackService.listarFeedbacks(pageable);
        return ResponseEntity.ok(feedbacks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> update(@PathVariable Long id, @Valid @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO updatedFeedback = feedbackService.atualizarFeedback(id, feedbackDTO);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.deletarFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<FeedbackDTO>> buscarPorPacienteId(@PathVariable Long pacienteId) {
        List<FeedbackDTO> feedbacks = feedbackService.buscarPorPacienteId(pacienteId);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/data")
    public ResponseEntity<List<FeedbackDTO>> buscarPorData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<FeedbackDTO> feedbacks = feedbackService.buscarPorData(data);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/nota")
    public ResponseEntity<List<FeedbackDTO>> buscarPorNota(@RequestParam int nota) {
        List<FeedbackDTO> feedbacks = feedbackService.buscarPorNota(nota);
        return ResponseEntity.ok(feedbacks);
    }
}
