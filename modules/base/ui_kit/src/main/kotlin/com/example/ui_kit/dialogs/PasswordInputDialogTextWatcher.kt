package com.example.ui_kit.dialogs

import android.text.Editable
import android.text.TextWatcher

class PasswordInputDialogTextWatcher(
    private var block: ((Editable) -> Unit)
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(s: Editable?) {
        s?.let { block(it) }
    }
}