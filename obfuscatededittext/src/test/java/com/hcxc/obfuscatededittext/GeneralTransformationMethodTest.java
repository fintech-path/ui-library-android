package com.hcxc.obfuscatededittext;

import static org.junit.Assert.*;

import android.text.SpannableString;
import android.text.SpannedString;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class GeneralTransformationMethodTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTransformation() {
        GeneralTransformationMethod method =
                new ObfuscatedTransformationMethodBuilder(3, 4)
                        .obfuscatedPatternCharacter('*').build();
        assertEquals("11**", method.getTransformation("1111", null));
    }

    @Test
    public void charAt() {
        GeneralTransformationMethod method =
                new ObfuscatedTransformationMethodBuilder(3, 4)
                        .obfuscatedPatternCharacter('*').build();
        assertEquals('*',method.getTransformation("1111", null).charAt(2));
    }

    @Test
    public void onFocusChanged() {
    }
}