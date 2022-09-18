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
@Table(name="cities")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<JobAdvertisement>jobAdvertisements;

}
