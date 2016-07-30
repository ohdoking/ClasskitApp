package com.example.ohdok.classkitapp.network;

/**
 * Created by Yang Si Young on 2016-07-31.
 */
public class ClientGCMInfo {

    private String gcmId;

    public ClientGCMInfo(String gcmId){
        this.gcmId = gcmId;
    }

    public void setGcmId(String gcmId){
        this.gcmId = gcmId;
    }

    public String getGcmId(){
        return this.gcmId;
    }
}
