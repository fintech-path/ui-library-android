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

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

public class ObfuscatedEditLayout extends RelativeLayout {

    private boolean currentObfuscatedState = true;
    private View contentView;
    private String title;
    private @DrawableRes
    int mLeftIconDrawable;
    private @DrawableRes
    int mClearIconDrawable;
    private @DrawableRes
    int mObfuscatedIconDrawable;
    private TextView mTextView;
    private FrameLayout mObfuscatedButton;
    private ImageView mObfuscatedView;
    private ImageView mClearView;
    private ImageView mLeftIconView;
    private TextView mLayoutTitle;
    private OnFunctionClickListener mFunctionClickListener;
    private ValidationStrategy mValidationStrategy;

    private TransformationMethod mMethod;

    public ObfuscatedEditLayout(Context context) {
        this(context, null);
    }

    public ObfuscatedEditLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObfuscatedEditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseObfuscateEditLayout);
        if (typedArray != null) {
            currentObfuscatedState = typedArray.getBoolean(R.styleable.BaseObfuscateEditLayout_defaultObfuscateStatus, true);
            title = typedArray.getString(R.styleable.BaseObfuscateEditLayout_title);
            mLeftIconDrawable = typedArray.getResourceId(R.styleable.BaseObfuscateEditLayout_leftIcon, -1);
            mObfuscatedIconDrawable = typedArray.getResourceId(R.styleable.BaseObfuscateEditLayout_obfuscateIcon, R.drawable.bg_input_cipher_btn);
            mClearIconDrawable = typedArray.getResourceId(R.styleable.BaseObfuscateEditLayout_clearIcon, R.drawable.icon_input_delete);

            typedArray.recycle();
        }
        initView();
    }

    protected View initView() {
        View view = View.inflate(getContext(), R.layout.auto_check_edit_layout_view, null);
        mTextView = view.findViewById(R.id.tv_text);
        mLayoutTitle = view.findViewById(R.id.tv_layout_title);
        mObfuscatedButton = view.findViewById(R.id.fl_obfuscated_btn);
        mObfuscatedView = view.findViewById(R.id.iv_obfuscated_btn);

        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString()) && mValidationStrategy != null) {
                    if (!mValidationStrategy.validate(s.toString())) {
                        mTextView.setText(mValidationStrategy.onTextFormatError(s.toString()));
                    }
                }
            }
        });

        if (!TextUtils.isEmpty(title)) {
            mLayoutTitle.setText(title);
        }

        mLeftIconView = view.findViewById(R.id.iv_icon_lef);
        if (mLeftIconDrawable == -1) {
            mLeftIconView.setVisibility(GONE);
        } else {
            mLeftIconView.setImageDrawable(getContext().getDrawable(mLeftIconDrawable));
        }

        mClearView = view.findViewById(R.id.iv_clear_btn);
        mClearView.setImageDrawable(getContext().getDrawable(mClearIconDrawable));
        mClearView.setOnClickListener(v -> {
            mTextView.setText("");
            refreshObfuscatedViewStatue(currentObfuscatedState);
            if (mFunctionClickListener != null) {
                mFunctionClickListener.onClearClick();
            }
        });

        mObfuscatedView.setImageDrawable(getContext().getDrawable(mObfuscatedIconDrawable));
        mObfuscatedButton.setOnClickListener(v -> {
            currentObfuscatedState = !currentObfuscatedState;
            refreshObfuscatedViewStatue(currentObfuscatedState);
            if (mFunctionClickListener != null) {
                mFunctionClickListener.onObfuscateButtonClick(currentObfuscatedState);
            }
        });
        refreshObfuscatedViewStatue(currentObfuscatedState);
        addView(view);
        LayoutParams viewLayoutLP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(viewLayoutLP);

        return view;
    }

    public void clearText() {
        mClearView.performClick();
    }

    private void refreshObfuscatedViewStatue(boolean targetStatue) {
        if (getObfuscatedTransformationMethod() != null) {
            mObfuscatedButton.setVisibility(VISIBLE);
            mTextView.setTransformationMethod(targetStatue ? getObfuscatedTransformationMethod() : HideReturnsTransformationMethod.getInstance());
            mObfuscatedView.getBackground().setLevel(targetStatue ? 1 : 0);
            mTextView.invalidate();
        } else {
            mObfuscatedButton.setVisibility(GONE);
        }
    }

    public void setFunctionClickListener(OnFunctionClickListener functionClickListener) {
        this.mFunctionClickListener = functionClickListener;
    }

    public void setTitleText(String text) {
        mLayoutTitle.setText(text);
    }

    public TextView getTextView() {
        return mTextView;
    }

    protected TransformationMethod getObfuscatedTransformationMethod() {
        if (mMethod != null) {
            return mMethod;
        } else {
            return null;
        }
    }

    public void setTransformationMethod(TransformationMethod method) {
        mMethod = method;
        refreshObfuscatedViewStatue(currentObfuscatedState);
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.mValidationStrategy = validationStrategy;
    }

    public interface OnFunctionClickListener {
        void onClearClick();

        void onObfuscateButtonClick(boolean isObfuscated);
    }

    public interface ValidationStrategy {
        boolean validate(String s);

        String onTextFormatError(String errorString);
    }
}
