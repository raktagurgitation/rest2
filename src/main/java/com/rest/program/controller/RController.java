package com.rest.program.controller;

import com.rest.program.dto.AnimalDTO;
import com.rest.program.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class RController {

    private final AnimalService animalService;

    @PostMapping("/api/animal")
    public ObjectNode sayHello(@RequestBody AnimalDTO animalDTO) {
        return animalService.save(animalDTO);
    }

    @GetMapping("/api/animal/all")
    public List<AnimalDTO> getAllBases(){
        return animalService.findAll();
    }

    @GetMapping("/api/animal/generate")
    public ObjectNode generateAnimal(){
        return animalService.generateAnimal();
    }

    @GetMapping("/api/animal")
    public ObjectNode getBaseById(@RequestParam("id") int id){
        return animalService.findById(id);
    }

    @PatchMapping("/api/animal")
    public ObjectNode updateBase(@RequestParam("id") int id,
                                @RequestBody AnimalDTO animalDTO){
        return animalService.update(animalDTO,id);
    }

    @DeleteMapping("/api/animal")
    public void deleteById(@RequestParam("id") int id){
       animalService.deleteById(id);
    }
}
