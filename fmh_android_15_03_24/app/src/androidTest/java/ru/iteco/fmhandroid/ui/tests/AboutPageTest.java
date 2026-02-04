package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AboutPageSteps;
import ru.iteco.fmhandroid.ui.steps.AuthPageSteps;
import ru.iteco.fmhandroid.ui.steps.LoadingPageSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {
    LoadingPageSteps loadingPageSteps = new LoadingPageSteps();
    AboutPageSteps aboutPageSteps = new AboutPageSteps();
    AuthPageSteps authPageSteps = new AuthPageSteps();
    MainPageSteps mainPageSteps = new MainPageSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    @Before
    public void setUp() {
        loadingPageSteps.loadApp();
        try {
            mainPageSteps.mainPageIsLoad();
        } catch (Exception e) {
            authPageSteps.validLogIn();
            mainPageSteps.mainPageIsLoad();
        }
    }

    @Test
    @Feature(value = "Страница О приложении")
    @DisplayName("Наличие всех элементов страницы")
    public void shouldContainAllElementsOnAboutPage() {
        mainPageSteps.openAboutPage();
        aboutPageSteps.aboutPageElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница О приложении")
    @DisplayName("Возвращение на главную")
    public void shouldReturnToMainPageFromAboutPage() {
        mainPageSteps.openAboutPage();
        aboutPageSteps.goToMain();
        mainPageSteps.mainPageIsLoad();
        mainPageSteps.mainPageElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница О приложении")
    @DisplayName("Переход к странице \"О приложении\", находясь на странице \"Новости\"")
    public void shouldNavigateToAboutPageFromNewsPage() {
        mainPageSteps.openNewsPage();
        mainPageSteps.openAboutPage();
        aboutPageSteps.aboutPageElementsAreVisible();

    }

    @Test
    @Feature(value = "Страница О приложении")
    @DisplayName("Переход к политике конфиденциальности по ссылке")
    public void shouldOpenPrivacyPolicyLink() {
        mainPageSteps.openAboutPage();
        Intents.init();
        aboutPageSteps.goToPrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        Intents.release();
    }

    @Test
    @Feature(value = "Страница О приложении")
    @DisplayName("Переход к пользовательскому соглашению по ссылке")
    public void shouldOpenTermsOfUseLink() {
        mainPageSteps.openAboutPage();
        Intents.init();
        aboutPageSteps.goToTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        Intents.release();
    }
}