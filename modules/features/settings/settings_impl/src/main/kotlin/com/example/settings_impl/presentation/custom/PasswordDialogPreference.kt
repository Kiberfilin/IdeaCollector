package com.example.settings_impl.presentation.custom

import android.content.Context
import android.util.AttributeSet
import androidx.preference.EditTextPreference
import com.example.settings_impl.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordDialogPreference @JvmOverloads constructor(
    private val scope: CoroutineScope,
    context: Context,
    attrs: AttributeSet? = null
) :
    EditTextPreference(context, attrs) {
    init {
        isPersistent = true
        dialogLayoutResource = R.layout.layout_password_dialog_property
    }

    lateinit var transformBlock: suspend (String) -> String

    override fun onSetInitialValue(defaultValue: Any?) {
        text = defaultValue as? String
    }

    var passwordText: String
        set(value) {
            scope.launch(Dispatchers.IO) {
                text = transformBlock.invoke(value)
            }
        }
        get() = text.toString()
}