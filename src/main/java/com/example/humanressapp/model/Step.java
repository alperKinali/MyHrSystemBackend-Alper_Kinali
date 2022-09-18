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
@Table(name="steps")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Step {
    @Id
    @GeneratedValue
    private long id;
    private String stepName;
    private int sOrder;


    @ManyToOne
    @JoinColumn(name = "jobadvet_id",referencedColumnName = "id")
    private JobAdvertisement jobAdvertisement;
    // bir İş ilanının birden fazla Mülakat adımı olabilir (İk mülakatı, live codding vb.)

    @OneToMany(mappedBy = "step")
    @JsonIgnore
    private List<Interview> interviews;

}
