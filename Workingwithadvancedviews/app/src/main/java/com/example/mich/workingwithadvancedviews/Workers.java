package com.example.mich.workingwithadvancedviews;

/**
 * Created by Mich on 12/7/15.
 */
public class Workers {
    // Member variables

    private String mName;
    private String mDepartment;

    // Class constructor
       /* public Workers() {
            mYearsOfService = 0;
            mName = mDepartment = "";
        }*/

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
