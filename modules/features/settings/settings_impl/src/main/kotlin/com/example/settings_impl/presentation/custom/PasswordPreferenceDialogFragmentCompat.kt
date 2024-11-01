package com.example.settings_impl.presentation.custom

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceDialogFragmentCompat
import com.example.settings_impl.R

class PasswordPreferenceDialogFragmentCompat() : PreferenceDialogFragmentCompat() {
    private lateinit var passwordEditText: EditText
    private lateinit var confirmationEditText: EditText
    private var passwordText: CharSequence = EMPTY_STRING
    private var confirmationText: CharSequence = EMPTY_STRING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            passwordText = savedInstanceState.getCharSequence(SAVE_STATE_PASSWORD_TEXT)!!
            confirmationText = savedInstanceState.getCharSequence(SAVE_STATE_CONFIRMATION_TEXT)!!
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence(SAVE_STATE_PASSWORD_TEXT, passwordText)
        outState.putCharSequence(SAVE_STATE_CONFIRMATION_TEXT, confirmationText)
    }

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)
        view.apply {
            passwordEditText = findViewById(R.id.passwordEditText)
            confirmationEditText = findViewById(R.id.confirmationEditText)
        }
        passwordEditText.setText(passwordText)
        confirmationEditText.setText(confirmationText)
    }

    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog).getButton(DialogInterface.BUTTON_POSITIVE).isEnabled =
            !(passwordText.toString().isEmpty()
                    || confirmationText.toString().isEmpty()
                    || passwordText.toString() != confirmationText.toString())
        dialog.apply {
            passwordEditText.addTextChangedListener(
                PasswordPreferenceDialogTextWatcher {
                    passwordText = it
                    setEnabledToPositiveButton()
                }
            )
            confirmationEditText.addTextChangedListener(
                PasswordPreferenceDialogTextWatcher {
                    confirmationText = it
                    setEnabledToPositiveButton()
                }
            )
        }
    }

    private fun setEnabledToPositiveButton() {
        (dialog as AlertDialog).getButton(DialogInterface.BUTTON_POSITIVE).isEnabled =
            passwordText.toString() == confirmationText.toString() && passwordText.isNotEmpty()
    }

    private fun getPasswordDialogPreference(): PasswordDialogPreference =
        preference as PasswordDialogPreference

    companion object {
        private const val SAVE_STATE_PASSWORD_TEXT: String =
            "PasswordPreferenceDialogFragmentCompat.passwordText"
        private const val SAVE_STATE_CONFIRMATION_TEXT: String =
            "PasswordPreferenceDialogFragmentCompat.confirmationText"
        private const val EMPTY_STRING: String = ""

        fun newInstance(key: String): PasswordPreferenceDialogFragmentCompat {
            val fragment = PasswordPreferenceDialogFragmentCompat()
            val b = Bundle(1)
            b.putString(ARG_KEY, key)
            fragment.arguments = b
            return fragment
        }
    }


    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            getPasswordDialogPreference().also {
                if ((passwordEditText.text.toString() == confirmationEditText.text.toString())
                    && passwordEditText.text.toString() != EMPTY_STRING
                ) {
                    it.callChangeListener(passwordEditText.text.toString())
                    it.passwordText = passwordEditText.text.toString()
                }
            }
        }
    }
}