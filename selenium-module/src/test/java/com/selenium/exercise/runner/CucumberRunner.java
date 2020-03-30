package com.selenium.exercise.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin     =   {"pretty","html:target/cucumber" , "json:target/cucumber.json" },
        features   =   {"src/test/resources/features"},
        glue       =   {"com.selenium.exercise.steps"},
        tags       =   {"@FindFlightChrome"},
        dryRun     =   false,
        strict     =   false,
        monochrome =   false
        )
public class CucumberRunner {

}
