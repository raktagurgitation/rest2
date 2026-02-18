package com.rest.program.controller;

import com.rest.program.dto.BaseDTO;
import com.rest.program.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class RController {

    private final BaseService baseService;

    @PostMapping("/api/base")
    public BaseDTO sayHello(@RequestBody BaseDTO baseDTO) {
        return baseService.save(baseDTO);
    }

    @GetMapping("/api/all_base")
    public List<BaseDTO> getAllBases(){
        return baseService.findAll();
    }

    @GetMapping("/api/base")
    public BaseDTO getBaseById(@RequestParam("id") UUID id){
        return baseService.findById(id);
    }

    @PatchMapping("/api/base")
    public BaseDTO updateBase(@RequestParam("id") UUID id,
                              @RequestBody BaseDTO baseDTO){
        return baseService.update(baseDTO,id);
    }

    @DeleteMapping("/api/base")
    public void deleteById(@RequestParam("id") UUID id){
       baseService.deleteById(id);
    }



}
