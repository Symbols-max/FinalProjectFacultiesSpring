package org.epam.final_project.controllers;
import org.epam.final_project.model.*;
import org.epam.final_project.model.enums.Role;
import org.epam.final_project.other.Converter;
import org.epam.final_project.other.Helper;
import org.epam.final_project.service.MyService;
import org.epam.final_project.service.UserService;
import org.epam.final_project.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MainController {
    final static Logger log = Logger.getLogger(MainController.class.getName());
    static final int ITEMS_PER_PAGE = 2;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private MyService myService;

    @Autowired
    private UserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;

    public MainController(FacultyService facultyService, UserService userService,
                          PasswordEncoder passwordEncoder, MyService myService) {
        this.facultyService = facultyService;
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
        this.myService=myService;
    }

    @ModelAttribute
    public void initial(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("bundle") == null) {
            ResourceBundle bundle = ResourceBundle.getBundle("language");
            session.setAttribute("bundle", bundle);
        }
    }

        @GetMapping("/")
        public String index (HttpServletRequest request, Model model,
                @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
                @RequestParam(required = false, defaultValue = "0") Integer page) {
            if (page < 0) {
                page = 0;
            }
            List<Faculty> facultyList;
            if (sort.equals("byFundedPlaces")) {
                facultyList = facultyService.findAll(PageRequest.of(page,
                        ITEMS_PER_PAGE,
                        Sort.Direction.DESC, "fundedPlaces"));
            } else if (sort.equals("byAllPlaces")) {
                facultyList = facultyService.findAll(PageRequest.of(page,
                        ITEMS_PER_PAGE,
                        Sort.Direction.DESC, "allPlaces"));
            } else {
                facultyList = facultyService.findAll(PageRequest.of(page,
                        ITEMS_PER_PAGE,
                        Sort.Direction.DESC, "nameFaculty"));
            }

            HttpSession session = request.getSession();
            session.setAttribute("sort", sort);
            model.addAttribute("faculties", facultyList);
            model.addAttribute("allPages", Helper.getPageCount(facultyService.count(), ITEMS_PER_PAGE));
            return "index";
        }


    @GetMapping("/authorization")
    public String authorization(@RequestParam("btn") String value) {
        if (value.equals("Sign Up")) {
            return "registration";
        } else {
            return "signIn";
        }
    }

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("language") String language, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("language", new Locale(language, language.toLowerCase()));
        session.setAttribute("bundle", bundle);
        return "redirect:/";
    }

    @GetMapping("/facultyInfo")
    public String facultyInfo(Model model, @RequestParam("id") long id) {

        Faculty faculty = facultyService.findFacultyById(id);
        model.addAttribute("faculty", faculty);
        long numberApplies = facultyService.countApplies(faculty);
        model.addAttribute("numberApplies", numberApplies);
        List<Subject> subjects=facultyService.findSubjectsByFaculty(faculty);
        model.addAttribute("subjects",subjects);

        return "facultyInfo";

    }

    @PostMapping("/apply")
    public String apply(Model model,@RequestParam("id_faculty") long id_faculty) {
        User user=userService.findUserByEmail(getCurrentUser().getUsername());
        if(!user.isStatus()){
            return "redirect:/entrant/homePage?errorApplyUserBlocked";
        }
        long info_id = user.getInfo().getId();
        Info info=user.getInfo();
        List<Subject> subjectsI = userService.findSubjectsByInfo(info);
        Faculty faculty = facultyService.findFacultyById(id_faculty);
        if(faculty.getRecruitment().equals("blocked")){
            return "redirect:/entrant/homePage?errorApplyBlocked";
        }
        List<Subject> subjectsF = facultyService.findSubjectsByFaculty(faculty);


        if (subjectsF.isEmpty()){
          myService.addApply(info_id, id_faculty);
        return "redirect:/entrant/homePage?success";
    }

        if(checkSubjects(subjectsI,subjectsF)){
            myService.addApply(info_id, id_faculty);
            return "redirect:/entrant/homePage?success";
        }
        else{
            return "redirect:/entrant/homePage?errorApply";
        }
    }



    @GetMapping("/signIn")
    public String signIn(Model model, HttpServletRequest request) { return "/signIn"; }

    @PostMapping("/registration")
    public String registration(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = passwordEncoder.encode(request.getParameter("password"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String middleName = request.getParameter("middleName");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String placeEducation = request.getParameter("placeEducation");

        Part filePart;
        byte[] diplom = null;
        try {
            filePart = request.getPart("diplom");
            diplom = Converter.streamToByteArray(filePart.getInputStream());
        } catch (IOException | ServletException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }

        Role role = Role.ENTRANT;
        boolean status = true;

        Info info = new Info(name, surname, middleName, city, region, placeEducation, diplom);

        User user = new User(email, password, status, role, info);

        User user1 = userService.addUser(user);
        if (user1 != null) {
            return "redirect:/entrant/homePage";
        } else {
            ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
            String message = bundle.getString("signUp.message");
            model.addAttribute("message", message);
            return "registration";
        }
    }


    @PostMapping("/checkPoint")
    public String checkPoint() {
        if(isAdmin(getCurrentUser())){

            return "redirect:/admin/adminka";
        }

        if(isEntrant(getCurrentUser())){
            return "redirect:/entrant/homePage";
        }

        return "/";
    }

    private org.springframework.security.core.userdetails.User getCurrentUser() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private boolean isAdmin(org.springframework.security.core.userdetails.User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

    private boolean isEntrant(org.springframework.security.core.userdetails.User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ENTRANT".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

    @GetMapping("/file")
    public void file(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String email= (String) session.getAttribute("email");

        createFile(email);

        File file = new File("diplom.pdf");
        response.setHeader("Content-Type",    request.getServletContext().getMimeType(file.getName()));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=Test.pdf");
        Files.copy(file.toPath(), response.getOutputStream());
    }

    private void createFile(String email){

        byte[] bytes=userService.findDiplomByUser(email);
        try (OutputStream outputStream = new FileOutputStream(new File("diplom.pdf")))
        {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSubjects(List<Subject> infoSubjects, List<Subject> facultySubjects){
        boolean[] booleans=new boolean[facultySubjects.size()];
        int f=0;
        for (Subject facultySubject:facultySubjects){
            for (Subject entrantSubject:infoSubjects){
                if(facultySubject.getNameSubject().equals(entrantSubject.getNameSubject())==true){
                    if(facultySubject.getGrade()<=entrantSubject.getGrade()){
                        booleans[f]=true;
                        f++;
                    }
                }
            }
        }

        for (int i = 0; i < booleans.length; i++) {
            if(booleans[i]!=true){
                return false;
            }
        }
        return true;
    }

    }




