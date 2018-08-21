package com.mytaxi.android_demo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTaxiLoginTest {

    public ActivityTestRule<AuthenticationActivity> mActivityRule = new  ActivityTestRule<AuthenticationActivity>(AuthenticationActivity.class);
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Rule
    public final RuleChain mRuleChain = RuleChain.outerRule(mActivityRule)
            .around(mRuntimePermissionRule);

    public static String usernamevalue;
    public static String passwordvalue;

    private AuthenticationActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityRule.getActivity();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void verifyCustomerLogin() throws Exception {
        Log.i("@Test","Performing Login Test in myTaxi Application using Valid Credentials");
        HashMap<String,String> credentials=GetLoginCredentials.getLoginCredentials();
        usernamevalue=credentials.get("Userid");
        passwordvalue=credentials.get("Pwd");
        Espresso.onView((withId(R.id.edt_username))).perform(ViewActions.typeText(usernamevalue) , closeSoftKeyboard());
        Espresso.onView((withId(R.id.edt_password))).perform(ViewActions.typeText(passwordvalue) , closeSoftKeyboard());
        Espresso.onView((withId(R.id.btn_login))).perform(click());
        Assert.assertTrue(String.valueOf(true), mActivity.isFinishing());
    }

    @After
    public void tearDown()
    {
        Log.e("@Test","End of myTaxi Login Test Case");
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
