package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataBlock {
    
    private final String HEADER = "Header";
    
    private int numberOfRows = 0;
    
    private int freeSpacePointer;
    
    private List<String> rowOffsets = new ArrayList<String>();
    
    private Map<String, DataRow> dataRows = new LinkedHashMap<String, DataRow>();
    
    public DataBlock() {
        super();
    }
    
    public int getNumberOfRows(){
        return numberOfRows;
    }
    
    public void setNumberOfRows(int numberOfRows){
        this.numberOfRows = numberOfRows;
    }
    
    public int getFreeSpacePointer(){
        return freeSpacePointer;
    }
    
    public void setFreeSpacePointer(int freeSpacePointer){
        this.freeSpacePointer = freeSpacePointer;
    }
    
    public List<String> getRowOffsets(){
        return rowOffsets;
    }
    
    public String getRowOffsetsAsString(){
        String offsets = "";
        for(String offset : rowOffsets){
            offsets += offset;
        }
        return offsets;
    }
    
    public void InsertOffset(String offset){
        rowOffsets.add(offset);
    }
    
    public void ClearOffsets(){
        rowOffsets.clear();
    }
    
    public Map<String, DataRow> getDataRows(){
        return dataRows;
    }
    
    public void InsertRow(String id, DataRow row){
        dataRows.put(id, row);
    }
}
