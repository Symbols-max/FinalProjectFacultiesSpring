package org.epam.final_project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false,length = 100)
    private String nameSubject;

    @NotNull
    @Max(value = 200)
    @Min(value=100)
    private int grade;

    @ManyToOne
    @JoinColumn(name="info_id")
    private Info info;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private Faculty faculty;

    public Subject(String nameSubject, int grade) {
       this.nameSubject=nameSubject;
       this.grade=grade;
    }

    public Subject(String nameSubject, int grade,Info info) {
        this.nameSubject=nameSubject;
        this.grade=grade;
        this.info=info;
    }
    public Subject(String nameSubject, int grade,Faculty faculty) {
        this.nameSubject=nameSubject;
        this.grade=grade;
        this.faculty=faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id && grade == subject.grade && Objects.equals(nameSubject, subject.nameSubject) && Objects.equals(info, subject.info) && Objects.equals(faculty, subject.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSubject, grade, info, faculty);
    }
}
