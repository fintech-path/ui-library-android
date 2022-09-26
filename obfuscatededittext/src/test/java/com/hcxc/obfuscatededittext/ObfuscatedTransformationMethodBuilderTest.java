package com.hcxc.obfuscatededittext;

import static com.hcxc.obfuscatededittext.ObfuscatedTransformationMethodBuilder.PresetMode.BANK_CARD;
import static com.hcxc.obfuscatededittext.ObfuscatedTransformationMethodBuilder.PresetMode.ID_CARD;
import static com.hcxc.obfuscatededittext.ObfuscatedTransformationMethodBuilder.PresetMode.MOBILE_PHONE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import android.text.method.TransformationMethod;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

public class ObfuscatedTransformationMethodBuilderTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initBuilder() {
        GeneralTransformationMethod method =
                new ObfuscatedTransformationMethodBuilder(3, 4)
                        .obfuscatedPatternCharacter('*').build();
        Assert.assertEquals(3, (int) Whitebox.getInternalState(method, "mStartIndexOfObfuscated"));
        Assert.assertEquals(6, (int) Whitebox.getInternalState(method, "mEndIndexOfObfuscated"));
        Assert.assertEquals('*', (char) Whitebox.getInternalState(method, "mObfuscatedPatternCharacter"));
    }

    @Test
    public void getObfuscatedTransformationMethodByPresetMode_Bank() {
        Assert.assertTrue(ObfuscatedTransformationMethodBuilder.getObfuscatedTransformationMethodByPresetMode(BANK_CARD) instanceof BankCardFormatValidTransformationMethod);
    }

    @Test
    public void getObfuscatedTransformationMethodByPresetMode_IdCard() {
        TransformationMethod method = ObfuscatedTransformationMethodBuilder.getObfuscatedTransformationMethodByPresetMode(ID_CARD);
        Assert.assertTrue(method instanceof GeneralTransformationMethod);
        Assert.assertEquals(7, (int) Whitebox.getInternalState(method, "mStartIndexOfObfuscated"));
        Assert.assertEquals(18, (int) Whitebox.getInternalState(method, "mEndIndexOfObfuscated"));
        Assert.assertEquals('*', (char) Whitebox.getInternalState(method, "mObfuscatedPatternCharacter"));
    }

    @Test
    public void getObfuscatedTransformationMethodByPresetMode_Phone() {
        TransformationMethod method = ObfuscatedTransformationMethodBuilder.getObfuscatedTransformationMethodByPresetMode(MOBILE_PHONE);
        Assert.assertTrue(method instanceof GeneralTransformationMethod);
        Assert.assertEquals(4, (int) Whitebox.getInternalState(method, "mStartIndexOfObfuscated"));
        Assert.assertEquals(7, (int) Whitebox.getInternalState(method, "mEndIndexOfObfuscated"));
        Assert.assertEquals('*', (char) Whitebox.getInternalState(method, "mObfuscatedPatternCharacter"));
    }
}