package edu.uw.harmony.UI.Contacts;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import edu.uw.harmony.R;
import edu.uw.harmony.UI.model.UserInfoViewModel;
import edu.uw.harmony.UI.settings.SettingsViewModel;
import edu.uw.harmony.databinding.FragmentContactRequestCardBinding;

/**
 * This is a Recycler Adapter that creates the ContactListFragment.
 *
 * @author Jack Lin
 * @version 1.0
 */
public class ContactRequestRecyclerViewAdapter extends RecyclerView.Adapter<ContactRequestRecyclerViewAdapter.ContactViewHolder>{
    private final List<ContactCard> mContacts;
    //Store the expanded state for each List item, true -> expanded, false -> not
    private final Map<ContactCard, Boolean> mExpandedFlags;
    ContactRequestViewModel rModel;
    UserInfoViewModel uModel;
    SettingsViewModel sModel;
    boolean newChat;
    List<String> selected;
    String autofill;

    /**
     * Constructor for contact request recycler view adapter. Initializes necessary fields
     */
    public ContactRequestRecyclerViewAdapter(List<ContactCard> items, ContactRequestViewModel rModel, UserInfoViewModel uModel, SettingsViewModel model) {
        this.mContacts= items;
        this.rModel = rModel;
        this.uModel = uModel;
        this.sModel = model;
        this.newChat = false;
        this.selected = new ArrayList<String>();
        this.autofill="";
        mExpandedFlags = mContacts.stream().collect(Collectors.toMap(Function.identity(), contacts -> false));
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_contact_request_card, parent, false), rModel, uModel, this.selected, this.autofill);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.setContact(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    /**
     * Objects from this class represent an Individual row View from the List* of rows in the Blog Recycler View.
     */
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FragmentContactRequestCardBinding binding;
        private ContactCard mContact;
        ContactRequestRecyclerViewAdapter mAdapter;
        ContactRequestViewModel contactRequestViewModel;
        UserInfoViewModel userInfoViewModel;
        List<String> selected;
        String autofill;

        /**
         * Public constructor for the contact view holder. Initializes all necessary fields
         */
        public ContactViewHolder(View view, ContactRequestViewModel mModel, UserInfoViewModel uModel, List<String> selected, String autofill) {
            super(view);
            mView = view;
            contactRequestViewModel = mModel;
            userInfoViewModel = uModel;
            this.selected = selected;
            this.autofill=autofill;

            binding = FragmentContactRequestCardBinding.bind(view);
            binding.contactCard.setOnClickListener(this::handleMoreOrLess);
            binding.contactDelete.setOnClickListener(button -> {
                mContacts.remove(mContact);
                notifyItemRemoved(mContacts.indexOf(mContact));
                mModel.contactDelete(uModel.getJwt(), Integer.parseInt(mContact.getId()));
            });

            binding.contactAccept.setOnClickListener(button -> {
                mContacts.remove(mContact);
                notifyItemRemoved(mContacts.indexOf(mContact));
                mModel.contactUpdate(uModel.getJwt(), Integer.parseInt(mContact.getId()));
            });
        }

        /**
         * When the button is clicked in the more state, expand the card to display
         * the blog preview and switch the icon to the less state.  When the button
         * is clicked in the less state, shrink the card and switch the icon to the
         * more state.
         * @param button the button that was clicked*/

        private void handleMoreOrLess(final View button) {
            mExpandedFlags.put(mContact, !mExpandedFlags.get(mContact));
            displayPreview();
        }

        /**
         * Helper used to determine if the preview should be displayed or not. */

        private void displayPreview() {
            if (mExpandedFlags.get(mContact)) {
                binding.contactPreview.setVisibility(View.VISIBLE);
            } else {
                binding.contactPreview.setVisibility(View.GONE);
            }
        }

        /**
         * Method to fill each contact card with information
         * @param contact ContactCard Object
         */
        void setContact(final ContactCard contact) {
            mContact = contact;

            binding.contactUsername.setText(contact.getUsername());
            binding.contactAvatar.setImageResource(contact.getAvatar());

            String name = contact.getName();
            String user = contact.getUsername();
            String id = contact.getId();

            String preview = "\r\n" + "Full Name: "+name + "\r\n" +
                    "Username: "+user + "\r\n";
            binding.contactPreview.setText(preview);

            if(sModel.getCurrentThemeID() == R.style.Theme_1_Harmony){
                binding.cardRoot.setCardBackgroundColor(binding.getRoot().getResources().getColor(R.color.offwhite));
                binding.contactUsername.setTextColor(Color.BLACK);
                binding.contactAccept.setColorFilter(binding.getRoot().getResources().getColor(R.color.tan));
                binding.contactDelete.setColorFilter(binding.getRoot().getResources().getColor(R.color.tan));
                binding.contactPreview.setTextColor(Color.BLACK);
            } else {
                binding.cardRoot.setCardBackgroundColor(binding.getRoot().getResources().getColor(R.color.black));
                binding.contactUsername.setTextColor(binding.getRoot().getResources().getColor(R.color.teal_200));
                binding.contactAccept.setColorFilter(binding.getRoot().getResources().getColor(R.color.white));
                binding.contactDelete.setColorFilter(binding.getRoot().getResources().getColor(R.color.white));
                binding.contactPreview.setTextColor(binding.getRoot().getResources().getColor(R.color.teal_200));
            }
            displayPreview();
        }
    }
}
