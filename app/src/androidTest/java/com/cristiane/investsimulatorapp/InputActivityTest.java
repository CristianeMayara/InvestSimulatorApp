package com.cristiane.investsimulatorapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import com.cristiane.investsimulatorapp.ui.InputActivity;

import android.support.annotation.StringRes;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by cristiane on 10/07/2018.
 */

@RunWith(AndroidJUnit4.class)
public class InputActivityTest {
    @Rule
    public ActivityTestRule<InputActivity> activityTestRule = new ActivityTestRule<>(InputActivity.class);

    @Test
    public void investedAmountIsEmpty() {
        onView(withId(R.id.et_value_input)).perform(clearText());
        onView(withId(R.id.bt_simulate)).perform(click());
        onView(withId(R.id.et_value_input)).check(matches(withError(getString(R.string.error_invalid_value))));
    }

    @Test
    public void maturityDateIsEmpty() {
        onView(withId(R.id.et_date_input)).perform(clearText());
        onView(withId(R.id.bt_simulate)).perform(click());
        onView(withId(R.id.et_date_input)).check(matches(withError(getString(R.string.error_invalid_date))));
    }

    @Test
    public void rateIsEmpty() {
        onView(withId(R.id.et_cdi_percentage_input)).perform(clearText());
        onView(withId(R.id.bt_simulate)).perform(click());
        onView(withId(R.id.et_cdi_percentage_input)).check(matches(withError(getString(R.string.error_invalid_rate))));
    }

    @Test
    public void valueInputIsInvalid() {
        onView(withId(R.id.et_value_input)).perform(typeText("0.0"), closeSoftKeyboard());
        onView(withId(R.id.bt_simulate)).perform(click());
        onView(withId(R.id.et_value_input)).check(matches(withError(getString(R.string.error_invalid_value))));
    }

    @Test
    public void rateIsInvalid() {
        onView(withId(R.id.et_cdi_percentage_input)).perform(typeText("0.0"), closeSoftKeyboard());
        onView(withId(R.id.bt_simulate)).perform(click());
        onView(withId(R.id.et_cdi_percentage_input)).check(matches(withError(getString(R.string.error_invalid_rate))));
    }

    private String getString(@StringRes int resourceId) {
        return activityTestRule.getActivity().getString(resourceId);
    }

    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof EditText) {
                    return ((EditText)item).getError().toString().equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message" + expected + ", find it!");
            }
        };
    }
}
