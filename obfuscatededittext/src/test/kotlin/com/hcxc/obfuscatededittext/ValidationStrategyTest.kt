package com.hcxc.obfuscatededittext

import android.content.Context
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ValidationStrategyTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_PhoneValidationStrategy() {
        val context = mockk<Context>(relaxed = true)
        val strategy = PhoneValidationStrategy(context)
        assertFalse(strategy.validate("15552257"))
        assertTrue(strategy.validate("15523456789"))
        assertEquals("", strategy.onTextFormatError(""))
    }

    @Test
    fun test_IdCardValidationStrategy() {
        val context = mockk<Context>(relaxed = true)
        val strategy = IdCardValidationStrategy(context)
        assertFalse(strategy.validate("1201013456"))
        assertTrue(strategy.validate("120101199214323456"))
        assertEquals("", strategy.onTextFormatError(""))
    }

    @Test
    fun test_BankCardValidationStrategy() {
        val context = mockk<Context>(relaxed = true)
        val strategy = BankCardValidationStrategy(context)
        assertFalse(strategy.validate("62222434"))
        assertTrue(strategy.validate("6222030405010405833"))
        assertEquals("", strategy.onTextFormatError(""))
    }
}