package ru.vtinch.scramblegame.game

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class BackgroundDrawableMatcher(private val colorId: Int) : TypeSafeMatcher<View>() {

   private var strokeColor : ColorStateList? = null

    override fun describeTo(description: Description?) {
        description?.appendText("Color did not match $colorId was $strokeColor");
    }

    override fun matchesSafely(item: View?): Boolean {
        item?.background ?: return false

        val resources = item.getContext().getResources();
        val resourceName = resources.getResourceEntryName(colorId);
        val drawable = (resources.getDrawable(colorId,null) as GradientDrawable).color
        strokeColor =(item.background as GradientDrawable).color
        //val colorFromRes = ResourcesCompat.getColor(resources,bgResId,null)

        return strokeColor == drawable
//        mColorFromView = (item.background as ColorDrawable).color
//        return mColorFromView == colorFromRes
    }
}

/**
 * binding.answerText.setBackgroundResource(R.drawable.bg_gray)
 *         val bg = binding.answerText.background as GradientDrawable
 *         bg.mutate()
 *         bg.setStroke(8,R.color.red)
 */