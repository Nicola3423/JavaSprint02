package com.example.cp01.Service;

import com.example.cp01.DTO.MedicoDTO;
import com.example.cp01.Entidade.Medico;
import com.example.cp01.Entidade.Paciente;
import com.example.cp01.Exception.MedicoNotFoundException;
import com.example.cp01.Repository.MedicoRepository;
import com.example.cp01.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public MedicoDTO criarMedico(MedicoDTO medicoDTO) {
        Paciente paciente = pacienteRepository.findById(medicoDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = new Medico();
        medico.setNome(medicoDTO.getNome());
        medico.setTelefone(medicoDTO.getTelefone());
        medico.setEmail(medicoDTO.getEmail());
        medico.setCrm(medicoDTO.getCrm());
        medico.setPaciente(paciente);

        Medico savedMedico = medicoRepository.save(medico);
        medicoDTO.setId(savedMedico.getId());
        return convertToDTO(savedMedico);
    }

    public MedicoDTO buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado com ID: " + id));
        return convertToDTO(medico);
    }

    public Page<MedicoDTO> listarMedicos(Pageable pageable) {
        return medicoRepository.findAll(pageable).map(this::convertToDTO);
    }

    public MedicoDTO atualizarMedico(Long id, MedicoDTO medicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado com ID: " + id));

        Paciente paciente = pacienteRepository.findById(medicoDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        medico.setNome(medicoDTO.getNome());
        medico.setTelefone(medicoDTO.getTelefone());
        medico.setEmail(medicoDTO.getEmail());
        medico.setCrm(medicoDTO.getCrm());
        medico.setPaciente(paciente);

        Medico updatedMedico = medicoRepository.save(medico);
        return convertToDTO(updatedMedico);
    }

    public void deletarMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new MedicoNotFoundException("Médico não encontrado com ID: " + id);
        }
        medicoRepository.deleteById(id);
    }

    private MedicoDTO convertToDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNome(medico.getNome());
        dto.setTelefone(medico.getTelefone());
        dto.setEmail(medico.getEmail());
        dto.setCrm(medico.getCrm());
        dto.setPacienteId(medico.getPaciente().getId());
        return dto;
    }

    public List<MedicoDTO> buscarPorPacienteId(Long pacienteId) {
        List<Medico> medicos = medicoRepository.findByPacienteId(pacienteId);
        return medicos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
