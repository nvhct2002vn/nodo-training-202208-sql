package demo.unit2.example;

import java.util.Calendar;
import java.util.Locale;

public class EnumExample {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        System.out.println("Hom nay la: " + VnDay.valueOf(calendar));
    }

    public enum VnDay {
        THU_HAI, THU_BA, CHU_NHAT, THU_BAY, KHONG_BIET;

        public static VnDay valueOf(Calendar calendar) {
            String type = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("vi"));
            return switch (type) {
                case "Th 2" -> THU_HAI;
                case "Th 7" -> THU_BAY;
                case "CN" -> CHU_NHAT;
                default -> THU_BA;
            };
//            return KHONG_BIET;
        }

    }
}
