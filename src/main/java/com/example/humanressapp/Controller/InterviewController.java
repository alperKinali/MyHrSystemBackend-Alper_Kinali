package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.InterviewCreateDto;
import com.example.humanressapp.model.Interview;
import com.example.humanressapp.service.abstracts.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class InterviewController {
    private InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }


    @PostMapping("/createInterview")
    public Interview createInterview(@RequestBody  InterviewCreateDto dto){
        return this.interviewService.createInterview(dto);
    }
    @PutMapping("/{interviewId}/succesStep")
    public void successStep(@PathVariable  long interviewId, @RequestParam String notes){
        this.interviewService.successStep(interviewId,notes);
    }
    @PutMapping("/{interviewId}/faillStep")
    public void faillStep(@PathVariable  long interviewId, @RequestParam String notes){
        this.interviewService.faillStep(interviewId,notes);
    }

}
