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

import android.graphics.Rect;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.view.View;

import java.util.regex.Pattern;

/**
 *
 */
public class BankCardFormatValidTransformationMethod implements TransformationMethod {

    private final String regex;

    private final int startLength;
    private final int endLength;

    public BankCardFormatValidTransformationMethod() {
        regex = "[0-9]{15,}";
        startLength = 6;
        endLength = 4;
    }

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        if (!TextUtils.isEmpty(source) && source.length() >= 15 && Pattern.compile(regex).matcher(source).find()) {
            String sourceString = source.toString();
            String startString = sourceString.substring(0, startLength);
            String endString = sourceString.substring(source.length() - endLength, source.length());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(startString);

            for (int i = 0; i < source.length() - startLength - endLength; i++) {
                stringBuilder.append("*");
            }
            stringBuilder.append(endString);
            return stringBuilder.toString();
        } else {
            return source;
        }
    }

    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused,
                               int direction, Rect previouslyFocusedRect) {
    }
}
