package com.mytaxi.android_demo;

import android.Manifest;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.rule.GrantPermissionRule.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MyTaxiSearchDriverTest {

    public ActivityTestRule<MainActivity> mActivityRule = new  ActivityTestRule<MainActivity>(MainActivity.class);
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);
    @Rule
    public final RuleChain mRuleChain = RuleChain.outerRule(mActivityRule)
            .around(mRuntimePermissionRule);

    public String driverNameText="Sarah Scott";
    private MainActivity mActivity = null;

    @Before
    public void initiateStringParameters()  {
        mActivity = mActivityRule.getActivity();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void searchDriverAndCall() {
        Log.i("@Test","Perform Search driver test case and call");
        Espresso.onView(withId(R.id.textSearch)).perform(ViewActions.typeText("sa"));
        Espresso.onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())).perform(click());
        Espresso.onView(withId(R.id.textViewDriverName)).check(matches(withText(driverNameText)));
        Espresso.onView((withId(R.id.fab))).check(matches(isDisplayed())).perform(ViewActions.click());
    }

    @After
    public void tearDown()
    {
        Log.e("@Test","End of Search Driver Test Case");
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
