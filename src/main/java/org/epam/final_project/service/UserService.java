package org.epam.final_project.service;

import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.epam.final_project.model.User;
import org.epam.final_project.model.enums.Role;
import org.epam.final_project.reposetories.InfoRepository;
import org.epam.final_project.reposetories.SubjectRepository;
import org.epam.final_project.reposetories.UserRepository;
import org.epam.final_project.reposetories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUsersByRole(Pageable pageable, Role role){
       return userRepository.findAllByRole(role,pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsersByRoleAndStatus(Pageable pageable, Role role,boolean status){
        return userRepository.findAllByRoleAndStatus(role,status,pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers(Pageable pageable){ return userRepository.findAll(pageable).getContent(); }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existUserByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public User addUser(User user){
        if(existUserByEmail(user.getEmail())){
            return null;
        }
        infoRepository.save(user.getInfo());

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public byte[] findDiplomByUser(String email){
        User user= userRepository.findByEmail(email);
        Info info=user.getInfo();
        return info.getDiplom();
    }

    @Transactional(readOnly = true)
    public Info findInfoByUser(User user){
        return infoRepository.findByUser(user);
    }

    @Transactional()
    public boolean changeInfo(String name, String surname, String middleName,
                                    String city, String region, String placeEducation, byte[] diplom, long id)
    {

        if(infoRepository.changeInfo(name,surname,middleName,city,region,placeEducation,diplom,id)!=1){
            return false;
        }
        else {
            return true;
        }
    }

    @Transactional()
    public boolean changeUserStatus(boolean status,String email)
    {

        if(userRepository.changeUserStatus(status,email)!=1){
            return false;
        }
        else {
            return true;
        }
    }

    @Transactional()
    public boolean changeSubjects(int grade,String name,Info info)
    {

        int i=subjectRepository.changeSubjects(grade,name,info);

        if(i==1){
            return true;
        }
        else if(i==0){
            Subject subject=new Subject(name,grade,info);
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

    @Transactional(readOnly = true)
    public List<Subject> findSubjectsByInfo(Info info)
    {
        return subjectRepository.findAllByInfo(info);
    }

    @Transactional
    public boolean deleteEntrant(String email){
        userRepository.deleteByEmail(email);
        if(userRepository.findByEmail(email)==null) {
            return true;
        }
        else {
            return false;
        }
    }

}
