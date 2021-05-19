// Generated by view binder compiler. Do not edit!
package edu.uw.harmony.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import edu.uw.harmony.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentNewChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText enterMessage;

  @NonNull
  public final EditText enterName;

  @NonNull
  public final ConstraintLayout frameLayout3;

  @NonNull
  public final ImageButton sendMessage;

  @NonNull
  public final TextView toWho;

  private FragmentNewChatBinding(@NonNull ConstraintLayout rootView, @NonNull EditText enterMessage,
      @NonNull EditText enterName, @NonNull ConstraintLayout frameLayout3,
      @NonNull ImageButton sendMessage, @NonNull TextView toWho) {
    this.rootView = rootView;
    this.enterMessage = enterMessage;
    this.enterName = enterName;
    this.frameLayout3 = frameLayout3;
    this.sendMessage = sendMessage;
    this.toWho = toWho;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNewChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNewChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_new_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNewChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.enterMessage;
      EditText enterMessage = rootView.findViewById(id);
      if (enterMessage == null) {
        break missingId;
      }

      id = R.id.enterName;
      EditText enterName = rootView.findViewById(id);
      if (enterName == null) {
        break missingId;
      }

      ConstraintLayout frameLayout3 = (ConstraintLayout) rootView;

      id = R.id.sendMessage;
      ImageButton sendMessage = rootView.findViewById(id);
      if (sendMessage == null) {
        break missingId;
      }

      id = R.id.toWho;
      TextView toWho = rootView.findViewById(id);
      if (toWho == null) {
        break missingId;
      }

      return new FragmentNewChatBinding((ConstraintLayout) rootView, enterMessage, enterName,
          frameLayout3, sendMessage, toWho);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}