package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.helper.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.getCurrentTime;

import android.view.View;

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
import ru.iteco.fmhandroid.ui.steps.NewsCreatorSteps;

@RunWith(AllureAndroidJUnit4.class)
public class CreateNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    LoadingPageSteps loadingPageSteps = new LoadingPageSteps();
    MainPageSteps mainPageSteps = new MainPageSteps();
    AuthPageSteps authPageSteps = new AuthPageSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    NewsCreatorSteps newsCreatorSteps = new NewsCreatorSteps();
    private View decorView;

    @Before
    public void setUp() {
        loadingPageSteps.loadApp();
        try {
            mainPageSteps.mainPageIsLoad();
        } catch (Exception e) {
            authPageSteps.validLogIn();
            mainPageSteps.mainPageIsLoad();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Переход к созданию новостей и наличие всех элементов")
    public void shouldDisplayAllElementsOnCreateNewsPage() {
        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        newsCreatorSteps.newsCreatorPageElementsAreVisible();
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Cоздание новости с валидными данными")
    public void shouldSuccessfullyCreateNewsWithValidData() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость тест";
        String description = "Описание новости тест";
        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        newsCreatorSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        newsCreatorSteps.clickSaveButton();
        controlPanelSteps.checkIfNewsWithTitle(title);
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Cоздание новости с пустыми данными")
    public void shouldFailToCreateNewsWithEmptyFieldsAndShowMessage() {

        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        newsCreatorSteps.clickSaveButton();
        newsCreatorSteps.checkToastMessageText("Заполните пустые поля", decorView);
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Отменить создание новости")
    public void shouldReturnToControlPanelOnNewsCreationCancellation() {
        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        newsCreatorSteps.clickCancelButton();
        newsCreatorSteps.clickOKButton();
        controlPanelSteps.controlPanelElementsAreVisible();
    }
}