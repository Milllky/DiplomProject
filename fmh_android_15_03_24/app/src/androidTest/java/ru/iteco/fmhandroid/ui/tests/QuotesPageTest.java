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
import ru.iteco.fmhandroid.ui.steps.QuotesPageSteps;

@RunWith(AllureAndroidJUnit4.class)
public class QuotesPageTest {

    LoadingPageSteps loadingPageSteps = new LoadingPageSteps();
    AuthPageSteps authPageSteps = new AuthPageSteps();
    MainPageSteps mainPageSteps = new MainPageSteps();
    QuotesPageSteps quotesPageSteps = new QuotesPageSteps();
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
        mainPageSteps.openQuotesPage();
    }

    @Test
    @Feature(value = "Страница с цитатами")
    @Story("Наличие всех элементов страницы")
    public void shouldDisplayAllElementsOnQuotesPage() {
        quotesPageSteps.quotesPageElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница с цитатами")
    @Story("Развернуть цитату и свернуть")
    public void shouldToggleQuoteDisplay() {
        String quoteTestText = "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер";

        quotesPageSteps.checkQuote(0);
        quotesPageSteps.descriptionIsDisplay(quoteTestText);
        quotesPageSteps.checkQuote(0);
    }
}