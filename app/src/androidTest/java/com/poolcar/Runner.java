package com.poolcar;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith (Cucumber.class)
@CucumberOptions(format = {"pretty"}, features = "features", glue="com.poolcar")
public class Runner {
}
