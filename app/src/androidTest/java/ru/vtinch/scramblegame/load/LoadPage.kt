package ru.vtinch.scramblegame.load

import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractPage
import ru.vtinch.scramblegame.core.elements.ButtonUi
import ru.vtinch.scramblegame.core.elements.DefaultTextUi
import ru.vtinch.scramblegame.core.waitTillDisplayed

class LoadPage : AbstractPage(
    containerId = R.id.load_container,
    classType = LinearLayout::class.java
) {

    private val errorUi: DefaultTextUi = DefaultTextUi.Base(
        id = R.id.error_text,
        containerClassTypeMatcher = containerIdMatcher,
        containerIdMatcher = containerIdMatcher,
    )

    private val progressUi = ProgressUi(
        containerClassTypeMatcher = containerIdMatcher,
        containerIdMatcher = containerIdMatcher,
    )

    private val retryUi: ButtonUi = ButtonUi.Base(
        containerClassTypeMatcher = containerIdMatcher,
        containerIdMatcher = containerIdMatcher,
        id = R.id.retry_button,
        text = R.string.retry,
    )

    fun assertErrorState() {
        progressUi.assertNotVisible()
        errorUi.assertVisible()
        retryUi.assertEnabled()
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun assertLoading() {
        progressUi.assertVisible()
        errorUi.assertNotVisible()
        retryUi.assertNotVisible()
    }

    fun waitTillError() {
        onView(isRoot()).perform(waitTillDisplayed(R.id.error_text, 2000))
    }

}