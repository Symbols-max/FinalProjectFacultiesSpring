package org.epam.final_project.reposetories;

import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.epam.final_project.other.DBManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class MyRepository {

    private static final Logger logger=Logger.getLogger(MyRepository.class.getName());

    public boolean addApply(long info_id,long faculty_id) {
        String sql = "INSERT INTO info_faculty(info_id, faculty_id) VALUES(?,?);";

        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, info_id);
            statement.setLong(2, faculty_id);
            if (statement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return true;
    }

    public List<Long> findFacultyById(long id) {
        List<Long> id_faculties = new ArrayList<>();
        String sql = "SELECT faculty_id FROM info_faculty where info_id=?;";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    id = rs.getLong(1);
                    id_faculties.add(id);
                }
                return id_faculties;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }


    public boolean deleteFacultyInEntrant(long id_info, long id_faculty) {
        String sql = "DELETE FROM info_faculty WHERE info_id=? and faculty_id=?";

        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id_info);
            statement.setLong(2, id_faculty);
            if (statement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return true;
    }

    public List<Info> entrantOnFacultyList(long id_faculty) {
        List<Info> infoList = new ArrayList<>();
        String sql = "select u.email,i.surname,i.name,i.middle_name,avg(s.grade) from faculties_spring.info as i\n" +
                "join faculties_spring.user as u on i.id=u.info_id\n" +
                "join faculties_spring.subject as s on s.info_id=i.id\n" +
                "join faculties_spring.info_faculty as fi on fi.info_id=i.id \n" +
                "where fi.faculty_id=?\n" +
                "group by i.id\n" +
                "order by avg(s.grade) desc;";

        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id_faculty);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String surname = rs.getString(1);
                    String name = rs.getString(2);
                    String middleName = rs.getString(3);
                    String email = rs.getString(4);
                    float avgGrade = rs.getFloat(5);
                    Info info = new Info(email, surname, name, middleName, avgGrade);
                    infoList.add(info);
                }
            }
            return infoList;
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}
