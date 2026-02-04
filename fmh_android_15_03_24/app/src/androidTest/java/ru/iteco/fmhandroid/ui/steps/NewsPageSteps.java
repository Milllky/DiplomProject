package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.withIndex;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helper.DataHelper;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

public class NewsPageSteps {
    NewsPage newsPage = new NewsPage();
    MainPage mainPage = new MainPage();

    public void newsListLoad() {
        Allure.step("Загрузка списка новостей");
        elementWaiting(withId(R.id.news_list_recycler_view), 5000);
    }

    public void newsPageElementsAreVisible() {
        Allure.step("Все элементы страницы новостей видны");
        newsPage.logo.check(matches(isDisplayed()));
        newsPage.title.check(matches(isDisplayed()));
        newsPage.sort.check(matches(isDisplayed()));
        newsPage.filter.check(matches(isDisplayed()));
        newsPage.controlPanelButton.check(matches(isDisplayed()));
        newsPage.allNewsBlock.check(matches(isDisplayed()));
    }

    public void checkGoBackMainPage() {
        Allure.step("Возврат на Главную страницу со страницы Новости");
        mainPage.menuButton.perform(click());
        mainPage.mainOfMenu.perform(click());
    }

    public void clickSortNewsButton() {
        Allure.step("Нажать кнопку сортировки");
        newsPage.sort.perform(click());
    }

    public void openNewsFilter() {
        Allure.step("Открыть расширенный фильтр");
        newsPage.filter.perform(click());
    }

    public void clickOneNewsItem(int index) {
        Allure.step("Развернуть/свернуть новость");
        newsPage.childNewsButton.perform(actionOnItemAtPosition(index, click()));
    }

    public String getFirstNewsTitle(int index) {
        Allure.step("Заголовок первой новости");
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), index)));
    }

    public String getCreateNewsDescription(int index) {
        Allure.step("Описание созданной новости");
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), index)));
    }
}