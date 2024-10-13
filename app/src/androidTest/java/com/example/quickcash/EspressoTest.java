package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Test
    public void fillOutResetEmail() {
        // Context of the app under test.

        ActivityScenario.launch(PasswordReset.class);

        onView(withId(R.id.emailBox)).perform(ViewActions.typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.sendButton)).perform(click());


    }
}