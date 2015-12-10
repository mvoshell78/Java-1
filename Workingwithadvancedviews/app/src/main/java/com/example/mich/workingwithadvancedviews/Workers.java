package com.example.mich.workingwithadvancedviews;


public class Workers {

    // Member variables
    private String mName;
    private String mJobRes;
    private String mPhone;
    private String mBio;


    //creates the object
    public Workers(String _name, String _jobRes, String _phone, String _bio ) {

        mName = _name;
        mJobRes = _jobRes;
        mPhone = _phone;
        mBio = _bio;

    }

    // Getter methods.
    public String getName() { return mName;}
    public String getJobRes() { return mJobRes;}
    public String getPhone() { return mPhone;}
    public String getBio() { return mBio;}

    // Setter Methods
    public void setName(String _name) { mName = _name; }
    public void setJobRes(String _jobRes) { mJobRes = _jobRes; }
    public void setPhone(String _Phone) { mPhone = _Phone; }
    public void setBio(String _bio) {mBio = _bio;}

}
