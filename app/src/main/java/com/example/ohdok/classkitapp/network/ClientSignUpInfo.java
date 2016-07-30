package com.example.ohdok.classkitapp.network;

/**
 * Created by Yang Si Young on 2016-07-30.
 */
public class ClientSignUpInfo {
    private String name;
    private String email;
    private String password;
    private String educationofficer;
    private String studentnumber;
    private String school_id;
    private String registration_id;

    public ClientSignUpInfo(String name ,String email ,String password, String educationofficer, String studentnumber, String school_id, String registration_id){
        this.name = name;
        this.email = email;
        this.password = password;
        this.educationofficer = educationofficer;
        this.studentnumber = studentnumber;
        this.school_id = school_id;
        this.registration_id = registration_id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
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

    public void setEducationofficer(String educationofficer){
        this.educationofficer = educationofficer;
    }

    public String getEducationofficer(){
        return this.educationofficer;
    }

    public void setStudentnumber(String studentnumber){
        this.studentnumber = studentnumber;
    }

    public String getStudentnumber(){
        return this.studentnumber;
    }

    public void setSchool_id(String school_id){
        this.school_id = school_id;
    }

    public String getSchool_id(String school_id){
        return this.school_id;
    }

    public void setRegistration_id(String registration_id){
        this.registration_id = registration_id;
    }

    public String getRegistration_id(){
        return this.registration_id;
    }


}
