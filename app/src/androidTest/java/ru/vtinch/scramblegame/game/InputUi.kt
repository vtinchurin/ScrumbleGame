package ru.vtinch.scramblegame.game

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R

class InputUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) {

//    private val layoutInteraction : ViewInteraction = onView(
//        allOf(
//            containerIdMatcher,
//            containerClassTypeMatcher,
//            isAssignableFrom(TextInputLayout::class.java),
//            withId(R.id.inputLayout)
//        )
//    )

    private val inputInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.inputEditText),
            //withParent(withId(R.id.inputLayout)),
            //withParent(isAssignableFrom(TextInputLayout::class.java))
        )
    )

    fun addInput(text: String) {
        inputInteraction.perform(click(),typeText(text))
        inputInteraction.perform(closeSoftKeyboard())
    }

    fun removeLetter() {
        inputInteraction.perform(click(),(pressKey(KeyEvent.KEYCODE_DEL)))
    }

}
