package com.example.countrylist.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countrylist.models.User;
import com.example.countrylist.utils.AuthenticationState;
import com.example.countrylist.utils.DataManager;

public class AuthViewModel extends ViewModel {

    final MutableLiveData<AuthenticationState> authenticationState =
            new MutableLiveData<>();
    String username;

    public void singUp(User user) {
        DataManager.getInstance().signUp(user);
    }

    public void LoginViewModel() {
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
        username = "";
    }

    public void authenticate(String username, String password) {
        if (login(username, password)) {
            this.username = username;
            authenticationState.setValue(AuthenticationState.AUTHENTICATED);
        } else {
            authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION);
        }
    }

    public void refuseAuthentication() {
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }


    public boolean login(String username, String password) {
        if (username.equals(DataManager.getInstance().getEmail()) && password.equals(DataManager.getInstance().getPassword()))
            return true;
        else
            return false;
    }
}
