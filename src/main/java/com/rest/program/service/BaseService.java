package com.rest.program.service;

import com.rest.program.dto.BaseDTO;
import com.rest.program.entity.Base;
import com.rest.program.repository.BaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BaseService {

    private final BaseRepository baseRepository;

    private Base convertToBase(BaseDTO baseDTO) {
        return Base.builder()
                .type(baseDTO.getType())
                .name(baseDTO.getName())
                .age(baseDTO.getAge())
                .build();
    }
    private BaseDTO convertToDTO(Base base) {
        return BaseDTO.builder()
                .type(base.getType())
                .name(base.getName())
                .age(base.getAge())
                .build();
    }

    public BaseDTO save(BaseDTO baseDTO) {
        return convertToDTO(baseRepository.save(convertToBase(baseDTO)));
    }

    public BaseDTO findById(UUID uuid) {

        Base base = baseRepository.findById(uuid).orElse(null);

        return convertToDTO(base);
    }

    public List<BaseDTO> findAll() {
        return baseRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public BaseDTO update(BaseDTO baseDTO, UUID id) {
        Base base = convertToBase(baseDTO);
        base.setId(id);
        return convertToDTO(baseRepository.save(base));
    }

    public void deleteById(UUID id) {
        baseRepository.deleteById(id);
    }


}
