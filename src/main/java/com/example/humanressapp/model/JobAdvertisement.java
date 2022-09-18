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
@Table(name="jobAdvertisements")
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@SQLDelete(sql = "UPDATE jobAdvertisements SET isActive = true WHERE id=?")
//@Where(clause = "isActive=false")
public class JobAdvertisement {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;

    private boolean isActive;
    // soft delete ve aktif etme kısımları için

    @ManyToOne
    @JoinColumn(name="hr_id",referencedColumnName = "id")
    private HR hr;

    @OneToMany(mappedBy = "jobAdvertisement")
    @JsonIgnore
    private List<Step> steps;

    @ManyToMany(mappedBy = "jobAdvertisements")
    @JsonIgnore
    private List<Candidate> candidates;
    // birden fazla adayın birden fazla iş ilana başvurma hakkı olsun.







}
