package org.epam.final_project.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Faculty {
    @Id
    private long id;

    @NotNull
    private String nameFaculty;
    @NotNull
    private int allPlaces;

    @NotNull
    private int fundedPlaces;
    private String description;
    private String recruitment;

    @ManyToMany(mappedBy = "faculties")
    private Set<Info> infoSet = new HashSet<>();

    @OneToMany(mappedBy="faculty", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    public Faculty(long id, String nameFaculty, int allPlaces, int fundedPlaces, String description,String recruitment) {
    this.id=id;
    this.nameFaculty=nameFaculty;
    this.allPlaces=allPlaces;
    this.fundedPlaces=fundedPlaces;
    this.description=description;
    this.recruitment=recruitment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && allPlaces == faculty.allPlaces && fundedPlaces == faculty.fundedPlaces && Objects.equals(nameFaculty, faculty.nameFaculty) && Objects.equals(description, faculty.description) && Objects.equals(recruitment, faculty.recruitment) && Objects.equals(infoSet, faculty.infoSet) && Objects.equals(subjects, faculty.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameFaculty, allPlaces, fundedPlaces, description, recruitment, infoSet, subjects);
    }
}
