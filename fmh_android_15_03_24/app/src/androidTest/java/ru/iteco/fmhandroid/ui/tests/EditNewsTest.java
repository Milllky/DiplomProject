package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.helper.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.getCurrentTime;

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
import ru.iteco.fmhandroid.ui.steps.NewsCreatorSteps;
import ru.iteco.fmhandroid.ui.steps.LoadingPageSteps;
import ru.iteco.fmhandroid.ui.steps.NewsEditorSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;

@RunWith(AllureAndroidJUnit4.class)
public class EditNewsTest {
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
    NewsEditorSteps newsEditorSteps = new NewsEditorSteps();

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
    @Story("Редактирование новости")
    public void shouldSuccessfullyEditNewsAndVerifyChanges() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        String title = "Заголовок";
        String description = "Описание";
        String newTitle = "Заголовок отредактирован";
        String newDescription = "Описание отредактировано";

        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        newsCreatorSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        newsCreatorSteps.clickSaveButton();
        controlPanelSteps.clickEditNews(title);
        newsEditorSteps.newsEditorPageElementsAreVisible();

        newsEditorSteps.EditNewsFields(randomCategory(), newTitle, publicationDate,
                publicationTime, newDescription);
        newsEditorSteps.changeStatus();
        newsEditorSteps.clickSaveButton();
        controlPanelSteps.checkIfNewsWithTitle(newTitle);
    }

    @Test
    @Feature(value = "Страница новостей")
    @Story("Отмена редактирования новости")
    public void shouldCancelEditAndMaintainOriginalNewsDetails() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        String title = "Заголовок тест2";
        String description = "Описание тест2";

        mainPageSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        newsCreatorSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        newsCreatorSteps.clickSaveButton();
        controlPanelSteps.clickEditNews(title);
        newsEditorSteps.newsEditorPageElementsAreVisible();
        newsEditorSteps.changeStatus();
        newsEditorSteps.clickCancelButton();
        newsEditorSteps.clickOKButton();
        controlPanelSteps.controlPanelElementsAreVisible();
    }

}