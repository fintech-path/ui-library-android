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
import android.text.Editable;
import android.text.GetChars;
import android.text.Spannable;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;

public class GeneralTransformationMethod implements TransformationMethod {

    private char[] mIgnoreObfuscatedCharacters;
    private boolean isHaveIgnoreObfuscatedCharacters;
    protected int mStartIndexOfObfuscated;
    protected int mEndIndexOfObfuscated;
    protected char mObfuscatedPatternCharacter;

    public GeneralTransformationMethod(int startIndexOfObfuscated, int endIndexOfObfuscated, char obfuscatedPatternCharacter) {
        this(startIndexOfObfuscated, endIndexOfObfuscated, obfuscatedPatternCharacter, false, null);
    }

    public GeneralTransformationMethod(int startIndexOfObfuscated, int endIndexOfObfuscated, char obfuscatedPatternCharacter,
                                       boolean isHaveIgnoreObfuscatedCharacters, char[] ignoreObfuscatedCharacters) {
        mStartIndexOfObfuscated = startIndexOfObfuscated;
        mEndIndexOfObfuscated = endIndexOfObfuscated;
        mObfuscatedPatternCharacter = obfuscatedPatternCharacter;
        mIgnoreObfuscatedCharacters = ignoreObfuscatedCharacters;
        this.isHaveIgnoreObfuscatedCharacters = isHaveIgnoreObfuscatedCharacters;
    }

    public CharSequence getTransformation(CharSequence source, View v) {

        if (mStartIndexOfObfuscated > 0
                && mEndIndexOfObfuscated > 0
                && mEndIndexOfObfuscated >= mStartIndexOfObfuscated
                && mObfuscatedPatternCharacter != '\u0000') {

            if (!(source instanceof Editable)) {

                if (!(source instanceof Spannable)) {
                    /*
                     * The text contains some of the source characters,
                     * but they can be flattened out now instead of
                     * at display time.
                     */
                    if (source instanceof Spanned) {
                        return new SpannedString(new SpannedTransformationCharSequence(
                                (Spanned) source, mStartIndexOfObfuscated, mEndIndexOfObfuscated, mObfuscatedPatternCharacter, isHaveIgnoreObfuscatedCharacters, mIgnoreObfuscatedCharacters));
                    } else {
                        return new TransformationCharSequence(source, mStartIndexOfObfuscated, mEndIndexOfObfuscated, mObfuscatedPatternCharacter, isHaveIgnoreObfuscatedCharacters, mIgnoreObfuscatedCharacters).toString();
                    }
                }
            }

            if (source instanceof Spanned) {
                return new SpannedTransformationCharSequence((Spanned) source, mStartIndexOfObfuscated, mEndIndexOfObfuscated, mObfuscatedPatternCharacter, isHaveIgnoreObfuscatedCharacters, mIgnoreObfuscatedCharacters);
            } else {
                return new TransformationCharSequence(source, mStartIndexOfObfuscated, mEndIndexOfObfuscated, mObfuscatedPatternCharacter, isHaveIgnoreObfuscatedCharacters, mIgnoreObfuscatedCharacters);
            }
        } else {
            Log.d("GeneralTransformationMethod", "para init error");
            return source;
        }
    }

    public void onFocusChanged(View view, CharSequence sourceText,
                               boolean focused, int direction,
                               Rect previouslyFocusedRect) {
        // This callback isn't used.
    }

    private static class TransformationCharSequence
            implements CharSequence, GetChars {
        private final char[] mIgnoreObfuscatedCharacters;
        private final boolean isHaveIgnoreObfuscatedCharacters;
        private final int mStartIndexOfObfuscated;
        private final int mEndIndexOfObfuscated;
        private final char mObfuscatedPatternCharacter;

        public TransformationCharSequence(CharSequence source,
                                          int startIndex, int endIndex, char character, boolean isHaveIgnoreCharacters, char[] ignoreCharacters) {
            mSource = source;
            mStartIndexOfObfuscated = startIndex - 1;
            mEndIndexOfObfuscated = endIndex;
            mObfuscatedPatternCharacter = character;
            mIgnoreObfuscatedCharacters = ignoreCharacters;
            this.isHaveIgnoreObfuscatedCharacters = isHaveIgnoreCharacters;
        }

        public int length() {
            return mSource.length();
        }

        public char charAt(int i) {
            char c = mSource.charAt(i);
            if (isHaveIgnoreObfuscatedCharacters
                    && mIgnoreObfuscatedCharacters != null) {
                for (char mIgnoreObfuscatedCharacter : mIgnoreObfuscatedCharacters) {
                    if (mIgnoreObfuscatedCharacter == c) {
                        return c;
                    }
                }
            }

            if (i >= mStartIndexOfObfuscated
                    && i < mEndIndexOfObfuscated) {
                c = mObfuscatedPatternCharacter;
            }
            return c;
        }

        public CharSequence subSequence(int start, int end) {
            char[] c = new char[end - start];

            getChars(start, end, c, 0);
            return new String(c);
        }

        public String toString() {
            char[] c = new char[length()];

            getChars(0, length(), c, 0);
            return new String(c);
        }

        public void getChars(int start, int end, char[] dest, int off) {
            TextUtils.getChars(mSource, start, end, dest, off);
            int offend = end - start + off;

            for (int i = off; i < offend; i++) {
                boolean isIgnoreChar = false;
                if (isHaveIgnoreObfuscatedCharacters
                        && mIgnoreObfuscatedCharacters != null) {
                    for (char mIgnoreObfuscatedCharacter : mIgnoreObfuscatedCharacters) {
                        if (mIgnoreObfuscatedCharacter == dest[i]) {
                            isIgnoreChar = true;
                            break;
                        }
                    }
                }

                if (!isIgnoreChar && i >= mStartIndexOfObfuscated && i < mEndIndexOfObfuscated) {
                    dest[i] = mObfuscatedPatternCharacter;
                }
            }
        }

        private final CharSequence mSource;
    }

    private static class SpannedTransformationCharSequence
            extends TransformationCharSequence
            implements Spanned {
        public SpannedTransformationCharSequence(Spanned source,
                                                 int startIndex, int endIndex, char character, boolean isHaveIgnoreCharacters, char[] ignoreCharacters) {
            super(source, startIndex, endIndex, character, isHaveIgnoreCharacters, ignoreCharacters);
            mSpanned = source;
        }

        public CharSequence subSequence(int start, int end) {
            return new SpannedString(this).subSequence(start, end);
        }

        public <T> T[] getSpans(int start, int end, Class<T> type) {
            return mSpanned.getSpans(start, end, type);
        }

        public int getSpanStart(Object tag) {
            return mSpanned.getSpanStart(tag);
        }

        public int getSpanEnd(Object tag) {
            return mSpanned.getSpanEnd(tag);
        }

        public int getSpanFlags(Object tag) {
            return mSpanned.getSpanFlags(tag);
        }

        public int nextSpanTransition(int start, int end, Class type) {
            return mSpanned.nextSpanTransition(start, end, type);
        }

        private Spanned mSpanned;
    }
}
