package petros.efthymiou.groovy

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import petros.efthymiou.groovy.playlist.idelingResource

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class PlayListFeature: BaseUITest() {
    val mActivityRule=ActivityTestRule(MainActivity::class.java)
    @Rule get
    @Test
    fun displayScreenTitle() {
       assertDisplayed("Playlists")
    }
    @Test
    fun displayListOfPlayLists(){
      //  Thread.sleep(6000)
        assertRecyclerViewItemCount(R.id.playlist_list,10)
        onView(allOf(withId(R.id.playlist_name), isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.image), isDescendantOfA(nthChildOf(withId(R.id.playlist_list),1))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }
    @Test
    fun displaysLoaderWhileFechingThePlaylist(){
        IdlingRegistry.getInstance().unregister(idelingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoader(){
      //  Thread.sleep(6000)
        assertNotDisplayed(R.id.loader)
    }
    @Test
    fun displayRockImageForRockListsItems(){
      //  Thread.sleep(6000)
        onView(allOf(withId(R.id.image), isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.image), isDescendantOfA(nthChildOf(withId(R.id.playlist_list),3))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }
    @Test
    fun navigateToDetailsScreen(){
        onView(allOf(withId(R.id.image), isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .perform(click())
        assertDisplayed(R.id.playlists_details_root)
    }





}