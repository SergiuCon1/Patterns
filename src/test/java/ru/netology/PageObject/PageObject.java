package ru.netology.PageObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PageObject {

    public String setDateNotEarlyThreeDays() {
        long dateSetUnix = 1656183600;// секунды
        long oneDayUnix = 86400;
        Date currentDay = new Date();
        long currentDayUnix = currentDay.getTime() / 1000L;
        if (dateSetUnix - currentDayUnix < oneDayUnix * 3) {
            dateSetUnix = currentDayUnix + oneDayUnix * 3;
        } else {
            dateSetUnix = dateSetUnix;
        }
        Date date = new Date(dateSetUnix * 1000L); // *1000 получаем миллисекунды
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy"); // какой формат нужен, выбераем
        String formattedDate = format.format(date);
        return formattedDate;
    }
}
