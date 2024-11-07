package com.example.cp01.Service;

import com.example.cp01.DTO.TratamentoDTO;
import com.example.cp01.Entidade.Tratamento;
import com.example.cp01.Entidade.Paciente;
import com.example.cp01.Exception.PacienteNotFoundException;
import com.example.cp01.Exception.TratamentoNotFoundException; // Importa a exceção
import com.example.cp01.Repository.TratamentoRepository;
import com.example.cp01.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public TratamentoDTO criarTratamento(TratamentoDTO tratamentoDTO) {
        Paciente paciente = pacienteRepository.findById(tratamentoDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));

        Tratamento tratamento = new Tratamento();
        tratamento.setDescricao(tratamentoDTO.getDescricao());
        tratamento.setTipo(tratamentoDTO.getTipo());
        tratamento.setData(tratamentoDTO.getData());
        tratamento.setPaciente(paciente);

        Tratamento savedTratamento = tratamentoRepository.save(tratamento);
        return convertToDTO(savedTratamento);
    }

    public TratamentoDTO buscarPorId(Long id) {
        Tratamento tratamento = tratamentoRepository.findById(id)
                .orElseThrow(() -> new TratamentoNotFoundException("Tratamento não encontrado"));
        return convertToDTO(tratamento);
    }

    public Page<TratamentoDTO> listarTratamentos(Pageable pageable) {
        return tratamentoRepository.findAll(pageable).map(this::convertToDTO);
    }

    public TratamentoDTO atualizarTratamento(Long id, TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = tratamentoRepository.findById(id)
                .orElseThrow(() -> new TratamentoNotFoundException("Tratamento não encontrado"));

        Paciente paciente = pacienteRepository.findById(tratamentoDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));

        tratamento.setDescricao(tratamentoDTO.getDescricao());
        tratamento.setTipo(tratamentoDTO.getTipo());
        tratamento.setData(tratamentoDTO.getData());
        tratamento.setPaciente(paciente);

        Tratamento updatedTratamento = tratamentoRepository.save(tratamento);
        return convertToDTO(updatedTratamento);
    }

    public void deletarTratamento(Long id) {
        if (!tratamentoRepository.existsById(id)) {
            throw new TratamentoNotFoundException("Tratamento não encontrado");
        }
        tratamentoRepository.deleteById(id);
    }

    private TratamentoDTO convertToDTO(Tratamento tratamento) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(tratamento.getId());
        dto.setData(tratamento.getData());
        dto.setTipo(tratamento.getTipo());
        dto.setDescricao(tratamento.getDescricao());
        dto.setPacienteId(tratamento.getPaciente().getId());
        return dto;
    }

    public List<TratamentoDTO> buscarPorPacienteId(Long pacienteId) {
        List<Tratamento> tratamentos = tratamentoRepository.findByPacienteId(pacienteId);
        return tratamentos.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
