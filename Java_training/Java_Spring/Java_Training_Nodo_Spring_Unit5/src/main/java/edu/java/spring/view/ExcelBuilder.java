package edu.java.spring.view;

import com.fasterxml.classmate.Annotations;
import com.sun.net.httpserver.Headers;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.model.Student;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExcelBuilder extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, org.apache.poi.ss.usermodel.Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Sheet sheet = workbook.createSheet("Java Clazz");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Age");

        JavaClazz clazz = (JavaClazz) map.get("clazzObj");
        for (int i = 0; i < clazz.getStudentList().size(); i++) {
            Student student = clazz.getStudentList().get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getAge());
        }

    }
}
