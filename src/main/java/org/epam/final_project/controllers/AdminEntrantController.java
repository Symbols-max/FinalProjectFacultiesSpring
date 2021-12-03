package org.epam.final_project.controllers;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Info;
import org.epam.final_project.model.Subject;
import org.epam.final_project.model.User;
import org.epam.final_project.model.enums.Role;
import org.epam.final_project.other.Helper;
import org.epam.final_project.service.FacultyService;
import org.epam.final_project.service.MyService;
import org.epam.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/entrants")
public class AdminEntrantController {
    static final int ITEMS_PER_PAGE = 2;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

    @Autowired
    private MyService myService;

    public AdminEntrantController(FacultyService facultyService, UserService userService, MyService myService) {
        this.facultyService = facultyService;
        this.userService = userService;
        this.myService = myService;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
                        @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<User> userList;
        if (page < 0) {
            page = 0;
        }


        if (sort.equals("active")) {
            userList = userService.findAllUsersByRoleAndStatus(PageRequest.of(page,
                    ITEMS_PER_PAGE,Sort.Direction.DESC, "id"),Role.ENTRANT,true);
        } else if (sort.equals("blocked")) {
            userList = userService.findAllUsersByRoleAndStatus(PageRequest.of(page,
                    ITEMS_PER_PAGE,Sort.Direction.DESC, "id"),Role.ENTRANT,false);
        } else {

            userList = userService.findAllUsersByRole(PageRequest.of(page,ITEMS_PER_PAGE,Sort.Direction.DESC, "id"), Role.ENTRANT);
        }

        HttpSession session = request.getSession();
        session.setAttribute("sort", sort);
        model.addAttribute("entrants", userList);
        model.addAttribute("allPages", Helper.getPageCount(facultyService.count(), ITEMS_PER_PAGE));

        return "admin/adminkaEntrant";
    }

    @GetMapping("/infoAboutEntrant")
    public String infoAboutEntrant(Model model,@RequestParam("email") String email){
        User entrant=userService.findUserByEmail(email);
        Info info=entrant.getInfo();
        model.addAttribute("entrant",entrant);
        model.addAttribute("info",info);
        List<Subject> subjects=userService.findSubjectsByInfo(info);
        model.addAttribute("subjects",subjects);
        List<Long> id_faculties=myService.findFacultyIdByInfoId(info.getId());
        model.addAttribute("id_faculties",id_faculties);
        return "admin/infoAboutEntrant";
    }

    @PostMapping("/changeUserStatus")
    public String changeUserStatus(HttpServletRequest request){
        String email=request.getParameter("email");
        User entrant=userService.findUserByEmail(email);
        if(entrant.getStatus().equals("active")){
            userService.changeUserStatus(false,email);
        }
        else{
            userService.changeUserStatus(true,email);
        }
        return "redirect:/admin/entrants/infoAboutEntrant?email="+email;
    }

    @PostMapping("/deleteEntrant")
    public String deleteEntrant(HttpServletRequest request){
        String email=request.getParameter("email");
        User entrant=userService.findUserByEmail(email);
        if(userService.deleteEntrant(email)) {
            return "redirect:/admin/entrants/";
        }
        else {
            return "redirect:/admin/entrants/infoAboutEntrant?email=" + email;
        }
    }
}
