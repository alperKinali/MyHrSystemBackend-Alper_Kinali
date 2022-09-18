package com.example.humanressapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="candidates")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Candidate  extends  User{

    private String firstName;
    private String lastName;
    // eğer mernis kullanılacak ise int çevir sadece yıl al.
    private LocalDate birthDate;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "jobAdvert_id",referencedColumnName = "id") )
    @JsonIgnore
    private List<JobAdvertisement> jobAdvertisements;

    // candidate içerisinde ki jobAdverblistesi.

    @OneToMany(mappedBy = "candidate")
    @JsonIgnore
    private List<Interview>interviews;
    // bir adayın birden fazla mülakatı olabilir.

}
