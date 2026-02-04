package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthPageSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.LoadingPageSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;
import ru.iteco.fmhandroid.ui.steps.NewsFilterSteps;
import ru.iteco.fmhandroid.ui.steps.NewsPageSteps;

@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    LoadingPageSteps loadingPageSteps = new LoadingPageSteps();
    MainPageSteps mainPageSteps = new MainPageSteps();
    AuthPageSteps authPageSteps = new AuthPageSteps();
    NewsPageSteps newsPageSteps = new NewsPageSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    NewsFilterSteps newFilterSteps = new NewsFilterSteps();

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
    @Feature(value = "Страница новостей")
    @Story("Наличие всех элементов на странице Новости")
    public void shouldDisplayCompleteContentOnNewsPage() {
        mainPageSteps.openNewsPage();
        newsPageSteps.newsPageElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Переход в Панель управления и наличие всех элементов")
    public void shouldAccessControlPanelWithAllElements() {
        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.controlPanelElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Возврат на Главную страницу со страницы Новости")
    public void shouldReturnToHomePageFromNewsPageWithFullContentCheck() {
        mainPageSteps.openNewsPage();
        newsPageSteps.newsPageElementsAreVisible();
        newsPageSteps.checkGoBackMainPage();
        mainPageSteps.mainPageElementsAreVisible();
    }
}