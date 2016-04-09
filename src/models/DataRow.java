package models;

import java.text.DecimalFormat;

public class DataRow {
    
    DecimalFormat formatter = new DecimalFormat("000");
    public final String NUM_COLS = "003";
    
    private String studentId;
    private String firstName;
    private String lastName;
    private boolean isDeleted = false;
    
    public DataRow(String studentId, String firstName, String lastName, boolean isDeleted) {
        super();
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isDeleted = isDeleted;
    }
    
    public String getStudentId(){
        return studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
        
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public boolean getIsDeleted(){
        return isDeleted;
    }
    
    public void setIsDeleted(boolean deleted){
        this.isDeleted = deleted;
    }
    
    public String toString(){
        String output = "";
        if(!isDeleted){
            output = NUM_COLS 
                        + formatter.format(studentId.length()) + studentId 
                        + formatter.format(firstName.length()) + firstName 
                        + formatter.format(lastName.length()) + lastName;
        }
        return output;
    }
}
