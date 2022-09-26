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

package com.hcxc.opensource

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.hcxc.obfuscatededittext.*
import com.hcxc.opensource.databinding.ActivityObfuscatedEdittectBinding

class ObfuscatedEdittextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityObfuscatedEdittectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObfuscatedEdittectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPhoneInput()
        initIdcardInput()
        initIBankcardInput()
        initCustomInput()
    }

    private fun initPhoneInput() {
        binding.viewPhoneObfuscate.setTransformationMethod(
            ObfuscatedTransformationMethodBuilder
                .getObfuscatedTransformationMethodByPresetMode(
                    ObfuscatedTransformationMethodBuilder.PresetMode.MOBILE_PHONE
                )
        )
        binding.viewPhoneObfuscate.setValidationStrategy(PhoneValidationStrategy(this))
        binding.btnPhoneInput.setOnClickListener {
            binding.viewPhoneObfuscate.textView.text = binding.etPhoneInput.text
            hideSoftKeyboardForView(this, binding.root)
        }
    }

    private fun initIdcardInput() {
        binding.btnIdcardInput.setOnClickListener {
            binding.viewIdcardObfuscate.textView.text = binding.etIdcardInput.text
            hideSoftKeyboardForView(this, binding.root)
        }
        binding.viewIdcardObfuscate.setTransformationMethod(
            ObfuscatedTransformationMethodBuilder
                .getObfuscatedTransformationMethodByPresetMode(
                    ObfuscatedTransformationMethodBuilder.PresetMode.ID_CARD
                )
        )
        binding.viewIdcardObfuscate.setValidationStrategy(IdCardValidationStrategy(this))
    }

    private fun initIBankcardInput() {
        binding.btnBankInput.setOnClickListener {
            binding.viewBankObfuscate.setTransformationMethod(
                ObfuscatedTransformationMethodBuilder
                    .getObfuscatedTransformationMethodByPresetMode(
                        ObfuscatedTransformationMethodBuilder.PresetMode.BANK_CARD
                    )
            )
            binding.viewBankObfuscate.textView.text = binding.etBankInput.text
            hideSoftKeyboardForView(this, binding.root)
        }
        binding.viewBankObfuscate.setValidationStrategy(BankCardValidationStrategy(this))
    }

    private fun initCustomInput() {
        binding.btnCustomInput.setOnClickListener {

            if (TextUtils.isEmpty(binding.etCustomInput.text)) {
                Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show()
            } else if (!TextUtils.isEmpty(binding.etCustomInputStart.text)
                && !TextUtils.isEmpty(binding.etCustomInputEnd.text)
                && !TextUtils.isEmpty(binding.etCustomInputChar.text)
                && binding.etCustomInputStart.text.toString().isDigitsOnly()
                && binding.etCustomInputEnd.text.toString().isDigitsOnly()
            ) {
                val start: Int = binding.etCustomInputStart.text.toString().toInt()
                val end: Int = binding.etCustomInputEnd.text.toString().toInt()
                if (start > 0 && end > 0) {
                    val char: Char = binding.etCustomInputChar.text.toString().toCharArray().first()
                    Log.d("initCustomInput", " $start $end $char")
                    binding.viewCustomObfuscate.setTransformationMethod(
                        ObfuscatedTransformationMethodBuilder(start, end)
                            .obfuscatedPatternCharacter(char).build()
                    )
                    binding.viewCustomObfuscate.textView.text = binding.etCustomInput.text
                } else {
                    Toast.makeText(this, "起始位置或掩码长度输入有误", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "参数格式错误", Toast.LENGTH_SHORT).show()
            }
            hideSoftKeyboardForView(this, binding.root)
        }
    }

    private fun hideSoftKeyboardForView(context: Context?, view: View?) {
        if (view != null && context != null) {
            val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}