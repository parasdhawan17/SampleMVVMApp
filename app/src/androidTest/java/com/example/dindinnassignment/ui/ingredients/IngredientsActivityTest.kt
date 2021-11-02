package com.example.dindinnassignment.ui.ingredients


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.dindinnassignment.R
import com.example.dindinnassignment.ui.orders.OrdersActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class IngredientsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(IngredientsActivity::class.java)

    @Test
    fun ingredientsActivityTest() {

        val editText = onView(withId(R.id.etSearch))
        editText.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withText("BENTO"),
                withParent(
                    allOf(
                        withContentDescription("Bento"),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("BENTO")))

        val textView2 = onView(
            allOf(
                withText("MAIN"),
                withParent(
                    allOf(
                        withContentDescription("Main"),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("MAIN")))

        val textView3 = onView(
            allOf(
                withText("APPETIZER"),
                withParent(
                    allOf(
                        withContentDescription("Appetizer"),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("APPETIZER")))
    }

}
