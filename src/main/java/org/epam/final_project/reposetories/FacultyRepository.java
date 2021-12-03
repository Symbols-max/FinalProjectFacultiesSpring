package org.epam.final_project.reposetories;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Info;
import org.epam.final_project.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Faculty deleteById(long id);

    @Modifying
    @Query("UPDATE Faculty f SET f.recruitment=:recruitment where f.id=:id")
    int changeRecruitment(@Param("recruitment") String recruitment,@Param("id") long id);

    @Modifying
    @Query("UPDATE Faculty f SET f.id=:id,f.nameFaculty=:name,f.allPlaces=:allPlaces,f.fundedPlaces=:fundedPlaces,f.description=:description where f.id=:oldId")
    int changeFaculty(@Param("id") long id,@Param("name") String name,@Param("allPlaces") int allPlaces,@Param("fundedPlaces") int fundedPlaces,@Param("description") String description,@Param("oldId") long oldId);
}
