package com.rest.program.dto;

import com.rest.program.utils.AnimalTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    public AnimalTypeEnum type;
    public String name;
    public int age;

}
