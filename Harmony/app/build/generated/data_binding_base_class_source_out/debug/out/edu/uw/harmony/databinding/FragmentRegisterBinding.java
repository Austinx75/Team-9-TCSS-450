// Generated by view binder compiler. Do not edit!
package edu.uw.harmony.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import edu.uw.harmony.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button3;

  @NonNull
  public final EditText editTextEmail;

  @NonNull
  public final EditText editTextFirstName;

  @NonNull
  public final EditText editTextLastName;

  @NonNull
  public final EditText editTextPassword;

  @NonNull
  public final EditText editTextPassword1;

  @NonNull
  public final ConstraintLayout frameLayout2;

  private FragmentRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull Button button3,
      @NonNull EditText editTextEmail, @NonNull EditText editTextFirstName,
      @NonNull EditText editTextLastName, @NonNull EditText editTextPassword,
      @NonNull EditText editTextPassword1, @NonNull ConstraintLayout frameLayout2) {
    this.rootView = rootView;
    this.button3 = button3;
    this.editTextEmail = editTextEmail;
    this.editTextFirstName = editTextFirstName;
    this.editTextLastName = editTextLastName;
    this.editTextPassword = editTextPassword;
    this.editTextPassword1 = editTextPassword1;
    this.frameLayout2 = frameLayout2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button3;
      Button button3 = rootView.findViewById(id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.editTextEmail;
      EditText editTextEmail = rootView.findViewById(id);
      if (editTextEmail == null) {
        break missingId;
      }

      id = R.id.editTextFirstName;
      EditText editTextFirstName = rootView.findViewById(id);
      if (editTextFirstName == null) {
        break missingId;
      }

      id = R.id.editTextLastName;
      EditText editTextLastName = rootView.findViewById(id);
      if (editTextLastName == null) {
        break missingId;
      }

      id = R.id.editTextPassword;
      EditText editTextPassword = rootView.findViewById(id);
      if (editTextPassword == null) {
        break missingId;
      }

      id = R.id.editTextPassword1;
      EditText editTextPassword1 = rootView.findViewById(id);
      if (editTextPassword1 == null) {
        break missingId;
      }

      ConstraintLayout frameLayout2 = (ConstraintLayout) rootView;

      return new FragmentRegisterBinding((ConstraintLayout) rootView, button3, editTextEmail,
          editTextFirstName, editTextLastName, editTextPassword, editTextPassword1, frameLayout2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
