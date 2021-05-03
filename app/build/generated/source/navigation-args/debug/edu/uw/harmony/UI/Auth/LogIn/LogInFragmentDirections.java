package edu.uw.harmony.UI.Auth.LogIn;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import edu.uw.harmony.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class LogInFragmentDirections {
  private LogInFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionLogInFragmentToRegisterFragment() {
    return new ActionOnlyNavDirections(R.id.action_logInFragment_to_registerFragment);
  }

  @NonNull
  public static ActionLogInFragmentToMainActivity actionLogInFragmentToMainActivity(
      @NonNull String jwt, @NonNull String email) {
    return new ActionLogInFragmentToMainActivity(jwt, email);
  }

  public static class ActionLogInFragmentToMainActivity implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionLogInFragmentToMainActivity(@NonNull String jwt, @NonNull String email) {
      if (jwt == null) {
        throw new IllegalArgumentException("Argument \"jwt\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("jwt", jwt);
      if (email == null) {
        throw new IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("email", email);
    }

    @NonNull
    public ActionLogInFragmentToMainActivity setJwt(@NonNull String jwt) {
      if (jwt == null) {
        throw new IllegalArgumentException("Argument \"jwt\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("jwt", jwt);
      return this;
    }

    @NonNull
    public ActionLogInFragmentToMainActivity setEmail(@NonNull String email) {
      if (email == null) {
        throw new IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("email", email);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("jwt")) {
        String jwt = (String) arguments.get("jwt");
        __result.putString("jwt", jwt);
      }
      if (arguments.containsKey("email")) {
        String email = (String) arguments.get("email");
        __result.putString("email", email);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_logInFragment_to_mainActivity;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getJwt() {
      return (String) arguments.get("jwt");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getEmail() {
      return (String) arguments.get("email");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionLogInFragmentToMainActivity that = (ActionLogInFragmentToMainActivity) object;
      if (arguments.containsKey("jwt") != that.arguments.containsKey("jwt")) {
        return false;
      }
      if (getJwt() != null ? !getJwt().equals(that.getJwt()) : that.getJwt() != null) {
        return false;
      }
      if (arguments.containsKey("email") != that.arguments.containsKey("email")) {
        return false;
      }
      if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getJwt() != null ? getJwt().hashCode() : 0);
      result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionLogInFragmentToMainActivity(actionId=" + getActionId() + "){"
          + "jwt=" + getJwt()
          + ", email=" + getEmail()
          + "}";
    }
  }
}