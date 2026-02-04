package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pages.NewsEditor;

public class NewsEditorSteps {

    NewsEditor newsEditorPage = new NewsEditor();

    public void newsEditorPageElementsAreVisible() {
        Allure.step("Все элементы панели редактирования новостей видны");
        newsEditorPage.titlePage.check(matches(isDisplayed()));
        newsEditorPage.categoryText.check(matches(isDisplayed()));
        newsEditorPage.titleText.check(matches(isDisplayed()));
        newsEditorPage.descriptionText.check(matches(isDisplayed()));
        newsEditorPage.publicationDate.check(matches(isDisplayed()));
        newsEditorPage.time.check(matches(isDisplayed()));
        newsEditorPage.switcher.check(matches(isDisplayed()));
        newsEditorPage.saveButton.check(matches(isDisplayed()));
        newsEditorPage.cancelButton.check(matches(isDisplayed()));
    }

    public void fillInNewsCategoryField(String text) {
        Allure.step("Ввод данных в поле Категория");
        newsEditorPage.categoryText.perform(replaceText(text));
    }

    public void fillInNewsTitleField(String text) {
        Allure.step("Ввод данных в поле Заголовок");
        newsEditorPage.titleText.perform(replaceText(text));
    }

    public void fillInPublicDateField(String text) {
        Allure.step("Ввод данных в поле Дата публикации");
        newsEditorPage.publicationDate.perform(replaceText(text));
    }

    public void fillInTimeField(String text) {
        Allure.step("Ввод данных в поле Время");
        newsEditorPage.time.perform(replaceText(text));
    }

    public void fillInNewsDescriptionField(String text) {
        Allure.step("Ввод данных в поле Описание");
        newsEditorPage.descriptionText.perform(replaceText(text));
    }

    public void EditNewsFields(String category, String title, String publicationDate,
                               String publicationTime, String description) {
        Allure.step("Перезаполнение/редактирование данных новости");
        fillInNewsCategoryField(category);
        fillInNewsTitleField(title);
        fillInNewsDescriptionField(description);
        fillInPublicDateField(publicationDate);
        fillInTimeField(publicationTime);
    }

    public void changeStatus() {
        Allure.step("Поменять статус новости");
        newsEditorPage.switcher.perform(click());
    }

    public void clickSaveButton() {
        Allure.step("Нажатие кнопки Сохранить");
        newsEditorPage.saveButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Нажатие кнопки Отмена");
        newsEditorPage.cancelButton.perform(click());
    }

    public void clickOKButton() {
        Allure.step("Нажатие кнопки ОК в сообщении");
        newsEditorPage.okButtonMessage.perform(click());
    }
}