package edu.uw.harmony.UI.settings;

import android.graphics.Color;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import edu.uw.harmony.R;
import edu.uw.harmony.UI.Auth.LogIn.LogInFragmentArgs;
import edu.uw.harmony.UI.Auth.LogIn.LogInFragmentDirections;
import edu.uw.harmony.UI.Auth.LogIn.LogInViewModel;
import edu.uw.harmony.UI.Avatar.AvatarAdapter;
import edu.uw.harmony.UI.Avatar.AvatarGenerator;
import edu.uw.harmony.UI.Avatar.AvatarViewModel;
import edu.uw.harmony.UI.model.UserInfoViewModel;
import edu.uw.harmony.databinding.FragmentLogInBinding;
import edu.uw.harmony.databinding.FragmentSettingsBinding;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * This is the fragment where you can change color theme, and reset password.
 * @author Austin Scott
 * @version 1.1
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment{

    /** Binding for this fragment*/
    private FragmentSettingsBinding binding;

    /** ViewModel for settings */
    private SettingsViewModel settingsViewModel;

    /** ViewModel for avatars */
    private AvatarViewModel avatarViewModel;

    /** ViewModel for userViewModel */
    private UserInfoViewModel userInfoViewModel;

    /** Key to access shared preferences */
    public static final String sharedPreferenceKey = "MY_SHARED_PREF";


    /** Key of saved theme*/
    public static final String savedThemeKey = "SAVED_THEME_KEY";

    /** Checks the state of the Switch button */
    public boolean switchState;

    /**
     * It instantiates the view model so we can get the current switch state and theme
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsViewModel = new ViewModelProvider(getActivity()).get(SettingsViewModel.class);
        avatarViewModel = new ViewModelProvider(getActivity()).get(AvatarViewModel.class);
        userInfoViewModel = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
    }


    /**
     * It switches the colors dynamically based on what the current theme is.
     * It also sets the switch button to what its current position was.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        SwitchCompat switchButton = (SwitchCompat) binding.darkModeSwitch;
        switchButton.setChecked(settingsViewModel.getSwitchState());
        binding.avatar.setVisibility(GONE);
        /** Dependent on the theme, this will set all text / image fields to a certain color. */
        if(settingsViewModel.getCurrentThemeID() == R.style.Theme_1_Harmony){
            binding.settingsDarkModeImage.setColorFilter(getResources().getColor(R.color.black));
            binding.darkModeTextView.setTextColor(Color.BLACK);
        } else {
            binding.settingsDarkModeImage.setColorFilter(getResources().getColor(R.color.grey));
            binding.darkModeTextView.setTextColor(Color.WHITE);
        }
        return binding.getRoot();
    }

    /**
     * It checks to see if the switch button has been switched, and if so it updates the theme.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonChangePassword.setOnClickListener(button -> {
            Navigation.findNavController(getView())
                    .navigate(SettingsFragmentDirections
                            .actionSettingsFragmentToPasswordChangeFragment());
        });
        binding.darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.i("Checked", "Theme_2_Harmony is Checked");
                    settingsViewModel.setSavedSwitchState(true);
                    updateTheme(R.style.Theme_2_Harmony);
                } else {
                    Log.i("Checked", "Theme_1_Harmony is Checked");
                    settingsViewModel.setSavedSwitchState(false);
                    updateTheme(R.style.Theme_1_Harmony);
                }
            }
        });

        avatarViewModel.connectGet(userInfoViewModel.getJwt());
        avatarViewModel.addAvatarObserver(getViewLifecycleOwner(), avatar -> {
            binding.avatar.setImageResource(avatar);
            binding.avatar.setVisibility(VISIBLE);
            binding.layoutWait.setVisibility(GONE);
        });

        binding.buttonChangeAvatar.setOnClickListener(button -> {
            Navigation.findNavController(getView()).navigate(SettingsFragmentDirections.actionSettingsFragmentToAvatarListFragment2());
        });

    }

    /**
     * Updates the theme given a theme id. Refreshes current theme as well.
     * @param themeId the ID of the theme. (R.styles.YourTheme)
     */
    private void updateTheme(int themeId) {
        getActivity().setTheme(themeId);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.detach(SettingsFragment.this).attach(SettingsFragment.this).commit();
        settingsViewModel.setSelectedTheme(themeId);
        getActivity().recreate();
    }
}