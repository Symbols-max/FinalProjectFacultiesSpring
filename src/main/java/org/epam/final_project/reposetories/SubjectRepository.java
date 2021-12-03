package org.epam.final_project.reposetories;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    @Modifying
    @Query("UPDATE Subject s set s.grade=:grade where s.nameSubject=:name and s.info=:info")
    int changeSubjects(@Param("grade") int grade,@Param("name") String name,@Param("info") Info info);

    @Modifying
    @Query("UPDATE Subject s set s.grade=:grade where s.nameSubject=:name and s.faculty=:faculty")
    int changeSubjectsFaculty(@Param("grade") int grade, @Param("name") String name, @Param("faculty")Faculty faculty);


    List findAllByInfo(Info info);

    List findAllByFaculty(Faculty faculty);
}
