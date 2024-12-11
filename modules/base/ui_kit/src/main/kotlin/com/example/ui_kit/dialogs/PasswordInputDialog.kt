package com.example.ui_kit.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.Editable.Factory
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.coroutineScope
import com.example.core_api.contracts.UserAuthenticationContract
import com.example.ui_kit.R
import com.example.ui_kit.databinding.LayoutPasswordInputDialogBinding
import kotlinx.coroutines.launch

class PasswordInputDialog : DialogFragment(), DialogInterface.OnClickListener {
    private var _binding: LayoutPasswordInputDialogBinding? = null
    private var enteredPassword: Editable = Factory.getInstance().newEditable("")
    private lateinit var isPasswordCorrect: suspend (String) -> Boolean

    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding
        get() = _binding!!

    /** Which button was clicked.  */
    private var mWhichButtonClicked = 0

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mWhichButtonClicked = DialogInterface.BUTTON_NEGATIVE
        _binding = LayoutPasswordInputDialogBinding.inflate(LayoutInflater.from(context))
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            .setTitle(R.string.passwordDialogTitle)
            .setPositiveButton(R.string.passwordDialogPositiveButtonLabel, this)
            .setNegativeButton(R.string.passwordDialogNegativeButtonLabel, this)
            .setView(binding.root)
            .setMessage(R.string.passwordDialogMessage)
        val dialog: AlertDialog = builder.create()
        return dialog
    }

    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog).apply {
            getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = enteredPassword.isNotEmpty()
            binding.passwordInputEditText.addTextChangedListener(
                PasswordInputDialogTextWatcher {
                    enteredPassword = it
                    getButton(DialogInterface.BUTTON_POSITIVE).isEnabled =
                        enteredPassword.isNotEmpty()
                }
            )
        }
    }

    private fun informPasswordIsWrong() {
        Toast.makeText(
            requireActivity().applicationContext, "Password wrong", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        mWhichButtonClicked = which
    }

    override fun onDismiss(dialog: DialogInterface) {
        lifecycle.coroutineScope.launch {
            if (mWhichButtonClicked == DialogInterface.BUTTON_POSITIVE) {
                val isCorrect: Boolean = isPasswordCorrect.invoke(enteredPassword.toString())
                if (!isCorrect) {
                    informPasswordIsWrong()
                }
                (requireActivity() as UserAuthenticationContract)
                    .getUserAuthenticatedStateFlow().value = isCorrect
            }
            super.onDismiss(dialog)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(isPasswordCorrect: suspend (String) -> Boolean): PasswordInputDialog =
            PasswordInputDialog().apply {
                this.isPasswordCorrect = isPasswordCorrect
            }
    }
}