package edu.java.spring.controller;


import edu.java.spring.dao.StudentDAO;
import edu.java.spring.dao.iplm.StudentDAOIplm;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.model.Student;
import edu.java.spring.utils.XSLTUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.print.attribute.standard.Media;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("clazz")
public class ClazzController {

    @Autowired
    StudentDAO studentDAO;

    @GetMapping(value = "xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody JavaClazz viewInXml() {
        return new JavaClazz(studentDAO.list());
    }

    @GetMapping(value = "json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody JavaClazz viewInJSON() {
        return new JavaClazz(studentDAO.list());
    }

    @GetMapping("/xslt")
    public ModelAndView viewXSLT() throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JavaClazz clazz = new JavaClazz();
        ModelAndView model = new ModelAndView("ClazzView");
        model.getModelMap().put("data", XSLTUtils.clazzToDomSource(clazz));
        return model;
    }

    @GetMapping("excel")
    public ModelAndView viewExcel() {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView mv = new ModelAndView("excelView");
        mv.getModelMap().put("clazzObj", clazz);
        return mv;
    }

    @GetMapping(value = "pdf", produces = "application/pdf")
    public ModelAndView viewPdf() {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView mv = new ModelAndView("pdfView");
        mv.getModelMap().put("clazzObj", clazz);
        return mv;
    }

    @GetMapping(value = "report", produces = "applicaton/pdf")
    public ModelAndView viewReport() {
        List<Student> students = studentDAO.list();
        JRDataSource dataSoucre = new JRBeanCollectionDataSource(studentDAO.list());
        ModelAndView mv = new ModelAndView("pdfReport");
        mv.addObject("dataSource", dataSoucre);
        return mv;
    }
}
