package com.example.settings_impl

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.EditTextPreferenceDialogFragmentCompat
import androidx.preference.ListPreference
import androidx.preference.ListPreferenceDialogFragmentCompat
import androidx.preference.MultiSelectListPreference
import androidx.preference.MultiSelectListPreferenceDialogFragmentCompat
import androidx.preference.Preference
import androidx.preference.Preference.SummaryProvider
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceManager
import com.example.core_api.constants.*
import com.example.core_api.contracts.AppWithFacade
import com.example.infrastructure.base_blueprints.preference_fragment.BasePreferenceFragment
import com.example.settings_impl.di.SettingsScreenComponent
import com.example.settings_impl.navigation.SettingsScreenRouter
import com.example.settings_impl.presentation.SettingsScreenView
import com.example.settings_impl.presentation.SettingsScreenViewModel
import com.example.settings_impl.presentation.SettingsScreenViewModelFactory
import com.example.settings_impl.presentation.custom.PasswordDialogPreference
import com.example.settings_impl.presentation.custom.PasswordPreferenceDialogFragmentCompat
import javax.inject.Inject

class SettingsScreenFragment : BasePreferenceFragment<SettingsScreenRouter,
        SettingsScreenViewModel,
        SettingsScreenViewModelFactory,
        SettingsScreenView>() {
    override fun daggerInit() = SettingsScreenComponent
        .madeSettingsScreenComponent(
            (requireActivity().application as AppWithFacade).getFacade(),
            findNavController(),
            lifecycle
        ).inject(this)

    @Inject
    override lateinit var viewModelFactory: SettingsScreenViewModelFactory

    @Inject
    override lateinit var router: SettingsScreenRouter

    @Inject
    override lateinit var view: SettingsScreenView

    private var isPasswordSet: Boolean = false
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAndSetViewModel(SettingsScreenViewModel::class.java)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)
        val enablePassword: CheckBoxPreference = CheckBoxPreference(context).apply {
            key = ENABLE_PASSWORD_KEY
            title = resources.getString(R.string.enablePasswordTitle)
            summary = resources.getString(R.string.enablePasswordSummary)
        }
        val passwordDialogPreference: PasswordDialogPreference =
            PasswordDialogPreference(
                lifecycle.coroutineScope,
                context
            ).apply {
                key = PASSWORD_AND_CONFIRMATION_KEY
                title = resources.getString(R.string.passwordAndConfirmationTitle)
                summaryProvider =
                    SummaryProvider { preference: PasswordDialogPreference ->
                        isPasswordSet = preference.passwordText.isNotEmpty()
                        Log.i("***", "SummaryProvider isPasswordSet = $isPasswordSet")
                        onBackPressedCallback.isEnabled = !isPasswordSet && enablePassword.isChecked
                        if (!isPasswordSet) {
                            resources.getString(R.string.passwordAndConfirmationNotSetSummary)
                        } else {
                            resources.getString(R.string.passwordAndConfirmationSetSummary)
                        }
                    }
                isEnabled = false
            }
        enablePassword.setOnPreferenceChangeListener { _, newValue ->
            passwordDialogPreference.apply {
                isEnabled = newValue as Boolean
                if (passwordDialogPreference.passwordText.isEmpty()) {
                    performClick()
                }
            }
            true
        }
        passwordDialogPreference.isEnabled =
            sharedPreferences.getBoolean(ENABLE_PASSWORD_KEY, false)
        val passwordSettingsCategory: PreferenceCategory = PreferenceCategory(context).apply {
            key = CATEGORY_PASSWORD_SETTINGS_KEY
            title = resources.getString(R.string.categoryPasswordSettingsTitle)
        }
        //TODO убрать
        val testPreference: Preference = Preference(context).apply {
            key = "test"
            title = "test"
            setOnPreferenceClickListener {
                Log.i(
                    "***",
                    "PasswordDialogPreference text = ${passwordDialogPreference.passwordText}"
                )
                true
            }
        }

        val themePreference: ListPreference = ListPreference(context).apply {
            key = THEME_KEY
            title = resources.getString(R.string.appThemeTitle)
            entries = resources.getStringArray(R.array.appTheme)
            entryValues = arrayOf(THEME_LIGHT, THEME_DARK, THEME_AUTO)
            summaryProvider = SummaryProvider { preference: ListPreference ->
                when (preference.value) {
                    null -> resources.getString(R.string.appThemeSummaryUndefined)

                    else -> preference.value
                }
            }
        }
        screen.apply {
            addPreference(passwordSettingsCategory)
            addPreference(themePreference)
        }
        passwordSettingsCategory
            .apply {
                addPreference(enablePassword)
                addPreference(passwordDialogPreference)
                addPreference(testPreference)
            }
        preferenceScreen = screen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager.findPreference<PasswordDialogPreference>(PASSWORD_AND_CONFIRMATION_KEY)!!
            .apply {
                preferenceDataStore = this@SettingsScreenFragment.view.providePasswordDatastore()
                transformBlock = this@SettingsScreenFragment.view::onPasswordValuePersisting
                Log.i("***", "PasswordDialogPreference text = $passwordText")
            }
        Log.i("***", "onVIewCreated isPasswordSet = $isPasswordSet")
        onBackPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            val isPasswordSetCheckBoxPreference: CheckBoxPreference =
                preferenceManager.findPreference(ENABLE_PASSWORD_KEY)!!
            //Log.i("***", "isPasswordSet = $isPasswordSet")
            //если пароль не сохранён но чекбокс отмечен
            if (!isPasswordSet && isPasswordSetCheckBoxPreference.isChecked) {
                //убираем отметку с чекбокса и отменяем этот колбэк
                isPasswordSetCheckBoxPreference.isChecked = isPasswordSet
                isEnabled = isPasswordSet
            }
        }
    }


    @SuppressLint("RestrictedApi")
    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (isHandled(callbackFragment, preference)) {
            return
        }

        // check if dialog is already showing
        if (parentFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
            return
        }
        val f: DialogFragment = getDialogFragment(preference)

        @Suppress("DEPRECATION")
        f.setTargetFragment(this, 0)
        f.show(parentFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isHandled(callbackFragment: Fragment?, preference: Preference): Boolean {
        var handled = false
        if (callbackFragment is OnPreferenceDisplayDialogCallback) {
            handled = (callbackFragment as OnPreferenceDisplayDialogCallback)
                .onPreferenceDisplayDialog(this, preference)
        }

        //  If the callback fragment doesn't handle OnPreferenceDisplayDialogCallback, looks up
        //  its parent fragment in the hierarchy that implements the callback until the first
        //  one that returns true
        var callbackFragment: Fragment? = this
        while (!handled && callbackFragment != null) {
            if (callbackFragment is OnPreferenceDisplayDialogCallback) {
                handled = (callbackFragment as OnPreferenceDisplayDialogCallback)
                    .onPreferenceDisplayDialog(this, preference)
            }
            callbackFragment = callbackFragment.parentFragment
        }
        if (!handled && context is OnPreferenceDisplayDialogCallback) {
            handled = (context as OnPreferenceDisplayDialogCallback)
                .onPreferenceDisplayDialog(this, preference)
        }

        // Check the Activity as well in case getContext was overridden to return something other
        // than the Activity.
        if (!handled && activity is OnPreferenceDisplayDialogCallback) {
            handled = (activity as OnPreferenceDisplayDialogCallback)
                .onPreferenceDisplayDialog(this, preference)
        }
        return handled
    }

    private fun getDialogFragment(preference: Preference): DialogFragment =
        when (preference) {
            is PasswordDialogPreference  -> {
                PasswordPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            }

            is EditTextPreference        -> {
                EditTextPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            }

            is ListPreference            -> {
                ListPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            }

            is MultiSelectListPreference -> {
                MultiSelectListPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            }

            else                         -> {
                throw IllegalArgumentException(
                    ("Cannot display dialog for an unknown Preference type: "
                            + preference.javaClass.simpleName
                            + ". Make sure to implement onPreferenceDisplayDialog() to handle "
                            + "displaying a custom dialog for this Preference.")
                )
            }
        }

    companion object {
        private const val DIALOG_FRAGMENT_TAG: String =
            "androidx.preference.PreferenceFragment.DIALOG"

        fun newInstance() = SettingsScreenFragment()
    }
}