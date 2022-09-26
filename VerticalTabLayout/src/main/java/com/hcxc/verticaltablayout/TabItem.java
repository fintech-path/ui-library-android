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
package com.hcxc.verticaltablayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

public class TabItem extends View {

    private Drawable mIcon;
    private String mText;
    private int mIconGravity = Gravity.LEFT;
    private int mIconWidth;
    private int mIconHeight;

    public TabItem(Context context) {
        super(context);
        init(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.vTabItem);
        mIcon = typedArray.getDrawable(R.styleable.vTabItem_icon);
        mText = typedArray.getString(R.styleable.vTabItem_text);
        mIconGravity = typedArray.getInt(R.styleable.vTabItem_vIconGravity, mIconGravity);
        mIconWidth = (int) typedArray.getDimension(R.styleable.vTabItem_iconWidth, 0);
        mIconHeight = (int) typedArray.getDimension(R.styleable.vTabItem_iconHeight, 0);
        typedArray.recycle();
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public int getIconGravity() {
        return mIconGravity;
    }

    public int getIconHeight() {
        return mIconHeight;
    }

    public void setIconHeight(int mIconHeight) {
        this.mIconHeight = mIconHeight;
    }

    public int getIconWidth() {
        return mIconWidth;
    }

    public void setIconWidth(int mIconWidth) {
        this.mIconWidth = mIconWidth;
    }

    public void setIconGravity(int mIconGravity) {
        this.mIconGravity = mIconGravity;
    }
}
