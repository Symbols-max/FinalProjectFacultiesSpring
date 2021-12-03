package org.epam.final_project.controllers;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.epam.final_project.model.User;
import org.epam.final_project.model.enums.Subjects;
import org.epam.final_project.other.Helper;

import org.epam.final_project.other.Sender;
import org.epam.final_project.service.FacultyService;
import org.epam.final_project.service.MyService;
import org.epam.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@Controller
@RequestMapping("/admin")
public class AdminController {
    static final int ITEMS_PER_PAGE = 2;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService entrantService;

    @Autowired
    private MyService myService;

    public AdminController(FacultyService facultyService, UserService entrantService,MyService myService){
        this.facultyService=facultyService;
        this.entrantService=entrantService;
        this.myService=myService;
    }

    @GetMapping("/adminka")
    public String index(HttpServletRequest request, Model model,
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

        return "admin/adminka";
    }

    @GetMapping("/addNewFaculty")
    public String addNewFaculty(){
        return "admin/addNewFaculty";
    }

    @PostMapping("/addNewFaculty")
    public String addNewFaculty(Model model,HttpServletRequest request){

        String name=request.getParameter("name");
        long id = Long.parseLong(request.getParameter("id"));
        String description=request.getParameter("description");
        int allPlaces = Integer.parseInt(request.getParameter("allPlaces"));
        int fundedPlaces = Integer.parseInt(request.getParameter("fundedPlaces"));
        String recruitment="active";
        if(fundedPlaces>allPlaces){
            String message="Не удалось добавить новый факультет.Количество бюджетных мест не должно превышать общее количество";
            model.addAttribute("message",message);
            return "/admin/addNewFaculty";
        }

        Faculty faculty  =new Faculty(id,name,allPlaces,fundedPlaces,description,recruitment);
        if(facultyService.addNewFaculty(faculty)!=null) {
            return "redirect:/admin/adminka";
        }
        else{
            return "admin/addNewFaculty?error";
        }
    }

    @GetMapping("/facultyInfo")
    public String facultyInfo(@RequestParam("id") long id,Model model,HttpServletRequest request){
        HttpSession session=request.getSession();
        Faculty faculty=facultyService.findFacultyById(id);
        session.setAttribute("recruitment",faculty.getRecruitment());
        session.setAttribute("idFaculty",id);
        model.addAttribute("faculty",faculty);
        List<Subject> subjects=facultyService.findSubjectsByFaculty(faculty);
        model.addAttribute("subjects",subjects);
        return "admin/facultyInfo";
    }


    @PostMapping("/changeRecruitmentStatus")
    public String changeRecruitmentStatus(HttpServletRequest request){
        HttpSession session=request.getSession();
        long id= (long) session.getAttribute("idFaculty");
        String recruitment=(String) session.getAttribute("recruitment");
       if(recruitment.equals("active")) {
           facultyService.changeRecruitment("blocked",id);
       }
       else{
           facultyService.changeRecruitment("active",id);
       }

        return "redirect:/admin/facultyInfo?id="+id;
    }

    @GetMapping("/changeFaculty")
    public String changeFaculty(){
        return "admin/changeFaculty";
    }

    @PostMapping("/changeFaculty")
    public String changeFaculty(HttpServletRequest request){

        HttpSession session=request.getSession();
        long oldId=(long)session.getAttribute("idFaculty");


        String name=request.getParameter("name");
        String description=request.getParameter("description");
        long id = Long.parseLong(request.getParameter("id"));
        int allPlaces = Integer.parseInt(request.getParameter("allPlaces"));
        int fundedPlaces = Integer.parseInt(request.getParameter("fundedPlaces"));

        if(fundedPlaces>allPlaces){
            String message="Не удалось изменить факультет.Количество бюджетных мест не должно превышать общее количество";
            request.setAttribute("message",message);
            return "/admin/changeFaculty";
        }

        if(facultyService.changeFaculty(id,name,allPlaces,fundedPlaces,description,oldId)) {
            return "redirect:/admin/facultyInfo?id=" + id;
        }
        else {
            return "/admin/changeFaculty?error";
        }
    }

    @PostMapping("/deleteFaculty")
    public String deleteFaculty(HttpServletRequest request){

        HttpSession session=request.getSession();
        long id=(long)session.getAttribute("idFaculty");

        if(facultyService.deleteFaculty(id)){
            return "redirect:/admin/adminka";
        }
        else {
            return "redirect:/admin/facultyInfo?id=" + id;
        }
    }

    @GetMapping("/entrantsOnFacultyList")
    public String entrantsOnFacultyList(HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        long id=(long)session.getAttribute("idFaculty");
        Faculty faculty=facultyService.findFacultyById(id);
        model.addAttribute("faculty",faculty);
        List<Info> infoList=myService.entrantsOnFacultyList(id);
        model.addAttribute("entrants",infoList);
        session.setAttribute("entrants",infoList);
        return "admin/entrantsOnFacultyList";
    }

    @PostMapping("/sendToEntrant")
    public String sendToEntrant(HttpServletRequest request){
        HttpSession session=request.getSession();
        long id=(long)session.getAttribute("idFaculty");
        Faculty faculty=facultyService.findFacultyById(id);
        int fundedPlaces=faculty.getFundedPlaces();
        int allPlaces=faculty.getAllPlaces();

        List<Info> entrantList= (List<Info>) session.getAttribute("entrants");
        Object[] entrants= entrantList.toArray();

        String title="Письмо от Киевского Университета об результатах поступления";
        String fundedMessage="Поздравляем вы прошли на факультет на бюджетную основу.";
        String facultyMessage="Поздравляем вы прошли на факультет.";
        String badMessage="К сожалению вы не прошли на факультет.";

        Sender sender=new Sender();

        if(entrants.length<fundedPlaces){
            for (Info e:entrantList) {
                sender.send(fundedMessage,title,e.getEmail());
            }
        }
        else {
            for (int i = 0; i < fundedPlaces; i++) {
                Info entrant= (Info) entrants[i];
                sender.send(fundedMessage,title,entrant.getEmail());
            }
        }

        if(entrants.length>fundedPlaces) {
            if (entrants.length < allPlaces) {
                for (int i = fundedPlaces; i < entrants.length; i++) {
                    Info entrant = (Info) entrants[i];
                    sender.send(facultyMessage, title, entrant.getEmail());
                }
            } else {
                for (int i = fundedPlaces; i < allPlaces; i++) {
                    Info entrant = (Info) entrants[i];
                    sender.send(facultyMessage, title, entrant.getEmail());
                }
            }
        }


        if(entrants.length>allPlaces) {
            for (int i = allPlaces; i < entrants.length; i++) {
                Info entrant= (Info) entrants[i];
                sender.send(badMessage,title,entrant.getEmail());
            }
        }


//        Sender sender=new Sender();
//        sender.send();

        return "redirect:/admin/entrantsOnFacultyList";
    }


    @GetMapping("/changeSubjectFaculty")
    public String changeSubjectForFaculty(){ return "admin/changeSubjectFaculty"; }

    @PostMapping("/changeSubjectFaculty")
    public String changeSubjectEntrant(Model model,HttpServletRequest request) throws IOException, ServletException {
        HttpSession session=request.getSession();
        long id=(long)session.getAttribute("idFaculty");
        Faculty faculty=facultyService.findFacultyById(id);
        Subjects[] subjects=Subjects.values();
        for (Subjects s : subjects) {
            if (!(request.getParameter(s.toString()).equals(""))) {
                int grade=Integer.parseInt(request.getParameter(s.toString()));
                String name=s.value();
                if(!facultyService.changeSubjectFaculty(grade,name,faculty)){
                    return "/admin/changeSubjectFaculty?error";
                }
            }
        }
        return "redirect:/admin/facultyInfo?id=" + id;
    }

}
