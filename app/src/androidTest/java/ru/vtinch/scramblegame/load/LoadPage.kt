package ru.vtinch.scramblegame.load

import android.widget.LinearLayout
import ru.easycode.zerotoheroandroidtdd.waitTillDisplayed
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractPage
import ru.vtinch.scramblegame.core.elements.ButtonUi
import ru.vtinch.scramblegame.core.elements.DefaultTextUi

class LoadPage : AbstractPage(
    containerId = R.id.load_container,
    classType = LinearLayout::class.java
) {

    private val errorUi: DefaultTextUi = DefaultTextUi.Base(
        id = R.id.error_text,
        textId = R.string.no_connectoin,
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

    fun waitTillLoad() {
        waitTillDisplayed(R.id.progressUi,3000)
    }





}