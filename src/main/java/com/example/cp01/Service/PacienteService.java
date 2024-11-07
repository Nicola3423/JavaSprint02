package com.example.cp01.Service;

import com.example.cp01.DTO.PacienteDTO;
import com.example.cp01.Entidade.Paciente;
import com.example.cp01.Exception.PacienteNotFoundException;
import com.example.cp01.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = convertToEntity(pacienteDTO);
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return convertToDTO(savedPaciente);
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));
        return convertToDTO(paciente);
    }

    public Page<PacienteDTO> listarPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Transactional
    public PacienteDTO atualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));

        paciente.setNome(pacienteDTO.getNome());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setDataNascimento(convertToDate(pacienteDTO.getDataNascimento()));

        Paciente updatedPaciente = pacienteRepository.save(paciente);
        return convertToDTO(updatedPaciente);
    }

    @Transactional
    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new PacienteNotFoundException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setEmail(paciente.getEmail());
        dto.setTelefone(paciente.getTelefone());
        dto.setDataNascimento(convertToLocalDate(paciente.getDataNascimento()));
        return dto;
    }

    private Paciente convertToEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());
        paciente.setDataNascimento(convertToDate(dto.getDataNascimento()));
        return paciente;
    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

