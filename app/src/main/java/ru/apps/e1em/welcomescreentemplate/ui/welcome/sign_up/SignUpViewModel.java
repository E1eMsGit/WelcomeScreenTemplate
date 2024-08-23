package ru.apps.e1em.welcomescreentemplate.ui.welcome.sign_up;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import ru.apps.e1em.welcomescreentemplate.R;

public class SignUpViewModel extends ViewModel {
    private MutableLiveData<SignUpFormState> loginFormState = new MutableLiveData<>();

    LiveData<SignUpFormState> getLoginFormState() {
        return loginFormState;
    }

    public void loginDataChanged(String username, String email, String password, String repeatPassword) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new SignUpFormState(R.string.invalid_username,null, null, null));
        } else if (!isEmailValid(email)) {
            loginFormState.setValue(new SignUpFormState(null, R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new SignUpFormState(null, null, R.string.invalid_password, null));
        } else if (!isRepeatPasswordValid(password, repeatPassword)) {
            loginFormState.setValue(new SignUpFormState(null, null, null, R.string.invalid_repeat_password));
        } else {
            loginFormState.setValue(new SignUpFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder email validation check
    private boolean isEmailValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return false;
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    // A placeholder repeatPassword validation check
    private boolean isRepeatPasswordValid(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }
}
