package edu.buffalo.cse.dataWHProject1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.buffalo.cse.dataWHProject1.model.FormData;
 
@Controller
public class Dummy {
	@Autowired
	private DataService dataService;
 
    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
 
        String message = "<br><div align='center'>" + "Message from modal <br>";
        return new ModelAndView("welcome", "message", message);
    }
    
    @RequestMapping(value="/query", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pings(@ModelAttribute("formData") FormData formData) {
	    List<Map<String, Object>> result = dataService.query(formData.getInputMap());
	    return fetchResult(result);
    }
    
    private String fetchResult(List<Map<String, Object>> result){
    	if (result.size() == 0)
    	    return "No record found.";
    	     
    	    StringBuilder sb = new StringBuilder();
    	    //sb.append("Form input is: " + formData.getInputMap() + "\n");
    	    sb.append("<table border='1'>");
    	    boolean isFirstEntryDone = false;
    	    for (Map<String, Object> row : result) {
    	    	//sb.append("Data " + row).append("\n");
    	    	if(!isFirstEntryDone){
    	    		sb.append("<tr>");
    	    		for (Map.Entry<String, Object> entry : row.entrySet()){
    	    			sb.append("<th>" + entry.getKey() + "</th>");
    	    		}
    	    		sb.append("</tr>");
    	    		isFirstEntryDone = true;
    	    	}
    	    	
    	    	sb.append("<tr>");
        		for (Map.Entry<String, Object> entry : row.entrySet()){
        			sb.append("<td>" + entry.getValue() + "</td>");
        		}
        		sb.append("</tr>");
    	    	
    	    }
    	    sb.append("</table>");
        	return sb.toString();
    }
}