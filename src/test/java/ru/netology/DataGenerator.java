package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Faker faker = new Faker(new Locale("ru"));

    public String generateCity(){
        String[] cityList = new String[]{"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород", "Биробиджан", "Благовещенск", "Брянск", "Великий Новгород", "Владивосток", "Владикавказ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Горно-Алтайск", "Грозный", "Екатеринбург", "Иваново", "Ижевск", "Иркутск", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Краснодар", "Красноярск", "Курган", "Курск", "Кызыл", "Липецк", "Магадан", "Магас", "Майкоп", "Махачкала", "Москва", "Мурманск", "Нальчик", "Нарьян-Мар", "Нижний Новгород", "Новосибирск", "Омск", "Орёл", "Оренбург", "Пенза", "Пермь", "Петрозаводск", "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону", "Рязань", "Салехард", "Самара", "Санкт-Петербург", "Саранск", "Саратов", "Севастополь", "Симферополь", "Смоленск", "Ставрополь", "Сыктывкар", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Улан-Удэ", "Ульяновск", "Уфа", "Хабаровск", "Ханты-Мансийск", "Чебоксары", "Челябинск", "Черкесск", "Чита", "Элиста", "Южно-Сахалинск", "Якутск", "Ярославль"};
        int city =  (int) (Math.random() * cityList.length);
        return cityList[city];
    }

    public String generateWrongCity(){
        Faker faker = new Faker(new Locale("en"));
        return faker.address().city();
    }

    public String generateDate(){
        final int random_num = (int) (Math.random() * 15);
        LocalDate date = LocalDate.now();
        LocalDate bookingDate = date.plusDays(3 + random_num);
        return dateFormat.format(bookingDate);
    }

    public String todayDate(){
        LocalDate date = LocalDate.now();
        return dateFormat.format(date);
    }

    public String generateName(){
        return faker.name().fullName();
    }

    public String generateWrongName(){
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public String generatePhone(){
        return faker.phoneNumber().phoneNumber();
    }

//    public String generateWrongPhone(){
//        return faker.numerify("#######");
//    }
}
