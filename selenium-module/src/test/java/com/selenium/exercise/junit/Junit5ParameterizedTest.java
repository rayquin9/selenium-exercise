/*
 * package com.selenium.exercise.junit;
 * 
 * import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.params.ParameterizedTest; import
 * org.junit.jupiter.params.provider.CsvSource;
 * 
 * public class Junit5ParameterizedTest {
 * 
 * @DisplayName("Roman numeral")
 * 
 * @ParameterizedTest(name = "\"{0}\" should be {1}")
 * 
 * @CsvSource({ "I, 1", "II, 24", "V, 5" }) void withNiceName(String word, int
 * number) { System.out.println("First arg is " + word + " and second arg is " +
 * number);
 * 
 * if ("II".contentEquals(word)) { org.hamcrest.MatcherAssert.assertThat(number,
 * org.hamcrest.Matchers.is(2)); } }
 * 
 * @Test public void econdTest() { org.hamcrest.MatcherAssert.assertThat(12,
 * org.hamcrest.Matchers.is(12));
 * 
 * } }
 */