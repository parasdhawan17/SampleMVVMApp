package com.example.dindinnassignment.ui.orders


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.dindinnassignment.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.junit.After

@LargeTest
@RunWith(AndroidJUnit4::class)
class OrdersActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(OrdersActivity::class.java)

    @Test
    fun ordersActivityTest() {


        val rvOrders = onView(withId(R.id.rvOrders))
        rvOrders.check(matches(isDisplayed()))


        val textView = onView(
            allOf(
                withId(R.id.tvName), withText("Fried Egg"),
                withParent(withParent(withId(R.id.rvIngredients))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Fried Egg")))

    }

    @After
    fun clean(){
        (mActivityTestRule.activity as OrdersActivity).viewModel.clean()
    }

}
