package com.example.humanressapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="interviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interview {
    @Id
    @GeneratedValue
    private long id;
    private boolean result;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "candidate_id",referencedColumnName = "id")
    private Candidate candidate;
    // bir adayın birden fazla mülakatı olabilir.

    @ManyToOne
    @JoinColumn(name = "step_id")
    private Step step;









}
