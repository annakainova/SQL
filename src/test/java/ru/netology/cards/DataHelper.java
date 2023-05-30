package ru.netology.cards;

import com.github.javafaker.Faker;
import lombok.Value;

import javax.xml.crypto.Data;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfoForNotExistedUser(String locale) {

        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().name();
        String password = faker.internet().password();
        return new AuthInfo(name, password);
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;

        public static String getRandomCode(String locale) {
            Faker faker = new Faker(new Locale(locale));
            String code = faker.numerify("#####");
            return code;
        }
    }
}

