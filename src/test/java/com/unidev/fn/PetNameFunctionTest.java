package com.unidev.fn;

import static org.junit.Assert.assertFalse;

import com.fnproject.fn.testing.FnResult;
import com.fnproject.fn.testing.FnTestingRule;
import org.junit.Rule;
import org.junit.Test;

public class PetNameFunctionTest {

    @Rule
    public final FnTestingRule testing = FnTestingRule.createDefault();

    @Test
    public void shouldReturnGreeting() {
        testing.givenEvent().enqueue();
        testing.thenRun(PetNameFunction.class, "handleRequest");

        FnResult result = testing.getOnlyResult();
        assertFalse(result.getBodyAsString().isEmpty());
    }

}