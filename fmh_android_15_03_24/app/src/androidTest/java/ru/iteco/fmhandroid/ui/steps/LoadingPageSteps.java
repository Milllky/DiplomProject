package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.helper.DataHelper.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.LoadingPage;
public class LoadingPageSteps {
    LoadingPage loadingPage = new LoadingPage();

    public void checkThatDownloadPageContentIsFull() {
        Allure.step("Наличие всех элементов экрана загрузки");
        loadingPage.splashImage.check(matches(isDisplayed()));
        loadingPage.splashProgressIndicator.check(matches(isDisplayed()));
        loadingPage.splashText.check(matches(isDisplayed()));
    }

    public void loadApp() {
        Allure.step("Загрузка приложения");
        elementWaiting(withId(R.id.splashscreen_image_view), 10000);
    }
}
