package org.epam.final_project.service;

import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.epam.final_project.reposetories.MyRepository;
import org.epam.final_project.reposetories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.util.List;

@Service
public class MyService {

    @Autowired
    MyRepository myRepository;

    public boolean addApply(long info_id,long faculty_id){


        return myRepository.addApply(info_id,faculty_id);
    }

    @Transactional(readOnly = true)
    public List<Long> findFacultyIdByInfoId(long info_id)
    {
        return myRepository.findFacultyById(info_id);
    }

    @Transactional
    public boolean deleteFacultyEntrant(long info_id,long faculty_id){
        return myRepository.deleteFacultyInEntrant(info_id,faculty_id);
    }

    @Transactional
    public List<Info> entrantsOnFacultyList(long faculty_id){
        return myRepository.entrantOnFacultyList(faculty_id);
    }
}
