package com.example.settings_impl

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAndSetViewModel(SettingsScreenViewModel::class.java)
    }

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
                        if (preference.text.isNullOrEmpty()) {
                            resources.getString(R.string.passwordAndConfirmationNotSetSummary)
                        } else {
                            resources.getString(R.string.passwordAndConfirmationSetSummary)
                        }
                    }
                //preferenceDataStore = datastore
                isEnabled = false
            }
        enablePassword.setOnPreferenceChangeListener { _, newValue ->
            passwordDialogPreference.isEnabled = newValue as Boolean
            true
        }
        passwordDialogPreference.isEnabled =
            sharedPreferences.getBoolean(ENABLE_PASSWORD_KEY, false)
        val passwordSettingsCategory: PreferenceCategory = PreferenceCategory(context).apply {
            key = CATEGORY_PASSWORD_SETTINGS_KEY
            title = resources.getString(R.string.categoryPasswordSettingsTitle)
        }
        screen.addPreference(passwordSettingsCategory)
        passwordSettingsCategory
            .apply {
                addPreference(enablePassword)
                addPreference(passwordDialogPreference)
            }
        preferenceScreen = screen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager.findPreference<PasswordDialogPreference>(PASSWORD_AND_CONFIRMATION_KEY)!!
            .apply {
                preferenceDataStore = this@SettingsScreenFragment.view.providePasswordDatastore()
                transformBlock = this@SettingsScreenFragment.view::onPasswordValuePersisting
            }
    }


    @SuppressLint("RestrictedApi")
    override fun onDisplayPreferenceDialog(preference: Preference) {
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

        if (handled) {
            return
        }

        // check if dialog is already showing
        if (parentFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
            return
        }
        val f: DialogFragment =
            if (preference is PasswordDialogPreference) {
                PasswordPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            } else if (preference is EditTextPreference) {
                EditTextPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            } else if (preference is ListPreference) {
                ListPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            } else if (preference is MultiSelectListPreference) {
                MultiSelectListPreferenceDialogFragmentCompat.newInstance(preference.getKey())
            } else {
                throw IllegalArgumentException(
                    ("Cannot display dialog for an unknown Preference type: "
                            + preference.javaClass.simpleName
                            + ". Make sure to implement onPreferenceDisplayDialog() to handle "
                            + "displaying a custom dialog for this Preference.")
                )
            }
        @Suppress("DEPRECATION")
        f.setTargetFragment(this, 0)
        f.show(parentFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    companion object {
        private const val CATEGORY_PASSWORD_SETTINGS_KEY: String = "categoryPasswordSettings"
        private const val ENABLE_PASSWORD_KEY: String = "enablePassword"
        private const val PASSWORD_AND_CONFIRMATION_KEY: String = "passwordAndConfirmation"
        private const val SORT_TYPE_KEY: String = "sortType"
        private const val THEME_KEY: String = "theme"
        private const val DIALOG_FRAGMENT_TAG: String =
            "androidx.preference.PreferenceFragment.DIALOG"

        fun newInstance() = SettingsScreenFragment()
    }
}