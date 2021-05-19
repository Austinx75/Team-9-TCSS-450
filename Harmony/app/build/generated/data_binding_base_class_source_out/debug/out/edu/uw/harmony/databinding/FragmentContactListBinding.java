// Generated by view binder compiler. Do not edit!
package edu.uw.harmony.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import edu.uw.harmony.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentContactListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout layoutRoot;

  @NonNull
  public final ConstraintLayout layoutWait;

  @NonNull
  public final RecyclerView listRoot;

  @NonNull
  public final ProgressBar progressBar;

  private FragmentContactListBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout layoutRoot, @NonNull ConstraintLayout layoutWait,
      @NonNull RecyclerView listRoot, @NonNull ProgressBar progressBar) {
    this.rootView = rootView;
    this.layoutRoot = layoutRoot;
    this.layoutWait = layoutWait;
    this.listRoot = listRoot;
    this.progressBar = progressBar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentContactListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentContactListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_contact_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentContactListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout layoutRoot = (ConstraintLayout) rootView;

      id = R.id.layout_wait;
      ConstraintLayout layoutWait = rootView.findViewById(id);
      if (layoutWait == null) {
        break missingId;
      }

      id = R.id.list_root;
      RecyclerView listRoot = rootView.findViewById(id);
      if (listRoot == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = rootView.findViewById(id);
      if (progressBar == null) {
        break missingId;
      }

      return new FragmentContactListBinding((ConstraintLayout) rootView, layoutRoot, layoutWait,
          listRoot, progressBar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}