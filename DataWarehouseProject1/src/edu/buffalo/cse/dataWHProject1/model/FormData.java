package edu.buffalo.cse.dataWHProject1.model;

import java.util.HashMap;
import java.util.Map;

public class FormData {
	private Map<String, String> inputMap = new HashMap<String, String>();
	 
    public Map<String, String> getInputMap() {
        return inputMap;
    }
 
    public void setInputMap(Map<String, String> inputMap) {
        this.inputMap = inputMap;
    }

}
