package com.example.cp01.Service;

import com.example.cp01.DTO.SintomaDTO;
import com.example.cp01.Entidade.Sintoma;
import com.example.cp01.Entidade.Paciente;
import com.example.cp01.Exception.PacienteNotFoundException;
import com.example.cp01.Exception.SintomaNotFoundException; // Importa a exceção
import com.example.cp01.Repository.SintomaRepository;
import com.example.cp01.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SintomaService {

    @Autowired
    private SintomaRepository sintomaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public SintomaDTO criarSintoma(SintomaDTO sintomaDTO) {
        Sintoma sintoma = new Sintoma();

        Paciente paciente = pacienteRepository.findById(sintomaDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));

        sintoma.setDescricao(sintomaDTO.getDescricao());
        sintoma.setGravidade(sintomaDTO.getGravidade());
        sintoma.setData(convertToDate(sintomaDTO.getData()));
        sintoma.setPaciente(paciente);

        Sintoma savedSintoma = sintomaRepository.save(sintoma);
        sintomaDTO.setId(savedSintoma.getId());
        return convertToDTO(savedSintoma);
    }

    public SintomaDTO buscarPorId(Long id) {
        Sintoma sintoma = sintomaRepository.findById(id)
                .orElseThrow(() -> new SintomaNotFoundException("Sintoma não encontrado"));
        return convertToDTO(sintoma);
    }

    public Page<SintomaDTO> listarSintomas(Pageable pageable) {
        return sintomaRepository.findAll(pageable).map(this::convertToDTO);
    }

    public SintomaDTO atualizarSintoma(Long id, SintomaDTO sintomaDTO) {
        Sintoma sintoma = sintomaRepository.findById(id)
                .orElseThrow(() -> new SintomaNotFoundException("Sintoma não encontrado"));

        Paciente paciente = pacienteRepository.findById(sintomaDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));
        sintoma.setDescricao(sintomaDTO.getDescricao());
        sintoma.setGravidade(sintomaDTO.getGravidade());
        sintoma.setData(convertToDate(sintomaDTO.getData()));
        sintoma.setPaciente(paciente);

        Sintoma updatedSintoma = sintomaRepository.save(sintoma);
        return convertToDTO(updatedSintoma);
    }

    public void deletarSintoma(Long id) {
        if (!sintomaRepository.existsById(id)) {
            throw new SintomaNotFoundException("Sintoma não encontrado");
        }
        sintomaRepository.deleteById(id);
    }

    private SintomaDTO convertToDTO(Sintoma sintoma) {
        SintomaDTO dto = new SintomaDTO();
        dto.setId(sintoma.getId());
        dto.setData(convertToLocalDate(sintoma.getData()));
        dto.setDescricao(sintoma.getDescricao());
        dto.setGravidade(sintoma.getGravidade());
        dto.setPacienteId(sintoma.getPaciente().getId());
        return dto;
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
