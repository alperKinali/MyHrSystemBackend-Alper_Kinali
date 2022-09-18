package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.StepCreateDto;
import com.example.humanressapp.model.Step;
import com.example.humanressapp.service.abstracts.StepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class StepController {

    private final StepService stepService;
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @PostMapping("/StepCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createStep(@RequestBody StepCreateDto dto){
        this.stepService.createStep(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public Step getById(@PathVariable long id){
        return this.stepService.getById(id);
    }
}
