package com.example.mich.workingwithadvancedviews;


public class Workers {

    // Member variables
    private String mName;
    private String mDepartment;


    //creates the object
    public Workers(String _name, String _department ) {

        mName = _name;
        mDepartment = _department;

    }

    // Getter methods.
    public String getName() { return mName;}
    public String getDepartment() { return mDepartment;}

    // Setter Methods
    public void setName(String _name) { mName = _name; }
    public void setDepartment(String _department) { mDepartment = _department; }

}
