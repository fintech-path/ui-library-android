/*
 * Copyright 2007-2022 Home Credit Xinchi Consulting Co. Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2. *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hcxc.obfuscatededittext;

import static com.hcxc.obfuscatededittext.ObfuscatedTransformationMethodBuilder.PresetMode.BANK_CARD;
import static com.hcxc.obfuscatededittext.ObfuscatedTransformationMethodBuilder.PresetMode.ID_CARD;
import static com.hcxc.obfuscatededittext.ObfuscatedTransformationMethodBuilder.PresetMode.MOBILE_PHONE;

import android.text.method.TransformationMethod;

import androidx.annotation.NonNull;

public class ObfuscatedTransformationMethodBuilder {

    private char[] ignoreObfuscatedCharacters;
    private int mStartIndexOfObfuscated;
    private int mEndIndexOfObfuscated;
    private char mObfuscatedPatternCharacter;


    public ObfuscatedTransformationMethodBuilder(int startIndex, int length) {
        this.mStartIndexOfObfuscated = startIndex;
        this.mEndIndexOfObfuscated = startIndex + length - 1;
    }

    public ObfuscatedTransformationMethodBuilder obfuscatedPatternCharacter(char c) {
        this.mObfuscatedPatternCharacter = c;
        return this;
    }

    public ObfuscatedTransformationMethodBuilder IgnoreObfuscatedCharacters(char[] ignoreChars) {
        this.ignoreObfuscatedCharacters = ignoreChars;
        return this;
    }


    public GeneralTransformationMethod build() {
        return new GeneralTransformationMethod(mStartIndexOfObfuscated, mEndIndexOfObfuscated, mObfuscatedPatternCharacter);
    }

    public static TransformationMethod getObfuscatedTransformationMethodByPresetMode(@NonNull ObfuscatedTransformationMethodBuilder.PresetMode mode) {
        if (MOBILE_PHONE == mode) {
            return new ObfuscatedTransformationMethodBuilder(4, 4).obfuscatedPatternCharacter('*').build();
        } else if (ID_CARD == mode) {
            return new ObfuscatedTransformationMethodBuilder(7, 12).obfuscatedPatternCharacter('*').build();
        } else if (BANK_CARD == mode) {
            return new BankCardFormatValidTransformationMethod();
        } else {
            return null;
        }
    }

    public enum PresetMode {
        MOBILE_PHONE,
        BANK_CARD,
        ID_CARD
    }
}
