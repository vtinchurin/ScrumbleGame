package ru.vtinch.scramblegame.game

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R

class InputUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) {

    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.inputEditText),
            isAssignableFrom(TextInputEditText::class.java)
        )
    )

    fun addInput(text: String) {
        interaction.perform(click()).perform(typeText(text))
    }

    fun removeLetter() {
        interaction.perform(click()).perform(pressKey(KeyEvent.KEYCODE_DEL))
    }

}
