package api.utils;
import java.util.Date;
import java.util.GregorianCalendar;

public class GenerateRandomDate {
    // Генерация даты
    public static String generateRandomDate() {
        long time = System.currentTimeMillis();
        Date dat = new Date(time);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return format.format(gc.getTime());
    }
}
