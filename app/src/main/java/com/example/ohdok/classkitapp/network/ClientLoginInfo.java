package com.example.ohdok.classkitapp.network;

import com.google.gson.annotations.JsonAdapter;

/**
 * Created by Yang Si Young on 2016-07-30.
 */
public class ClientLoginInfo {

    private String email;
    private String password;

    public ClientLoginInfo(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }


}
