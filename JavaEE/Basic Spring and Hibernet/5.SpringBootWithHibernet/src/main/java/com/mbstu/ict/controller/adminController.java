package com.mbstu.ict.controller;
import com.mbstu.ict.dao.StudentRepository;
import com.mbstu.ict.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller //Controller class >> spring will not find the meaning of mapping without this keyword
public class adminController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("-----------index-------------");
        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("studentList", studentList);
        //model.addAttribute("myMessage","Hey we have run it successfully.....");


        return "index"; // It is a jsp file
    }

    /*@RequestMapping(value = "/upsertStudent", method = RequestMethod.GET)
    public String studentView(Model model,@RequestParam(value = "studentId",required = false) Integer studentId) {
        System.out.println("-----------StudentEdit-------------");
        if(studentId==null){
        Student student = new Student();
        model.addAttribute("student", student);
        }
        else{
            Student student=studentRepository.getStudentById(studentId);
            model.addAttribute("student", student);
        }
        return "student";
    }*/
    @RequestMapping(value = "/upsertStudent", method = RequestMethod.GET)
    public String studentEdit(Model model, @RequestParam(value ="studentId", required=false) Integer studentId) {
        System.out.println("----------StudentEdit---------");
        if(studentId== null){
            Student student = new Student();
            model.addAttribute("student", student);
        }
        else {
            Student student = studentRepository.getStudentById(studentId);
            model.addAttribute("student", student);
        }
        return "student";
    }

    @RequestMapping(value = "/upsertStudent", method = RequestMethod.POST)
    public String studentPost(Model model, @ModelAttribute("student") Student student, HttpServletRequest request) {
        System.out.println("-----------studentPost-------------");
        System.out.println(student);

        try {
            studentRepository.save(student);
            request.setAttribute("message","Edit Successfully");
        }catch (Exception e)
        {
            request.setAttribute("message","Failed to save student");
        }
        return "redirect:./";
    }
    @RequestMapping(value = "/deletStudent", method = RequestMethod.GET)
    public String deleteStudent(Model model,@RequestParam(value = "studentId",required = false) Integer studentId) {
        System.out.println("-----------Delete-------------studentId:"+studentId);
            Student student=studentRepository.getStudentById(studentId);
         studentRepository.delete(student);
        return "redirect:./";
    }

}
