package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.ui.ResetPasswordActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PasswordResetEspressoTest {

    @Test
    public void testValidEmailEntered() {
        // Context of the app under test.
        ActivityScenario.launch(ResetPasswordActivity.class);

        onView(withId(R.id.resetEmailBox)).perform(ViewActions.typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.sendButton)).perform(click());
    }
}