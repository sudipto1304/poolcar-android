package com.poolcar.test;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.MonitoringInstrumentation;
import android.util.Log;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;
import cucumber.api.junit.Cucumber;


public class Instrumentation extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore mInstrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        Context inst_context = InstrumentationRegistry.getTargetContext();
        Log.d("Cucumber: ", "Instrumentation Context Package " + inst_context.getPackageName());
        mInstrumentationCore.create(arguments);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();

        waitForIdleSync();
        mInstrumentationCore.start();
    }
}