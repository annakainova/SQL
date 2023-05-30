package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.cards.DataHelper;
import ru.netology.cards.SQLHelper;
import ru.netology.page.LoginPage;

import static ru.netology.cards.SQLHelper.cleanDataBase;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @AfterAll
    static void teardown() {
        cleanDataBase();
    }

    @Test
    @DisplayName("Successfully login. Login and password from sut data")
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var code = SQLHelper.getVerificationCode();
        verificationPage.validVerify(code.getCode());
    }

    @Test
    @DisplayName("Should be error if user does not exist in base")
    void shouldBeErrorIfUserDoesNotExistInBase() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoForNotExistedUser("en");
        loginPage.verifyErrorNotification(authInfo);
    }

    @Test
    @DisplayName("Should be error if user exists in data base but verification code is wrong")
    void shouldBeErrorIfVerificationCodeIsWrong() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var code = DataHelper.VerificationCode.getRandomCode("en");
        verificationPage.verifyErrorNotification(code);
    }
}
