package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.elementWaiting;

import android.view.View;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helper.DataHelper;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
public class AuthPageSteps {
    AuthPage authPage = new AuthPage();

    public void authPageIsLoad() {
        Allure.step("Загрузка страницы авторизации");
        elementWaiting(withId(R.id.enter_button), 10000);
    }

    public void authPageElementsIsVisible() {
        Allure.step("Наличие всех элементов формы авторизации");
        authPage.title.check(matches(isDisplayed()));
        authPage.loginField.check(matches(isDisplayed()));
        authPage.passwordField.check(matches(isDisplayed()));
        authPage.loginButton.check(matches(isDisplayed()));
    }

    public void validLogIn() {
        Allure.step("Отправка формы с валидными данными");
        DataHelper help = new DataHelper();
        authPage.loginField.perform(typeText(help.getValidUser().getLogin()), closeSoftKeyboard());
        authPage.passwordField.perform(typeText(help.getValidUser().getPassword()), closeSoftKeyboard());
        authPage.loginButton.perform(click());
    }

    public void notValidLogIn() {
        Allure.step("Отправка формы с невалидными данными");
        DataHelper helper = new DataHelper();
        authPage.loginField.perform(typeText(helper.getNotValidUser().getLogin()), closeSoftKeyboard());
        authPage.passwordField.perform(typeText(helper.getNotValidUser().getPassword()), closeSoftKeyboard());
        authPage.loginButton.perform(click());
    }

    public void emptyLogIn() {
        Allure.step("Отправка пустой формы");
        authPage.loginButton.perform(click());
    }

    public void checkMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}
