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
package com.hcxc.obfuscatededittext

import android.content.Context
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import java.util.*

class PhoneValidationStrategy(private val context: Context) :
    ObfuscatedEditLayout.ValidationStrategy {


    override fun validate(s: String): Boolean {
        return s.matches(Regex("^1([3456789])[0-9]{9}"))
    }

    override fun onTextFormatError(errorString: String): String {
        Toast.makeText(
            context,
            context.getText(R.string.phone_format_error_tip),
            Toast.LENGTH_SHORT
        ).show()
        return ""
    }
}

class IdCardValidationStrategy(private val context: Context) :
    ObfuscatedEditLayout.ValidationStrategy {

    override fun validate(s: String): Boolean {
        return s.length == 18 && !s.lowercase(Locale.getDefault()).substring(0, 17).contains("x")
    }

    override fun onTextFormatError(errorString: String): String {
        Toast.makeText(
            context,
            context.getText(R.string.idcard_format_error_tip),
            Toast.LENGTH_SHORT
        ).show()
        return ""
    }
}

class BankCardValidationStrategy(private val context: Context) :
    ObfuscatedEditLayout.ValidationStrategy {

    override fun validate(s: String): Boolean {
        return s.length >= 15 && s.isDigitsOnly()
    }

    override fun onTextFormatError(errorString: String): String {
        Toast.makeText(
            context,
            context.getText(R.string.bank_card_format_error_tip),
            Toast.LENGTH_SHORT
        ).show()
        return ""
    }
}