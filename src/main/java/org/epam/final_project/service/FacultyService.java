package org.epam.final_project.service;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Subject;
import org.epam.final_project.reposetories.InfoRepository;
import org.epam.final_project.reposetories.FacultyRepository;
import org.epam.final_project.reposetories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional
    public Faculty addNewFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Transactional
    public boolean changeRecruitment(String recruitment,long id) {
        if(facultyRepository.changeRecruitment(recruitment,id)!=1){
            return false;
        }
        else {
            return true;
        }
    }

    @Transactional
    public boolean deleteFaculty(long id) {
        if(facultyRepository.deleteById(id)!=null){
            return false;
        }
        else {
            return true;
        }
    }

    @Transactional
    public boolean changeSubjectFaculty(int grade,String name,Faculty faculty) {

        int i=subjectRepository.changeSubjectsFaculty(grade,name,faculty);

        if(i==1){
            return true;
        }
        else if(i==0){
            Subject subject=new Subject(name,grade,faculty);
            Subject s=subjectRepository.save(subject);
            if(s!=null){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Transactional
    public boolean changeFaculty(long id,String name,int allPlaces,int fundedPlaces,String description,long oldId) {
        if(facultyRepository.changeFaculty(id,name,allPlaces,fundedPlaces,description,oldId)!=1){
            return false;
        }
        else {
            return true;
        }
    }

    @Transactional(readOnly=true)
    public List<Subject> findSubjectsByFaculty(Faculty faculty) {
        return subjectRepository.findAllByFaculty(faculty);
    }


    @Transactional(readOnly=true)
    public List<Faculty> findAll(Pageable pageable) {
        return facultyRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public Faculty findFacultyById(long id){
        return facultyRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public int countApplies(Faculty faculty){
        return infoRepository.countByFaculties(faculty);
    }

    @Transactional(readOnly = true)
    public long count() {
        return facultyRepository.count();
    }
}
