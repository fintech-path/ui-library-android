package com.hcxc.obfuscatededittext;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class ObfuscatedEditLayoutTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        ObfuscatedEditLayout layout = new ObfuscatedEditLayout(null);
    }
}