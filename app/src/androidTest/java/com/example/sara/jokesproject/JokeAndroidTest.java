package com.example.sara.jokesproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by sara on 9/25/2017.
 */

@RunWith(AndroidJUnit4.class)
public class JokeAndroidTest {


    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }
    @Test
    public void testGetJoke() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_jokes))
        .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.tv_joke))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.tv_joke))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
}
