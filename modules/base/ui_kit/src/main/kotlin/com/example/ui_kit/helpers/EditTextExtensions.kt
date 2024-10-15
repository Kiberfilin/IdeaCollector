package com.example.ui_kit.helpers

import android.content.Context
import android.os.Build
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


@Suppress("IMPLICIT_CAST_TO_ANY")
fun EditText.requestFocusAndShowKeyboard() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        requestFocus()
        windowInsetsController?.show(WindowInsets.Type.ime())
    } else {
        requestFocus()
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

@Suppress("IMPLICIT_CAST_TO_ANY")
fun EditText.clearFocusAndHideKeyboard() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        clearFocus()
        windowInsetsController?.hide(WindowInsets.Type.ime())
    } else {
        clearFocus()
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }