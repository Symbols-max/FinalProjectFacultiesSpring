package org.epam.final_project.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.epam.final_project.model.User;
import org.epam.final_project.model.enums.Subjects;
import org.epam.final_project.other.Helper;
import org.epam.final_project.service.FacultyService;
import org.epam.final_project.service.MyService;
import org.epam.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/entrant")
public class EntrantController {
    static final int ITEMS_PER_PAGE = 2;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

    @Autowired
    private MyService myService;


    public EntrantController(FacultyService facultyService, UserService userService,MyService myService){
        this.facultyService=facultyService;
        this.userService = userService;
        this.myService=myService;
    }

    @GetMapping("/homePage")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(value = "sort",required = false,defaultValue = "") String sort,
                        @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<Faculty> facultyList;
        if (page < 0){
            page = 0;
        }

        if(sort.equals("byFundedPlaces")) {
            facultyList = facultyService.findAll(PageRequest.of(page,
                    ITEMS_PER_PAGE,
                    Sort.Direction.DESC, "fundedPlaces"));
        }
        else if(sort.equals("byAllPlaces")){
            facultyList = facultyService.findAll(PageRequest.of(page,
                    ITEMS_PER_PAGE,
                    Sort.Direction.DESC, "allPlaces"));
        }
        else{
            facultyList = facultyService.findAll(PageRequest.of(page,
                    ITEMS_PER_PAGE,
                    Sort.Direction.DESC, "nameFaculty"));
        }

        HttpSession session=request.getSession();
        session.setAttribute("sort",sort);
        model.addAttribute("faculties", facultyList);
        model.addAttribute("allPages", Helper.getPageCount(facultyService.count(),ITEMS_PER_PAGE));

        return "entrant/homePage";
    }

    @GetMapping("/profile")
    public String index(Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
       String email=getCurrentUser().getUsername();
       session.setAttribute("email",email);
       User user=userService.findUserByEmail(email);
       model.addAttribute("user",user);
       model.addAttribute("info",user.getInfo());

       List<Subject> subjects=userService.findSubjectsByInfo(user.getInfo());
       model.addAttribute("subjects",subjects);

       List<Long> id_faculties=myService.findFacultyIdByInfoId(user.getInfo().getId());
        model.addAttribute("id_faculties",id_faculties);
        return "entrant/profile";
    }

    @PostMapping("/deleteEntrantFaculty")
    public String deleteFaculty(@RequestParam("id") long id_faculty){

        String email=getCurrentUser().getUsername();
        User user=userService.findUserByEmail(email);
        long id_info=user.getInfo().getId();
        myService.deleteFacultyEntrant(id_info,id_faculty);
        
        return "redirect:/entrant/profile";
    }

    @GetMapping("/changeInfoEntrant")
    public String changeInfo() {

        return "entrant/changeInfoEntrant";
    }

    @PostMapping("/changeInfoEntrant")
    public String changeInfo(Model model,HttpServletRequest request) throws IOException, ServletException {
        String name=request.getParameter("newName");
        String surname=request.getParameter("newSurname");
        String middleName=request.getParameter("newMiddleName");
        String city=request.getParameter("newCity");
        String region=request.getParameter("newRegion");
        String placeEducation=request.getParameter("newPlaceEducation");
        Part filePart=request.getPart("newDiplom");
        InputStream inputStream= filePart.getInputStream();
        final byte[] diplom;
        try (inputStream) {
            diplom = inputStream.readAllBytes();
        }

        User user=userService.findUserByEmail(getCurrentUser().getUsername());
        long id=user.getInfo().getId();

        if(userService.changeInfo(name,surname,middleName,city,region, placeEducation,diplom,id)){
            return "redirect:/entrant/profile";
        }
        else{
            return "entrant/changeInfoEntrant?error";
        }
    }

    @GetMapping("/changeSubjectEntrant")
    public String changeSubjectEntrant(Model model) {
        Subjects[] subjects=Subjects.values();
        model.addAttribute("subjects",subjects);
        return "entrant/changeEntrantSubject";
    }

    @PostMapping("/changeSubjectEntrant")
    public String changeSubjectEntrant(Model model,HttpServletRequest request) throws IOException, ServletException {
        User user=userService.findUserByEmail(getCurrentUser().getUsername());
        Info info=user.getInfo();
        Subjects[] subjects=Subjects.values();
        for (Subjects s : subjects) {
            if (!(request.getParameter(s.toString()).equals(""))) {
                int grade=Integer.parseInt(request.getParameter(s.toString()));
                String name=s.value();
                if(!userService.changeSubjects(grade,name,info)){
                    return "entrant/changeEntrantSubject?error";
                }
            }
        }

            return "redirect:/entrant/profile";

    }


    private org.springframework.security.core.userdetails.User getCurrentUser() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    }
