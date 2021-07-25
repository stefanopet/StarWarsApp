package com.stefano.starwars.mvp.character_list_mvp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;


import com.stefano.starwars.R;

import junit.framework.TestCase;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class CharacterListViewTest extends TestCase {

    private ActivityScenario<CharacterListView> mActivityTestRule = ActivityScenario.launch(CharacterListView.class);
    private String name = "skywalker";


    @Before
    public void setUp() throws Exception {
        mActivityTestRule = ActivityScenario.launch(CharacterListView.class);
        mActivityTestRule.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void testScenarioSearchForNameSelected(){
        //select search for name
        Espresso.onView(withId(R.id.id_rb)).perform((click()));
        //check radiobutton selected
        Espresso.onView(withId(R.id.id_rb)).check(matches(isChecked()));
        Espresso.onView(withId(R.id.search_all_rb)).check(matches(not(isChecked())));
    }

    @Test
    public void testScenarioSearchAll(){
        //select search for name
        Espresso.onView(withId(R.id.search_all_rb)).perform((click()));
        //check radiobutton selected
        Espresso.onView(withId(R.id.id_rb)).check(matches(not(isChecked())));
        Espresso.onView(withId(R.id.search_all_rb)).check(matches(isChecked()));
    }

    @Test
    public void testScenarioSearchForName(){
        //select search for id
        Espresso.onView(withId(R.id.id_rb)).perform((click()));
        //check radiobutton selected
        Espresso.onView(withId(R.id.id_rb)).check(matches(isChecked()));
        Espresso.onView(withId(R.id.search_all_rb)).check(matches(not(isChecked())));
        //input id beer edit text
        Espresso.onView(withId(R.id.for_id_et)).perform(typeText(name));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.search_btn)).perform(click());

    }

    @Test
    public void testScenarioSearchAllAndScroll(){
        //select search for id
        Espresso.onView(withId(R.id.search_all_rb)).perform((click()));
        //check radiobutton selected
        Espresso.onView(withId(R.id.id_rb)).check(matches(not(isChecked())));
        Espresso.onView(withId(R.id.search_all_rb)).check(matches(isChecked()));
        //perform button click
        Espresso.onView(withId(R.id.search_btn)).perform(click());
        //scroll to position 20
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(scrollToPosition(20));
    }

    @After
    public void tearDown() throws Exception {
    }
}