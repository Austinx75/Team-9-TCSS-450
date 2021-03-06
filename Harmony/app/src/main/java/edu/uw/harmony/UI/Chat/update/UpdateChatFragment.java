package edu.uw.harmony.UI.Chat.update;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.uw.harmony.R;
import edu.uw.harmony.UI.Contacts.ContactListViewModel;
import edu.uw.harmony.UI.Contacts.ContactRecyclerViewAdapter;
import edu.uw.harmony.UI.model.NewMessageCountViewModel;
import edu.uw.harmony.UI.model.UserInfoViewModel;
import edu.uw.harmony.UI.settings.SettingsViewModel;
import edu.uw.harmony.databinding.FragmentUpdateChatBinding;

/**
 * Fragment that contains the fields to update the members in the chat room. They will be able to
 * select the people within the chat and change them or leave the chat.
 * A simple {@link Fragment} subclass.
 */
public class UpdateChatFragment extends Fragment {

    /** View Binding for the update chat fragment*/
    FragmentUpdateChatBinding binding;
    /** View model for the contact list*/
    private ContactListViewModel mContactModel;
    /** View model for the user*/
    private UserInfoViewModel mUserModel;
    /** View model for update chat*/
    private UpdateChatViewModel mModel;
    /** The members that are currently in the room*/
    private List<String> selected;
    /** ViewModel for settings */
    private SettingsViewModel settingsViewModel;
    /** the chat id that we are updating*/
    private int chatId;
    /** The updated members for the chat*/
    private StringBuilder updatedMembers;
    /** List of all members that are selected*/
    private List<String> updated;
    /** View Model for the new messages*/
    private NewMessageCountViewModel mChatCountViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.selected = new ArrayList<>();
        mModel = new ViewModelProvider(getActivity()).get(UpdateChatViewModel.class);
        settingsViewModel = new ViewModelProvider(getActivity()).get(SettingsViewModel.class);
        mUserModel = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
        mContactModel = new ViewModelProvider(getActivity()).get(ContactListViewModel.class);
        UpdateChatFragmentArgs args = UpdateChatFragmentArgs.fromBundle(getArguments());
        mModel.connectGet(mUserModel.getJwt(), mUserModel.getEmail(), args.getChatid());
        chatId = args.getChatid();
        updatedMembers = new StringBuilder();
        updated = new ArrayList<>();
        mModel.done();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateChatBinding.inflate(inflater);
        /** Dependent on the theme, this will set all text / image fields to a certain color. */
        if(settingsViewModel.getCurrentThemeID() == R.style.Theme_1_Harmony){
//            binding.toWho.setTextColor(Color.BLACK);
//            binding.enterName.setTextColor(Color.BLACK);
//            binding.enterMessage.setTextColor(Color.BLACK);
//            binding.sendMessage.setBackgroundColor(Color.WHITE);
//            binding.sendMessage.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
        } else {
            binding.editTextChatname.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.offwhite, null)));
            binding.editTextChatname.setTextColor(Color.WHITE);
        }
        binding.buttonDelete.setBackgroundColor((Color.RED));
        binding.buttonDelete.setTextColor(Color.BLACK);
        binding.frameLayout3.setVisibility(View.GONE);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * Override onViewCreated, add a observer to contact list and update it based on changes
     * @param view View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContactModel.addContactListObserver(getViewLifecycleOwner(), contactList -> {
            binding.listRoot.setAdapter(
                    new ContactRecyclerViewAdapter(contactList,mContactModel,mUserModel, settingsViewModel, true, updated, this.selected));

        });
        binding.buttonContinue .setOnClickListener(button -> {
            updatedMembers.append(updated.get(0));
            for (int i = 1; i < updated.size(); i++ ){
                updatedMembers.append(" " +updated.get(i));
            }
            mModel.connectPost(mUserModel.getJwt(), mUserModel.getEmail(), chatId, updatedMembers.toString());
            Navigation.findNavController(getView()).navigate(UpdateChatFragmentDirections.actionUpdateChatFragmentToNavigationChatPost(chatId));
        });
        binding.buttonDelete.setOnClickListener(button -> {
           mModel.connectDelete(mUserModel.getJwt(), mUserModel.getEmail(), chatId);
           Navigation.findNavController(getView()).navigate(UpdateChatFragmentDirections.actionUpdateChatFragmentToConfirmationDeleteFragment());
        });
        mModel.addResponseGetObserver(getViewLifecycleOwner(), response ->{
            if (response.has("code")) {
//                this error cannot be fixed by the user changing credentials
                binding.editTextChatname.setError("Something went wrong. Please restart");
            } else {
                mContactModel.connectGet(mUserModel.getJwt());
                try {
                    binding.editTextChatname.setText(response.get("name").toString());
                    JSONArray emails = response.getJSONArray("members");
                    for (int i = 0; i < emails.length(); i++ ){
                        this.selected.add(emails.get(i).toString());
                        updated.add(emails.get(i).toString());
                    }
                    Log.e("MEMBERS", selected.toString());
                    binding.layoutWait.setVisibility(View.GONE);
                    binding.frameLayout3.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mChatCountViewModel = new ViewModelProvider(getActivity()).get(NewMessageCountViewModel.class);
        mChatCountViewModel.setCurrentChatRoom(chatId);
        mChatCountViewModel.reset();
        this.selected.clear();
        this.updated.clear();
        mModel.done();
    }
}