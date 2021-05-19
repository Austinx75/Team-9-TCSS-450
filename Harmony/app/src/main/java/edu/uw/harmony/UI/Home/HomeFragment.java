package edu.uw.harmony.UI.Home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.harmony.R;
import edu.uw.harmony.UI.Contacts.ContactGenerator;
import edu.uw.harmony.UI.Contacts.ContactRecyclerViewAdapter;
import edu.uw.harmony.UI.Weather.WeatherViewModel;
import edu.uw.harmony.UI.Weather.WeeklyForecastRecyclerViewAdapter;
import edu.uw.harmony.UI.model.UserInfoViewModel;
import edu.uw.harmony.UI.settings.SettingsViewModel;
import edu.uw.harmony.databinding.FragmentHomeBinding;
import edu.uw.harmony.databinding.FragmentWeatherBinding;

/**
 * This fragment shows a few components that summarize info throughout the app. This is the first
 * fragment navigated to when the user signs in.
 *
 * @author  Gary Kono
 * @version 1.0
 */
public class HomeFragment extends Fragment {

    /** This is the binder for home fragment. allows us to accessa all its attributes*/
    private FragmentHomeBinding binding;
    /** ViewModel for settings */
    private SettingsViewModel settingsViewModel;
    /** This is the view model for the user. It allows us to access the email of user.*/
    private UserInfoViewModel model;
    /** This is the home view model*/
    private HomeViewModel hModel;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        settingsViewModel = new ViewModelProvider(getActivity()).get(SettingsViewModel.class);
        model = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
        hModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        /** I instantiate the recycler view here.*/
        View notificationView = binding.listRoot;
        if (notificationView instanceof RecyclerView){
            Log.d("Test", "Notification Size: " + NotificationGenerator.getNotificationList().toString());
            ((RecyclerView) notificationView).setAdapter(new NotificationRecyclerViewAdapter(NotificationGenerator.getNotificationList()));
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        hModel.connectGet();
        hModel.setJWT(model.getJwt());
        hModel.setHomeBinding(binding);

        /** Dependent on the theme, this will set all text / image fields to a certain color. */
        if(settingsViewModel.getCurrentThemeID() == R.style.Theme_1_Harmony){
            binding.textEmailHome.setTextColor(Color.BLACK);
            binding.textDegHome.setTextColor(Color.BLACK);
            binding.textNotificationsHome.setTextColor(Color.BLACK);
            binding.imageLogoHome.setColorFilter(Color.BLACK);
        } else {
            binding.textEmailHome.setTextColor(Color.WHITE);
            binding.textDegHome.setTextColor(Color.WHITE);
            binding.textNotificationsHome.setTextColor(Color.WHITE);
            binding.imageLogoHome.setColorFilter(Color.WHITE);

        }
        /** Sets the email and gets rid of the progress bar.*/
        Log.d("STATUS", "Got to success");
        binding.textEmailHome.setText(model.getEmail());
        binding.layoutWait.setVisibility(view.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}