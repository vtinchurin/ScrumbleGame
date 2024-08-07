package ru.vtinch.scramblegame.game

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class BackgroundColourMatcher(private val bgResId: Int) : TypeSafeMatcher<View>() {

    private var res: Drawable? = null

    override fun describeTo(description: Description?) {
        description?.appendText("Color did not match $bgResId was $res");
    }

    override fun matchesSafely(item: View?): Boolean {
        item?.background ?: return false
        val resources = item.resources
        val drawable = ResourcesCompat.getDrawable(resources, bgResId, null)
        //val colorFromRes = ResourcesCompat.getColor(resources,bgResId,null)
        res = (item.background as Drawable).current
        return res == drawable
//        mColorFromView = (item.background as ColorDrawable).color
//        return mColorFromView == colorFromRes
    }
}