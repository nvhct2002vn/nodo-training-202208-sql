package edu.java.spring.controller;

import com.sun.net.httpserver.HttpHandler;
import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

import static java.io.File.separator;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/form")
    public ModelAndView form() {
        return new ModelAndView("studentForm", "command", new Student());
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result, @RequestParam("id") Optional<Integer> id) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv = new ModelAndView("studentForm", "command", student);
            mv.addObject("errors", result);
            return mv;
        }
        if (id.isPresent()) {
            studentDAO.update(student);
        } else {
            studentDAO.insert(student);
        }
        mv.addObject("student", student);

        mv.addObject("studentList", studentDAO.list());

        mv.setViewName("/studentList");
        return mv;
    }

    @GetMapping("/list")
    public ModelAndView listStudent(@RequestParam(value = "q", required = false) Optional<String> query) {
        List<Student> studentList;
        if (query.isPresent()) {
            studentList = studentDAO.getUserByName(query.get());
        } else {
            studentList = studentDAO.list();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("studentList", studentList);
        mv.setViewName("/studentList");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentDAO.delete(id);
        return "redirect:/student/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id) {
        Student student = new Student();
        student = studentDAO.get(id);
        return new ModelAndView("/studentForm", "command", student);
    }

    @GetMapping("/json/{id}")
    public @ResponseBody Student viewJson(@PathVariable("id") Integer id) {
        return studentDAO.get(id);
    }

    private Path getImageFile(HttpServletRequest request, Integer id) {
        ServletContext context = request.getSession().getServletContext();
        String diskPath = context.getRealPath("/");
        File folder = new File(diskPath + File.separator + "avatar");
        folder.mkdir();
        return Path.of(String.valueOf(folder), String.valueOf(id) + ".jpg");
    }

    @PostMapping("/avatar/save")
    public String handleFromUpload(MultipartFile file, Integer id, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return "studentError";
        }
        Path avatarFile = getImageFile(request, id);

        Files.write(avatarFile, file.getBytes(), StandardOpenOption.CREATE);
        System.out.println(avatarFile);
        return "redirect:/student/list";
    }

    @GetMapping("/avatar/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id, HttpServletRequest request) throws IOException {
        ByteArrayOutputStream byteOutPut = new ByteArrayOutputStream();
        if (id != null) {
            Path avatarPath = getImageFile(request, id);
            if (Files.exists(avatarPath)) {
                byteOutPut.write(Files.readAllBytes(avatarPath));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(byteOutPut.toByteArray(), headers, HttpStatus.CREATED);
    }
}
