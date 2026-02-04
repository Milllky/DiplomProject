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
import ru.iteco.fmhandroid.ui.steps.LoadingPageSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;
import ru.iteco.fmhandroid.ui.steps.NewsPageSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
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
    @Feature(value = "Главная страница")
    @Story("Переход ко всем новостям с главной страницы")
    public void shouldAccessAllNewsFromHomePage() {
        mainPageSteps.openAllNews();
    }

    @Test
    @Feature(value = "Главная страница")
    @Story("Развернуть новость на главной странице")
    public void shouldExpandSingleNewsOnHomePag() {
        newsPageSteps.clickOneNewsItem(0);
    }

}
