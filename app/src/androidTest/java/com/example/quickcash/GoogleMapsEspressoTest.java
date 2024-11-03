package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GoogleMapsEspressoTest {

    @Rule
    public ActivityScenarioRule<MapActivity> activityRule
            = new ActivityScenarioRule<>(MapActivity.class);


    @Test
    public void testJobLocationsDisplayedOnMap() {
        // Check that the map is displayed
        onView(ViewMatchers.withId(R.id.mapView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check that job markers are displayed on the map
        activityRule.getScenario().onActivity(activity -> {
            GoogleMap map = activity.getMap();
            assertNotNull("Map should be initialized", map);
            assertTrue("Map should contain job markers", map.getMarkers().size() > 0);
        });
    }

    @Test
    public void testClickJobMarkerDisplaysDetails() {
        // Simulate a click on a marker (assuming there is at least one marker on the map)
        activityRule.getScenario().onActivity(activity -> {
            GoogleMap map = activity.getMap();
            assertNotNull("Map should be initialized", map);
            Marker firstMarker = map.getMarkers().get(0);
            firstMarker.performClick();
        });

        // Check that job details pop-up is displayed after clicking a marker
        onView(ViewMatchers.withId(R.id.jobDetailsPopup))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
