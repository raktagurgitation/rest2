package com.rest.program.service;

import com.rest.program.dto.AnimalDTO;
import com.rest.program.dto.CatFactDTO;
import com.rest.program.dto.RandomNameDTO;
import com.rest.program.entity.Animal;
import com.rest.program.repository.AnimalRepository;
import com.rest.program.utils.AnimalTypeEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String catsFactURL = "https://catfact.ninja/fact?max_length=100";
    private final String randomNameURL = "https://api.randomdatatools.ru/?count=1&params=FirstName";

    private Animal convertToAnimal(AnimalDTO animalDTO) {
        return Animal.builder()
                .type(animalDTO.getType())
                .name(animalDTO.getName())
                .age(animalDTO.getAge())
                .build();
    }

    private AnimalDTO convertToAnimalDTO(Animal animal) {
        return AnimalDTO.builder()
                .type(animal.getType())
                .name(animal.getName())
                .age(animal.getAge())
                .build();
    }

    private ObjectNode getAnimalResponse(AnimalDTO animalDTO) {
        ObjectNode animalJson = objectMapper.valueToTree(animalDTO);
        CatFactDTO catFactDTO = restTemplate.getForObject(catsFactURL, CatFactDTO.class);
        log.info("GET from catsFactURL: {}", catsFactURL);
        animalJson.put("fact", catFactDTO.getFact());
        return animalJson;
    }
    private ObjectNode getAnimalResponse(AnimalDTO animalDTO, String message) {
        ObjectNode animalJson = objectMapper.valueToTree(animalDTO);
        animalJson.put("fact", message);
        return animalJson;
    }

    public ObjectNode save(AnimalDTO animalDTO) {
        animalRepository.save(convertToAnimal(animalDTO));
        return getAnimalResponse(animalDTO);
    }

    public ObjectNode generateAnimal() {
        int age = ThreadLocalRandom.current().nextInt(1, 20);
        AnimalTypeEnum animalType = AnimalTypeEnum.values()[ThreadLocalRandom.current().nextInt(AnimalTypeEnum.values().length)];

        String nameHTML = restTemplate.getForObject(randomNameURL, String.class);
        RandomNameDTO randomNameDTO = objectMapper.readValue(nameHTML, RandomNameDTO.class);
        AnimalDTO animalDTO = new AnimalDTO(animalType,randomNameDTO.getFirstName(), age);
        animalRepository.save(convertToAnimal(animalDTO));

        log.info("Take random name from {}", randomNameURL);

        if (animalType.equals(AnimalTypeEnum.CAT)) {
            return getAnimalResponse(animalDTO);
        }
        return getAnimalResponse(animalDTO, "No fact for this type of animal :(");
    }

    public ObjectNode findById(int id) {
        return getAnimalResponse(convertToAnimalDTO(animalRepository.findById(id).orElse(null)));
    }

    public List<AnimalDTO> findAll() {
        return animalRepository.findAll().stream().map(this::convertToAnimalDTO).toList();
    }

    public ObjectNode update(AnimalDTO animalDTO, int id) {
        Animal animal = convertToAnimal(animalDTO);
        animal.setId(id);
        animalRepository.save(animal);
        return getAnimalResponse(animalDTO);
    }

    public void deleteById(int id) {
        animalRepository.deleteById(id);
    }


}
