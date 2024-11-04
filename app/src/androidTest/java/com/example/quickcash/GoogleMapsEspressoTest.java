package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.quickcash.maps.MapsActivity;
import com.google.android.gms.maps.GoogleMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GoogleMapsEspressoTest {

    @Rule
    public ActivityScenarioRule<MapsActivity> activityRule
            = new ActivityScenarioRule<>(MapsActivity.class);


    @Test
    public void testMap() {
        // Check that the map is displayed
        onView(ViewMatchers.withId(R.id.map))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check that map has been initialized
        activityRule.getScenario().onActivity(activity -> {
            GoogleMap map = activity.getMap();
            assertNotNull("Map should be initialized", map);
        });
    }
}
