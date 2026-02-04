package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.ControlPanel;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

public class ControlPanelSteps {

    ControlPanel controlPanel = new ControlPanel();
    NewsPage newsPage = new NewsPage();
    NewsCreatorSteps newsCreatorSteps = new NewsCreatorSteps();


    public void openControlPanelPage() {
        Allure.step("Переход в панель управления со страницы Новости");
        newsPage.controlPanelButton.perform(click());
        elementWaiting(withId(R.id.add_news_image_view), 5000);
    }

    public void controlPanelElementsAreVisible() {
        Allure.step("Наличие всех элементов панели управления");
        elementWaiting(withId(R.id.add_news_image_view), 5000);
        controlPanel.logo.check(matches(isDisplayed()));
        controlPanel.sortButton.check(matches(isDisplayed()));
        controlPanel.filterButton.check(matches(isDisplayed()));
        controlPanel.addNewsButton.check(matches(isDisplayed()));
    }

    public void clickSortNewsButton() {
        Allure.step("Нажать кнопку сортировки");
        controlPanel.sortButton.perform(click());
    }

    public void openNewsFilter() {
        Allure.step("Открыть расширенный фильтр");
        controlPanel.filterButton.perform(click());
    }

    public void openCreateNewsButton() {
        Allure.step("Нажать кнопку создание новости");
        controlPanel.addNewsButton.perform(click());
    }

    public void clickDeleteNews(String newsTitle) {
        Allure.step("Удалить новость с указанным заголовком");
        controlPanel.deleteNewsButton(newsTitle).perform(click());
        newsCreatorSteps.clickOKButton();
    }

    public void clickEditNews(String newsTitle) {
        Allure.step("Нажать кнопку Редактирования новости");
        controlPanel.editNewsButton(newsTitle).perform(click());
    }

    public void checkIfNewsWithTitle(String titleText) {
        Allure.step("Проверка наличия новости с указанным заголовком");
        onView(allOf(withText(titleText), isDisplayed())).check(matches(isDisplayed()));
    }

    public void checkIfNoNewsWithTitle(String titleText) {
        Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(allOf(withText(titleText), isDisplayed())).check(doesNotExist());
    }
}