package com.egr423.android.napkin;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnnualPayActivityTest {

    private AnnualPayActivity mAnnualPayActivity = new AnnualPayActivity();

    // Inputs
    private double hourlyPay;
    private double annualPay;
    private int workHoursPerWeek;
    private int workWeeksPerYear;

    @Test
    public void expectedHourlyRateTest() {

    }

    @Test
    public void expectedAnnualPayTest() {
        // The annual pay is supposed to be $72,800.00 if the hourly pay is $35
        hourlyPay = 35.0;
        annualPay = 0.0;
        workWeeksPerYear = 52;
        workHoursPerWeek = 40;

        mAnnualPayActivity.hourlyPay = hourlyPay;
        mAnnualPayActivity.annualPay = annualPay; // reset previous annual pay
        mAnnualPayActivity.workWeeksPerYear = workWeeksPerYear;
        mAnnualPayActivity.workHoursPerWeek = workHoursPerWeek;

        // May need to combine unit and instrumentation tests because I need the current bundle

        Log.d("Expected: ", String.valueOf((hourlyPay * workHoursPerWeek * workWeeksPerYear)));
        Log.d("Actual: ", String.valueOf(mAnnualPayActivity.expectedAnnualPay));

        assertTrue((hourlyPay * workHoursPerWeek * workWeeksPerYear) == mAnnualPayActivity.expectedAnnualPay);
    }
}