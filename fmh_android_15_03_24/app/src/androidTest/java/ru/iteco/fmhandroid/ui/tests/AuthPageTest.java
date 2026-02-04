package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.steps.AuthPageSteps;
import ru.iteco.fmhandroid.ui.steps.LoadingPageSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AuthPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    LoadingPageSteps loadingPageSteps = new LoadingPageSteps();
    AuthPage authPage = new AuthPage();
    AuthPageSteps authPageSteps = new AuthPageSteps();
    MainPage mainPage = new MainPage();
    MainPageSteps mainPageSteps = new MainPageSteps();
    private View decorView;

    @Before
    public void setUp() {
        loadingPageSteps.loadApp();
        try {
            authPageSteps.authPageIsLoad();
        } catch (
                Exception e) {
            mainPageSteps.logOut();
            authPageSteps.authPageIsLoad();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @After
    public void tearDown() {
        try {
            mainPageSteps.logOut();
        } catch (Exception ignored) {
        }
    }

    @Test
    @Feature(value = "Страница Авторизации")
    @Story("Наличие всех элементов страницы авторизации")
    public void shouldDisplayAllElementsInAuthPage() {
        authPageSteps.authPageElementsIsVisible();
    }

    @Test
    @Feature(value = "Страница Авторизации")
    @Story("Авторизация в приложении c валидными данными")
    public void shouldAuthorizeAndLoadMainScreenWithValidData() {
        authPageSteps.validLogIn();
        mainPageSteps.mainPageIsLoad();
        mainPageSteps.mainPageElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница Авторизации")
    @Story("Авторизация в приложении c невалидными данными")
    public void shouldRejectLoginWithInvalidDataAndShowErrorMessage() {
        authPageSteps.notValidLogIn();
        authPageSteps.checkMessageText("Неверный логин или пароль", decorView);
        authPage.title.check(matches(isDisplayed()));
        mainPage.mainLogo.check(matches(not(isDisplayed())));
    }

    @Test
    @Feature(value = "Страница Авторизации")
    @Story("Авторизация с пустой формой")
    public void shouldRejectEmptyFieldsWithMessage() {
        authPageSteps.emptyLogIn();
        authPageSteps.checkMessageText("Логин и пароль не могут быть пустыми", decorView);
        authPage.title.check(matches(isDisplayed()));
        mainPage.mainLogo.check(matches(not(isDisplayed())));
    }

    @Test
    @Feature(value = "Страница Авторизации")
    @Story("Выход из учётной записи")
    public void shouldLogoffAndReturnToAuthPage() {
        authPageSteps.validLogIn();
        mainPageSteps.mainPageIsLoad();
        mainPageSteps.logOut();
        authPageSteps.authPageElementsIsVisible();
    }

}