package com.sms.controller;

import com.sms.entity.Student;
import com.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        return "students";
    }
    @GetMapping("/students/new")
    public String showAddStudentForm(Model model) {

        Student student = new Student();

        model.addAttribute("student", student);

        return "add-student";
    }
    @PostMapping("/students")
    public String saveStudent(
            @ModelAttribute("student") Student student) {

        studentService.saveStudent(student);

        return "redirect:/students";
    }
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(
            @PathVariable Long id,
            Model model) {

        Student student =
                studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "edit-student";
    }
    @PostMapping("/students/update")
    public String updateStudent(
            @ModelAttribute("student") Student student) {

        studentService.updateStudent(student);

        return "redirect:/students";
    }
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }
    @GetMapping("/students/search")
    public String searchStudents(
            @RequestParam("keyword") String keyword,
            Model model) {

        model.addAttribute(
                "students",
                studentService.searchStudents(keyword)
        );

        return "students";
    }
}