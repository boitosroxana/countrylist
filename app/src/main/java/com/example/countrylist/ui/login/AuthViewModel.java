package com.example.countrylist.ui.login;

import androidx.lifecycle.ViewModel;

import com.example.countrylist.models.User;
import com.example.countrylist.utils.DataManager;

public class AuthViewModel extends ViewModel {
   public void singUp(User user){
       DataManager.getInstance().signUp(user);


   }
   public boolean login(String username, String password){
       if(username.equals(DataManager.getInstance().getEmail())&& password.equals(DataManager.getInstance().getPassword()))
        return true;
       else
           return false;
   }
}
