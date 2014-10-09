package edu.buffalo.cse.dataWHProject1.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Statistics {
	public static List<Integer> GetValues(List<Map<String,Object>> result)
	{
		List<Integer> values = new ArrayList<Integer>();
		for (Map<String, Object> row : result)
		{
			for (Map.Entry<String, Object> entry : row.entrySet())
			{
				values.add((Integer)entry.getValue());
			}
			
		}
		
		return values;
	}
	
	private static double mean(List<Integer> values)
	{
		double mean = 0;
		for (Integer value : values)
		{
			mean+= value;
		}
		
		return mean/values.size();
	}
	
	private static double variance(List<Integer> values)
	{
		double variance = 0;
		double mean = mean(values);
		for (Integer value : values)
		{
			variance+= Math.pow(value-mean,2);
		}
		return variance/(values.size()-1);
	}
	
	
	public static double ttest(List<Integer> values1, List<Integer> values2)
	{
		double m1 = mean(values1);
		double m2 = mean(values2);
		
		double var1 = variance(values1);
		double var2 = variance(values2);
		
		int n1 = values1.size();
		int n2 = values2.size();
		
		double pVar = ((n1-1)*var1 +(n2-1)*var2)/(n1+n2-2);
		
		return (m1-m2)/(Math.sqrt(pVar*(1.0d/n1 + 1.0d/n2)));
		
	}
	
	public static double fstat(List<Integer> values1,List<Integer> values2,List<Integer> values3, List<Integer> values4)
	{
		
		int N = values1.size()+values2.size()+values3.size()+values4.size();
		double m1 = mean(values1);
		double m2 = mean(values2);
		double m3 = mean(values3);
		double m4 = mean(values4);
		double oMean = (m1*values1.size()+m2*values2.size()+m3*values3.size()+m4*values4.size())/N;
		
		double ssc = Math.pow(m1-oMean, 2)*values1.size()+
				Math.pow(m2-oMean, 2)*values2.size()
				+Math.pow(m3-oMean, 2)*values3.size()
				+Math.pow(m4-oMean, 2)*values4.size();
		
		double sse = variance(values1)*(values1.size()-1) +
				variance(values2)*(values2.size()-1) +
				variance(values3)*(values3.size()-1) +
				variance(values4)*(values4.size()-1);
		
		double mserr = sse/(N-4);
		return ssc/(3*mserr);
	}

	public static double covariance(ArrayList<Integer> array1, ArrayList<Integer> array2)
	{
		double covar = 0;
		double m1 = mean(array1);
		double m2 = mean(array2);
		for (int i = 0; i<array1.size();i++)
		{
			covar += (array1.get(i) - m1)*(array2.get(i) - m2);  
		}
		
		return covar/(array1.size()-1);
		
	}
	
	public static double correlation(ArrayList<Integer> array1, ArrayList<Integer> array2)
	{
		double covar = covariance(array1,array2);
		double var1 = variance(array1);
		double var2 = variance(array2);
		
		return covar/(Math.sqrt(var1*var2));
	}
	
	public static Integer[][] getPatients(List<Map<String, Object>> result)
	{
		Integer[][] values = new Integer[result.size()][2];
		int i = 0;
		for (Map<String, Object> row : result)
		{
			int j = 0;
			for (Map.Entry<String, Object> entry : row.entrySet())
			{
				values[i][j] = (Integer)entry.getValue();
				j++;
			}
			i++;
		}
		
		return values;
		
	}
	
	public static double parttwo6(List<Map<String, Object>> result1, List<Map<String, Object>> result2)
	{
		Integer[][] values1 = getPatients(result1);
		
		//populate arrays
		HashMap<Integer,ArrayList<Integer>> patients1 = new HashMap<Integer,ArrayList<Integer>>();
		
		for (Integer[] value : values1)
		{
			if(!patients1.containsKey(value[0]))
				patients1.put(value[0],new ArrayList<Integer>());
			
			patients1.get(value[0]).add(value[1]);
		}
		
		Integer[] type = new Integer[1];
		Integer[] keys = patients1.keySet().toArray(type);
		
		double avgCorr = 0;
		if (result2 == null)
		{	for (int i = 0; i < keys.length;i++)
				for (int j = i+1; j < keys.length;j++)
					avgCorr += correlation(patients1.get(keys[i]),patients1.get(keys[j]));
			
			return avgCorr = avgCorr*2/((patients1.size()-1)*patients1.size());
		}
		else
		{
			Integer[][] values2 = getPatients(result2);
			HashMap<Integer,ArrayList<Integer>> patients2 = new HashMap<Integer,ArrayList<Integer>>();
			for (Integer[] value : values2)
			{
				patients2.get(value[0]).add(value[1]);
			}
			
			for (Map.Entry<Integer, ArrayList<Integer>> patient1 : patients1.entrySet())
				for (Map.Entry<Integer, ArrayList<Integer>> patient2 : patients2.entrySet())
					avgCorr += correlation(patient1.getValue(),patient2.getValue());
		
			return avgCorr = avgCorr/(patients1.size()*patients2.size());
		}
		
		
	}
}
