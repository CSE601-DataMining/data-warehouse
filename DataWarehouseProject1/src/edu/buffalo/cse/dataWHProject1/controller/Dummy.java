package edu.buffalo.cse.dataWHProject1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.buffalo.cse.dataWHProject1.model.FormData;
import edu.buffalo.cse.dataWHProject1.utils.Statistics;
 
@Controller
public class Dummy {
	@Autowired
	private DataService dataService;
 
    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
 
        String message = "<br><div align='center'>" + "Message from modal <br>";
        return new ModelAndView("welcome", "message", message);
    }
    
    @RequestMapping("/datamining")
    public ModelAndView datamining() {
 
        String message = "<br><div align='center'>" + "Message from modal <br>";
        return new ModelAndView("datamining", "message", message);
    }
    
    @RequestMapping(value="/query", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pings(@ModelAttribute("formData") FormData formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	/*Map<String, String> dummyInputMap = new HashMap<String, String>();
    	dummyInputMap.put("queryNum", "2-3");
    	dummyInputMap.put("name", "ALL");
    	dummyInputMap.put("mu_id", "001");
    	dummyInputMap.put("cl_id", "00002");*/
	    List<Map<String, Object>> result = dataService.query(formData.getInputMap());
	    return fetchResult(result);
    }
    
    @RequestMapping(value="/querystats4", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pingsStats4(@ModelAttribute("formData") FormData formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	Map<String, String> inMap = formData.getInputMap();
    	inMap.put("queryNum", "2-4");
	    List<Map<String, Object>> result1 = dataService.query(inMap);
	    inMap.put("queryNum", "2-45");
	    List<Map<String, Object>> result2 = dataService.query(inMap);
		double result = Statistics.ttest(Statistics.GetValues(result1),Statistics.GetValues(result2));
	    return "<h4>T Statistics result: " + result + "</h4>";
    }
    
    @RequestMapping(value="/querystats5", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pingsStats5(@ModelAttribute("formData") FormData formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	Map<String, String> inMap = formData.getInputMap();
    	inMap.put("name", "ALL");
	    List<Map<String, Object>> result1 = dataService.query(inMap);
	    inMap.put("name", "AML");
	    List<Map<String, Object>> result2 = dataService.query(inMap);
	    inMap.put("name", "colon tumor");
	    List<Map<String, Object>> result3 = dataService.query(inMap);
	    inMap.put("name", "breast tumor");
	    List<Map<String, Object>> result4 = dataService.query(inMap);
		double result = Statistics.fstat(Statistics.GetValues(result1),
				Statistics.GetValues(result2),
				Statistics.GetValues(result3),
				Statistics.GetValues(result4));
		return "<h4>F Statistics result: " + result + "</h4>";
    }
    
    @RequestMapping(value="/querystats6", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pingsStats6(@ModelAttribute("formData") FormData formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	Map<String, String> inMap = formData.getInputMap();
    	inMap.put("name", "ALL");
    	StringBuilder sb = new StringBuilder();
	    List<Map<String, Object>> result1 = dataService.query(inMap);
	    double result = Statistics.parttwo6(result1,null);
	    sb.append("<h4>ALL - ALL " + result + "</h4><br />");
	    inMap.put("name", "AML");
	    List<Map<String, Object>> result2 = dataService.query(inMap);
	    double resultaml = Statistics.parttwo6(result1,result2);
	    sb.append("<h4>ALL - AML " + resultaml + "</h4>");
		
	    return sb.toString();
    }
    
    //For Part III - 1
    @RequestMapping(value="/querystats31", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pingsStats31(@ModelAttribute("formData") FormData formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	Map<String, String> inMap = formData.getInputMap();
    	inMap.put("queryNum", "3-1");
	    List<Map<String, Object>> result1 = dataService.query(inMap);
	    inMap.put("queryNum", "3-15");
	    List<Map<String, Object>> result2 = dataService.query(inMap);
	    List<Integer> infoGene = Statistics.FindInfoGene(result1, result2);
	    StringBuilder sb = new StringBuilder();
	    sb.append("<b>Count of infogene:</b> " + infoGene.size() + "<br>");
	    sb.append("<table><tr><th> Infogenes </th></tr>");
	    for(Integer i : infoGene){
	    	sb.append("<tr><td> " + i + "</td></tr>");
	    }
	    sb.append("</table>");
	    return sb.toString();
    }
    
  //For Part III - 2
    @RequestMapping(value="/querystats32", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String pingsStats32(@ModelAttribute("formData") FormData formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	Map<String, String> inMap = formData.getInputMap();
    	inMap.put("queryNum", "3-1");
    	List<Map<String, Object>> result1 = dataService.query(inMap);
		
		inMap.put("queryNum", "3-15");
		List<Map<String, Object>> result2 = dataService.query(inMap);
		
		List<Integer> infoGene = Statistics.FindInfoGene(result1, result2);
		
		String UIDs = "";
		for (Integer UID : infoGene)
		{
			UIDs += UID +",";
		}
		System.out.println(UIDs);
		inMap.put("queryNum", "3-2");
		inMap.put("name", "ALL");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		List<Map<String, Object>> result3 = dataService.query(inMap);
		
		inMap.put("queryNum", "3-25");
		inMap.put("name", "ALL");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		List<Map<String, Object>> result4 = dataService.query(inMap);
		inMap.put("queryNum", "3-26");
		
		inMap.put("patientID", "test1");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		List<Map<String, Object>> result5 = dataService.query(inMap);
		ArrayList<Double> corr1 =  Statistics.partThree2(result3, result5);
		ArrayList<Double> corr2 =  Statistics.partThree2(result4, result5);
		boolean patient1 = Statistics.partThree2Result(corr1, corr2);
		
		inMap.put("patientID", "test2");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		result5 = dataService.query(inMap);
		corr1 =  Statistics.partThree2(result3, result5);
		corr2 =  Statistics.partThree2(result4, result5);
		boolean patient2 = Statistics.partThree2Result(corr1, corr2);
		
		inMap.put("patientID", "test3");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		result5 = dataService.query(inMap);
		corr1 =  Statistics.partThree2(result3, result5);
		corr2 =  Statistics.partThree2(result4, result5);
		boolean patient3 = Statistics.partThree2Result(corr1, corr2);
		
		inMap.put("patientID", "test4");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		result5 = dataService.query(inMap);
		corr1 =  Statistics.partThree2(result3, result5);
		corr2 =  Statistics.partThree2(result4, result5);
		boolean patient4 = Statistics.partThree2Result(corr1, corr2);
		
		inMap.put("patientID", "test5");
		inMap.put("UIDs", UIDs.substring(0, UIDs.length()-1));
		result5 = dataService.query(inMap);
		corr1 =  Statistics.partThree2(result3, result5);
		corr2 =  Statistics.partThree2(result4, result5);
		boolean patient5 = Statistics.partThree2Result(corr1, corr2);
		
		StringBuilder sb = new StringBuilder();
		String classString = "";
		sb.append("<table><tr><th>Patient Sample</th> <th>Classification </th></tr>");
		sb.append("<tr><td> Patient 1 </td><td> " + classify(patient1) + " </td></tr>");
		sb.append("<tr><td> Patient 2 </td><td> " + classify(patient2) + " </td></tr>");
		sb.append("<tr><td> Patient 3 </td><td> " + classify(patient3) + " </td></tr>");
		sb.append("<tr><td> Patient 4 </td><td> " + classify(patient4) + " </td></tr>");
		sb.append("<tr><td> Patient 5 </td><td> " + classify(patient5) + " </td></tr>");
		sb.append("</table>");
	    return sb.toString();
    }
    
    private String classify(boolean result){
    	if(result){
    		return "has ALL";
    	}
    	else{
    		return "does not have ALL";
    	}
    }
    
    @RequestMapping(value="/populate", produces="text/plain", method = RequestMethod.POST)
    @ResponseBody
    public String populate(@RequestBody String formData) {
    	//For testing use the below map else replace it with formData.getInputMap()
    	//JSONObject jsonObject = JSONObject.fromObject(json);
        //String formData = jsonObject.get("message").toString();
    	System.out.println("POPSTRING " + formData);
    	String key = formData.split("=")[0];
    	Map<String, String> dummyInputMap = new HashMap<String, String>();
    	dummyInputMap.put("key", key);
	    List<Map<String, Object>> result = dataService.queryToPopulate(dummyInputMap);
	    StringBuilder sb = new StringBuilder();
	    for(Map<String, Object> row : result){
	    	for(Map.Entry<String, Object> map : row.entrySet()){
	    		sb.append(map.getValue() + "|");
	    	}
	    }
	    return sb.toString();
    }
    
    private String fetchResult(List<Map<String, Object>> result){
    	if (result.size() == 0)
    	    return "No record found.";
    	     
    	    StringBuilder sb = new StringBuilder();
    	    //sb.append("Form input is: " + formData.getInputMap() + "\n");
    	    sb.append("<table border='1'>");
    	    boolean isFirstEntryDone = false;
    	    int resultCount = result.size();
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
    	    sb.append("|" + resultCount);
        	return sb.toString();
    }
}