package com.example.cp01.Service;

import com.example.cp01.DTO.FeedbackDTO;
import com.example.cp01.Entidade.Feedback;
import com.example.cp01.Entidade.Paciente;
import com.example.cp01.Exception.FeedbackNotFoundException;
import com.example.cp01.Repository.FeedbackRepository;
import com.example.cp01.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public FeedbackDTO criarFeedback(FeedbackDTO feedbackDTO) {
        Paciente paciente = pacienteRepository.findById(feedbackDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Feedback feedback = new Feedback();
        feedback.setData(feedbackDTO.getData());
        feedback.setComentario(feedbackDTO.getComentario());
        feedback.setNota(feedbackDTO.getNota());
        feedback.setPaciente(paciente);

        Feedback savedFeedback = feedbackRepository.save(feedback);
        feedbackDTO.setId(savedFeedback.getId());
        return convertToDTO(savedFeedback);
    }

    public FeedbackDTO buscarPorId(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback não encontrado com ID: " + id));
        return convertToDTO(feedback);
    }

    public Page<FeedbackDTO> listarFeedbacks(Pageable pageable) {
        return feedbackRepository.findAll(pageable).map(this::convertToDTO);
    }

    public FeedbackDTO atualizarFeedback(Long id, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback não encontrado com ID: " + id));

        feedback.setData(feedbackDTO.getData());
        feedback.setComentario(feedbackDTO.getComentario());
        feedback.setNota(feedbackDTO.getNota());

        Paciente paciente = pacienteRepository.findById(feedbackDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        feedback.setPaciente(paciente);

        Feedback updatedFeedback = feedbackRepository.save(feedback);
        return convertToDTO(updatedFeedback);
    }

    public void deletarFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new FeedbackNotFoundException("Feedback não encontrado com ID: " + id);
        }
        feedbackRepository.deleteById(id);
    }

    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setData(feedback.getData());
        dto.setComentario(feedback.getComentario());
        dto.setNota(feedback.getNota());
        dto.setPacienteId(feedback.getPaciente().getId());
        return dto;
    }

    public List<FeedbackDTO> buscarPorPacienteId(Long pacienteId) {
        List<Feedback> feedbacks = feedbackRepository.findByPaciente_Id(pacienteId);
        return feedbacks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FeedbackDTO> buscarPorData(LocalDate data) {
        List<Feedback> feedbacks = feedbackRepository.findByData(data);
        return feedbacks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FeedbackDTO> buscarPorNota(int nota) {
        List<Feedback> feedbacks = feedbackRepository.findByNota(nota);
        return feedbacks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
