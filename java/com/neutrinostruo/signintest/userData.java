package com.neutrinostruo.signintest;

/**
 * Created by Devesh Anand on 04-02-2017.
 */

public class userData {

    private String email;
    private String password;
    private String displayPictureUri;
    private String displayName;
    private String mobile;
    private String admissionNumber;
    private String branch;
    private String year;
    private String section;

    public userData(){

    }

    public userData (String email, String password, String displayName,String displayPictureUri, String mobile,String admissionNumber,
                     String branch, String year, String section){
        this.email = email;
        this.password = password;
        this.displayPictureUri = displayPictureUri;
        this.displayName = displayName;
        this.mobile = mobile;
        this.admissionNumber = admissionNumber;
        this.branch = branch;
        this.year= year;
        this.section = section;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getDisplayPictureUri(){
        return displayPictureUri;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String getMobile(){
        return mobile;
    }

    public String getAdmissionNumber(){
        return admissionNumber;
    }

    public String getBranch(){
        return branch;
    }

    public String getSection(){
        return section;
    }

}
