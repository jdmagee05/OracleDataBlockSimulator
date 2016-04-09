package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import java.util.Map;

import java.util.Set;

import models.DataBlock;
import models.DataRow;

public class SimulatorControl {
    
    DecimalFormat formatter = new DecimalFormat("000");
    private frmSimulator view;
    private ActionListener actionListener;
    
    private DataBlock dataBlock;
    
    public SimulatorControl(frmSimulator view) {
        super();
        this.view = view;
        dataBlock = new DataBlock(); 
    }
    
    public void Insert(){
        actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dataBlock.InsertRow(view.getStudentIdField().getText(), CreateDataRow());
                dataBlock.setNumberOfRows(dataBlock.getNumberOfRows() + 1);
                String numberOfRows = formatter.format(dataBlock.getNumberOfRows());
                String rowData = "";
                
                dataBlock.ClearOffsets();
                for(DataRow row : dataBlock.getDataRows().values()){
                    if(row.getStudentId() == ""){
                        dataBlock.InsertOffset("000");
                    }
                    else{
                        dataBlock.InsertOffset(formatter.format(12 + rowData.length() + Integer.parseInt(numberOfRows)*3));
                    }
                    rowData += row.toString();
                }

                String pointerToFreeSpace = formatter.format(12 + rowData.length() + dataBlock.getRowOffsetsAsString().length() + 1);
                String output =  "Header" + numberOfRows + pointerToFreeSpace + dataBlock.getRowOffsetsAsString() + rowData; 
                view.getOutputArea().setText(output);
                
                view.getStudentIdField().setText(null);
                view.getStudentFirstNameField().setText(null);
                view.getStudentLastNameField().setText(null);
                view.getStatusLabel().setText("Insert Successful");
            }
        };
        view.getInsertButton().addActionListener(actionListener);
    }
    
    public void Update(){
        actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(dataBlock.getDataRows().keySet().contains(view.getStudentIdField().getText())){
                dataBlock.InsertRow(view.getStudentIdField().getText(), CreateDataRow());
                
                String numberOfRows = formatter.format(dataBlock.getNumberOfRows());
                String rowData = "";
                
                dataBlock.ClearOffsets();
                for(DataRow row : dataBlock.getDataRows().values()){
                    if(row.getStudentId() == ""){
                        dataBlock.InsertOffset("000");
                    }
                    else{
                        dataBlock.InsertOffset(formatter.format(12 + rowData.length() + Integer.parseInt(numberOfRows)*3));
                    }
                    rowData += row.toString();
                }
                String pointerToFreeSpace = formatter.format(12 + rowData.length() + dataBlock.getRowOffsetsAsString().length() + 1);
                String output =  "Header" + numberOfRows + pointerToFreeSpace + dataBlock.getRowOffsetsAsString() + rowData; 
                view.getOutputArea().setText(output);
                
                view.getStudentIdField().setText(null);
                view.getStudentFirstNameField().setText(null);
                view.getStudentLastNameField().setText(null);
                view.getStatusLabel().setText("Update successful");
                }
                else{
                    view.getStatusLabel().setText("Update failed. No records with that Student ID");
                }
            }
        };
        view.getUpdateButton().addActionListener(actionListener);
    }
    
    public void Delete(){
        actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(dataBlock.getDataRows().keySet().contains(view.getStudentIdField().getText())){
                DataRow deletedRow = new DataRow("", "", "", true);
                
                dataBlock.InsertRow(view.getStudentIdField().getText(), deletedRow);
                
                String numberOfRows = formatter.format(dataBlock.getNumberOfRows());
                String rowData = "";
                
                dataBlock.ClearOffsets();
                for(DataRow row : dataBlock.getDataRows().values()){
                    if(row.getStudentId() == ""){
                        dataBlock.InsertOffset("000");
                    }
                    else{
                        dataBlock.InsertOffset(formatter.format(12 + rowData.length() + Integer.parseInt(numberOfRows)*3));
                    }
                    rowData += row.toString();
                }
                String pointerToFreeSpace = formatter.format(12 + rowData.length() + dataBlock.getRowOffsetsAsString().length() + 1);
                String output =  "Header" + numberOfRows + pointerToFreeSpace + dataBlock.getRowOffsetsAsString() + rowData; 
                view.getOutputArea().setText(output);
                
                view.getStudentIdField().setText(null);
                view.getStudentFirstNameField().setText(null);
                view.getStudentLastNameField().setText(null);
                view.getStatusLabel().setText("Delete Successful");
            }
                else{
                    view.getStatusLabel().setText("Delete failed. No records with that Student ID");
                }
            }
        };
        view.getDeleteButton().addActionListener(actionListener);
    }
    
    public void Clear(){
        actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClearFields();
            }
        };
        view.getClearButton().addActionListener(actionListener);
    }
    
    
    public void Select(){
        actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                String studentId = view.getStudentIdField().getText();
                String numberOfRows = formatter.format(dataBlock.getNumberOfRows());
                String rowData = "";
                dataBlock.ClearOffsets();
                for(DataRow row : dataBlock.getDataRows().values()){
                    if(row.getStudentId() == ""){
                        dataBlock.InsertOffset("000");
                    }
                    else{
                        dataBlock.InsertOffset(formatter.format(12 + rowData.length() + Integer.parseInt(numberOfRows)*3));
                    }
                    rowData += row.toString();
                }
                String pointerToFreeSpace = formatter.format(12 + rowData.length() + dataBlock.getRowOffsetsAsString().length() + 1);
                String contentsOfDataBlock =  "Header" + numberOfRows + pointerToFreeSpace + dataBlock.getRowOffsetsAsString() + rowData; 
                
                String idLength = formatter.format(studentId.length());
                int index = contentsOfDataBlock.indexOf("003" + idLength + studentId);
                String data = contentsOfDataBlock.substring(index);
                int firstNameLength = Integer.parseInt(data.substring(("003" + idLength + studentId).length(), ("003" + idLength + studentId).length() + 3));
                String firstName = data.substring(("003" + idLength + studentId).length() + 3, ("003" + idLength + studentId).length() + 3 + firstNameLength);
                int lastNameLength = Integer.parseInt(data.substring(("003" + idLength + studentId).length() + 3 + firstNameLength, 
                                                                     ("003" + idLength + studentId).length() + 3 + firstNameLength + 3));
                String lastName = data.substring(("003" + idLength + studentId).length() + 3 + firstNameLength + 3, ("003" + idLength + studentId).length() + 3 + firstNameLength + 3 + lastNameLength);
                //String output = data.substring(0, ("003" + idLength + studentId).length() + 3 + firstNameLength + 3 + lastNameLength);
                //view.getOutputArea().setText(output);
                view.getStudentFirstNameField().setText(firstName);
                view.getStudentLastNameField().setText(lastName);
                view.getStatusLabel().setText("Select successful");
            }
            catch(StringIndexOutOfBoundsException e){
                view.getStatusLabel().setText("Select failed. No records with that Student ID");
            }
            } 
        };
        view.getSelectButton().addActionListener(actionListener);
    }
    
    private void ClearFields(){
        view.getStudentIdField().setText("");
        view.getStudentFirstNameField().setText("");
        view.getStudentLastNameField().setText("");
    }
    
    private DataRow CreateDataRow(){
        String studentId = view.getStudentIdField().getText();
        String firstName = view.getStudentFirstNameField().getText();
        String lastName = view.getStudentLastNameField().getText();
        return new DataRow(studentId, firstName, lastName, false);
    }
}
