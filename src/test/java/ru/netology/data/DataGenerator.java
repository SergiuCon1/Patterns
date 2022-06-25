package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru"));

    public static String randomCity() {
        String[] city = new String[]{
                "Абакан",
                "Анадырь",
                "Архангельск",
                "Астрахань",
                "Барнаул",
                "Белгород",
                "Биробиджан",
                "Благовещенск",
                "Брянск",
                "Великий Новгород",
                "Владивосток",
                "Владикавказ",
                "Владимир",
                "Волгоград",
                "Вологда",
                "Воронеж",
                "Горно-Алтайск",
                "Грозный",
                "Екатеринбург",
                "Иваново",
                "Ижевск",
                "Иркутск",
                "Йошкар-Ола",
                "Казань",
                "Калининград",
                "Калуга",
                "Кемерово",
                "Киров",
                "Кострома",
                "Краснодар",
                "Красноярск",
                "Курган",
                "Курск",
                "Кызыл",
                "Липецк",
                "Магадан",
                "Магас",
                "Майкоп",
                "Махачкала",
                "Москва",
                "Мурманск",
                "Нальчик",
                "Нарьян-Мар",
                "Нижний Новгород",
                "Новосибирск",
                "Омск",
                "Орёл",
                "Оренбург",
                "Пенза",
                "Пермь",
                "Петрозаводск",
                "Петропавловск-Камчатский",
                "Псков",
                "Ростов-на-Дону",
                "Рязань",
                "Салехард",
                "Самара",
                "Санкт-Петербург",
                "Саранск",
                "Саратов",
                "Севастополь",
                "Симферополь",
                "Смоленск",
                "Ставрополь",
                "Сыктывкар",
                "Тамбов",
                "Тверь",
                "Томск",
                "Тула",
                "Тюмень",
                "Улан-Удэ",
                "Ульяновск",
                "Уфа",
                "Хабаровск",
                "Ханты-Мансийск",
                "Чебоксары",
                "Челябинск",
                "Черкесск",
                "Чита",
                "Элиста",
                "Южно-Сахалинск",
                "Якутск",
                "Ярославль"
        };
        int indexOfCity = (int) Math.floor(Math.random() * city.length);
        return city[indexOfCity];
    }

    public static String generateFullName() {
        return faker.name().fullName();
    }

    public static String generatePhoneNumber() {
        return faker.numerify("+###########");
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
