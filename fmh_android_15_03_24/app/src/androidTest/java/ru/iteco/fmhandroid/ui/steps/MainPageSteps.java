package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.MainPage;
public class MainPageSteps {
    MainPage mainPage = new MainPage();

    public void mainPageIsLoad() {
        Allure.step("Загрузка страницы");
        elementWaiting(withId(R.id.all_news_text_view), 5000);
    }

    public void mainPageElementsAreVisible() {
        Allure.step("Все элементы главной страницы видны");
        mainPage.profileButton.check(matches(isDisplayed()));
        mainPage.menuButton.check(matches(isDisplayed()));
        mainPage.ourMissionButton.check(matches(isDisplayed()));
        mainPage.titleOfNewsContainer.check(matches(isDisplayed()));
        mainPage.allNewsButton.check(matches(isDisplayed()));
    }

    public void openNewsPage() {
        Allure.step("Открытие страницы Новости");
        mainPage.menuButton.perform(click());
        mainPage.newsOfMenu.perform(click());
    }

    public void openAboutPage() {
        Allure.step("Открытие страницы О приложении");
        mainPage.menuButton.perform(click());
        mainPage.aboutOfMenu.perform(click());
    }

    public void openQuotesPage() {
        Allure.step("Открытие страницы Цитаты");
        mainPage.ourMissionButton.perform(click());
    }

    public void logOut() {
        Allure.step("Выход из профиля");
        mainPage.profileButton.perform(click());
        mainPage.logOutButton.perform(click());
    }

    public void openAllNews() {
        Allure.step("Переход ко всем новостям");
        mainPage.allNewsButton.perform(click());
    }

}
