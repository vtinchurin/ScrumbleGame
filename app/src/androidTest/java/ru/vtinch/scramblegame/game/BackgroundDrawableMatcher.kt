package ru.vtinch.scramblegame.game

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class BackgroundDrawableMatcher(private val backgroundId: Int) : TypeSafeMatcher<View>() {

    private var strokeColor: ColorStateList? = null

    override fun describeTo(description: Description?) {
        description?.appendText("Color did not match $backgroundId was $strokeColor");
    }

    override fun matchesSafely(item: View?): Boolean {
        item?.background ?: return false
        val resources = item.context.resources;
        val drawable = (resources.getDrawable(backgroundId, null) as GradientDrawable).color
        strokeColor = (item.background as GradientDrawable).color
        return strokeColor == drawable

    }
}

