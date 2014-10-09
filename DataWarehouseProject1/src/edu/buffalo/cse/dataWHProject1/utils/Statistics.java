package edu.buffalo.cse.dataWHProject1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

}
