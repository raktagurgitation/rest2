package com.rest.program.service;

import com.rest.program.dto.AnimalDTO;
import com.rest.program.entity.Animal;
import com.rest.program.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    private Animal convertToBase(AnimalDTO animalDTO) {
        return Animal.builder()
                .type(animalDTO.getType())
                .name(animalDTO.getName())
                .age(animalDTO.getAge())
                .build();
    }
    private AnimalDTO convertToDTO(Animal animal) {
        return AnimalDTO.builder()
                .type(animal.getType())
                .name(animal.getName())
                .age(animal.getAge())
                .build();
    }

    public AnimalDTO save(AnimalDTO animalDTO) {
        return convertToDTO(animalRepository.save(convertToBase(animalDTO)));
    }

    public AnimalDTO findById(int id) {

        Animal animal = animalRepository.findById(id).orElse(null);

        return convertToDTO(animal);
    }

    public List<AnimalDTO> findAll() {
        return animalRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public AnimalDTO update(AnimalDTO animalDTO, int id) {
        Animal animal = convertToBase(animalDTO);
        animal.setId(id);
        return convertToDTO(animalRepository.save(animal));
    }

    public void deleteById(int id) {
        animalRepository.deleteById(id);
    }


}
