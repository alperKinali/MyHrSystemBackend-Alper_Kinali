package com.example.humanressapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name ="hr")
public class HR extends  User {
    private String companyName;
    private String webSite;
    private  String adress;

    // one to many jobAdverb.
    @OneToMany(mappedBy ="hr")
    @JsonIgnore
    private List<JobAdvertisement> jobAdvertisements;
    // bir hr'nin birden fazla iş ilanı olabilir.

}
