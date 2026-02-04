package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.pages.NewsFilter;

public class NewsFilterSteps {

    NewsFilter newsFilterPage = new NewsFilter();

    public void newsFilterPageElementsAreVisible() {
        Allure.step("Все элементы фильтра новостей видны");
        newsFilterPage.titlePage.check(matches(isDisplayed()));
        newsFilterPage.categoryText.check(matches(isDisplayed()));
        newsFilterPage.dateStartText.check(matches(isDisplayed()));
        newsFilterPage.dateEndText.check(matches(isDisplayed()));
        newsFilterPage.filterActive.check(matches(isDisplayed()));
        newsFilterPage.filterNotActive.check(matches(isDisplayed()));
        newsFilterPage.filterButton.check(matches(isDisplayed()));
        newsFilterPage.cancelButton.check(matches(isDisplayed()));
    }

    public void clickFilterButton() {
        Allure.step("Нажатие кнопки Фильтровать");
        newsFilterPage.filterButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Нажатие кнопки Отмена");
        newsFilterPage.cancelButton.perform(click());
    }

    public void clickOKButton() {
        Allure.step("Нажатие кнопки ОК в сообщении");
        newsFilterPage.okButtonMessage.perform(click());
    }

    public void fillInStartDateField(String startDate) {
        Allure.step("Поле начальная дата - ввод данных");
        newsFilterPage.dateStartText.perform(replaceText(startDate));
    }

    public void fillInEndDateField(String endDate) {
        Allure.step("Поле конечная дата - ввод данных");
        newsFilterPage.dateEndText.perform(replaceText(endDate));
    }

    public void clickActiveCheckBox() {
        Allure.step("Нажать чекбокс - Активна");
        newsFilterPage.filterActive.perform(click());
    }

    public void clickNotActiveCheckBox() {
        Allure.step("Нажать чекбокс - НЕ активна");
        newsFilterPage.filterNotActive.perform(click());
    }

    public void checkBoxStatusActive(boolean checked) {
        Allure.step("Проверка нажатия - Активна");

        if (checked) {
            newsFilterPage.filterActive.check(matches(isChecked()));
        } else {
            newsFilterPage.filterActive.check(matches(isNotChecked()));
        }
    }

    public void checkBoxStatusNotActive(boolean checked) {
        Allure.step("Проверка нажатия - НЕ активна");

        if (checked) {
            newsFilterPage.filterNotActive.check(matches(isChecked()));
        } else {
            newsFilterPage.filterNotActive.check(matches(isNotChecked()));
        }
    }

}