package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pages.NewsCreator;

public class NewsCreatorSteps {

    NewsCreator newsCreatorPage = new NewsCreator();

    public void newsCreatorPageElementsAreVisible() {
        Allure.step("Все элементы панели создания новостей видны");
        newsCreatorPage.titlePage.check(matches(isDisplayed()));
        newsCreatorPage.categoryText.check(matches(isDisplayed()));
        newsCreatorPage.titleText.check(matches(isDisplayed()));
        newsCreatorPage.descriptionText.check(matches(isDisplayed()));
        newsCreatorPage.publicationDate.check(matches(isDisplayed()));
        newsCreatorPage.time.check(matches(isDisplayed()));
        newsCreatorPage.switcher.check(matches(isDisplayed()));
        newsCreatorPage.saveButton.check(matches(isDisplayed()));
        newsCreatorPage.cancelButton.check(matches(isDisplayed()));
    }

    public void fillInNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Категория");
        newsCreatorPage.categoryText.perform(replaceText(text));
    }

    public void fillInNewsTitleField(String text) {
        Allure.step("Ввод данных в поле Заголовок");
        newsCreatorPage.titleText.perform(replaceText(text));
    }

    public void fillInPublicDateField(String text) {
        Allure.step("Ввод данных в поле Дата публикации");
        newsCreatorPage.publicationDate.perform(replaceText(text));
    }

    public void fillInTimeField(String text) {
        Allure.step("Ввод данных в поле Время");
        newsCreatorPage.time.perform(replaceText(text));
    }

    public void fillInNewsDescriptionField(String text) {
        Allure.step("Ввод данных в поле Описание");
        newsCreatorPage.descriptionText.perform(replaceText(text));
    }

    public void createNews(String category, String title, String publicationDate,
                           String publicationTime, String description) {
        Allure.step("Ввод данных для создания новости");
        fillInNewsCategoryField(category);
        fillInNewsTitleField(title);
        fillInPublicDateField(publicationDate);
        fillInTimeField(publicationTime);
        fillInNewsDescriptionField(description);
    }

    public void clickSaveButton() {
        Allure.step("Нажатие кнопки Сохранить");
        newsCreatorPage.saveButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Нажатие кнопки Отмена");
        newsCreatorPage.cancelButton.perform(click());
    }

    public void clickOKButton() {
        Allure.step("Нажатие кнопки ОК в сообщении");
        newsCreatorPage.okButtonMessage.perform(click());
    }

    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}