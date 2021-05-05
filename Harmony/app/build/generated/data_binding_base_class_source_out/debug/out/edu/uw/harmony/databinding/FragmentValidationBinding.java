// Generated by view binder compiler. Do not edit!
package edu.uw.harmony.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import edu.uw.harmony.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentValidationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonValidationFragmentValidate;

  @NonNull
  public final ConstraintLayout frameLayout5;

  @NonNull
  public final TextView textView4;

  private FragmentValidationBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonValidationFragmentValidate, @NonNull ConstraintLayout frameLayout5,
      @NonNull TextView textView4) {
    this.rootView = rootView;
    this.buttonValidationFragmentValidate = buttonValidationFragmentValidate;
    this.frameLayout5 = frameLayout5;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentValidationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentValidationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_validation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentValidationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_validationFragment_validate;
      Button buttonValidationFragmentValidate = rootView.findViewById(id);
      if (buttonValidationFragmentValidate == null) {
        break missingId;
      }

      ConstraintLayout frameLayout5 = (ConstraintLayout) rootView;

      id = R.id.textView4;
      TextView textView4 = rootView.findViewById(id);
      if (textView4 == null) {
        break missingId;
      }

      return new FragmentValidationBinding((ConstraintLayout) rootView,
          buttonValidationFragmentValidate, frameLayout5, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}