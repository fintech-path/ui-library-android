package com.hcxc.obfuscatededittext;

import static org.junit.Assert.assertEquals;

import android.text.TextUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(TextUtils.class)
public class BankCardFormatValidTransformationMethodTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(TextUtils.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTransformationNotValid() {
        BankCardFormatValidTransformationMethod method = new BankCardFormatValidTransformationMethod();
        assertEquals("1111", method.getTransformation("1111", null));
    }

    @Test
    public void getTransformationValid() {
        BankCardFormatValidTransformationMethod method = new BankCardFormatValidTransformationMethod();
        assertEquals("622203*******8764", method.getTransformation("62220304050298764", null));
    }

    @Test
    public void onFocusChanged() {
    }
}