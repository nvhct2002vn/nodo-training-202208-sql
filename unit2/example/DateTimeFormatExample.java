package demo.unit2.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeFormatExample {
    public static void main(String[] args) {
        Locale locale = new Locale("vi", "VN");
        DateFormat dateFormat = new SimpleDateFormat("EEEE,dd MMMMMM yyyy", locale);
        Calendar calendar = Calendar.getInstance();

        System.out.println(dateFormat.format(calendar.getTime()));
    }

}
