package org.epam.final_project.reposetories;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Info;
import org.epam.final_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InfoRepository extends JpaRepository<Info,Long> {

    int countByFaculties(Faculty faculty);

    @Modifying
    @Query("UPDATE Info i set i.name=:name,i.surname=:surname,i.middleName=:middleName,i.city=:city,i.region=:region,i.placeEducation=:placeEducation,i.diplom=:diplom where i.id=:id")
    int changeInfo(@Param("name") String name, @Param("surname") String surname,
                       @Param("middleName") String middleName, @Param("city") String city,
                       @Param("region") String region, @Param("placeEducation") String placeEducation,
                       @Param("diplom")byte[] diplom,@Param("id")long id);

    Info findByUser(User user);

}
