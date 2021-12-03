package org.epam.final_project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Info {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String middleName;
    @Column(length = 50)
    private String surname;
    @Column(length = 50)
    private String city;
    private String region;
    private String placeEducation;
    private byte[] diplom;

    @Transient
    private float avgGrade;
    @Transient
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "info_faculty",
            joinColumns = @JoinColumn(name = "info_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<Faculty> faculties = new HashSet<>();

    @OneToMany(mappedBy="info", cascade=CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();


    @OneToOne(mappedBy = "info")
    private User user;

    public Info(String name,String surname,String middleName,String city, String region, String placeEducation, byte[] diplom) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.city = city;
        this.region = region;
        this.placeEducation = placeEducation;
        this.diplom = diplom;
    }

    public Info(String email,String surname,String name,String middleName,float avgGrade) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.email=email;
        this.avgGrade=avgGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return id == info.id && Objects.equals(name, info.name) && Objects.equals(middleName, info.middleName) && Objects.equals(surname, info.surname) && Objects.equals(city, info.city) && Objects.equals(region, info.region) && Objects.equals(placeEducation, info.placeEducation) && Arrays.equals(diplom, info.diplom) && Objects.equals(faculties, info.faculties) && Objects.equals(subjects, info.subjects) && Objects.equals(user, info.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, middleName, surname, city, region, placeEducation, faculties, subjects, user);
        result = 31 * result + Arrays.hashCode(diplom);
        return result;
    }
}
