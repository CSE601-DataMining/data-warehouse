package edu.buffalo.cse.dataWHProject1.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Graph {
	
	// hasmap T -> T,c
	//Join(t1,t2,c)
	//Add(t1)
	//Find(t1,t2)
	//Get connections
	class Join{
		String table;
		String column;
		public Join next;	
		
		public Join(String table, String column)
		{
			this.table = table;
			this.column = column;
			this.next = null;
		}
	}
	
	public Graph()
	{
		AddTable("patient");
		AddTable("disease");
		AddTable("drug");
		AddTable("test");
		AddTable("clinical_fact");
		AddTable("sample");
		AddTable("marker");
		AddTable("assay");
		AddTable("term");
		AddTable("sample_fact");
		AddTable("probe");
		AddTable("measure_unit");
		AddTable("microarray_fact");
		AddTable("gene");
		AddTable("go");
		AddTable("cluster");
		AddTable("domain");
		AddTable("promoter");
		AddTable("gene_fact");
		AddTable("experiment_fact");
		
		Join("patient", "clinical_fact", "p_id");
		Join("disease", "clinical_fact", "ds_id");
		Join("drug", "clinical_fact", "dr_id");
		Join("test", "clinical_fact", "tt_id");
		Join("sample", "clinical_fact", "s_id");
		Join("sample", "sample_fact", "s_id");
		Join("marker", "sample_fact", "mk_id");
		Join("assay", "sample_fact", "as_id");
		Join("term", "sample_fact", "tm_id");
		Join("sample", "microarray_fact", "s_id");
		Join("probe", "microarray_fact", "pb_id");
		Join("probe", "gene", "UID");
		Join("probe", "gene_fact", "UID");
		Join("measure_unit", "microarray_fact", "mu_id");
		Join("go", "gene_fact", "go_id");
		Join("cluster", "gene_fact", "cl_id");
		Join("domain", "gene_fact", "dm_id");
		Join("promoter", "gene_fact", "pm_id");
		Join("experiment_fact", "microarray_fact", "e_id");
		
		
	}
	
	HashMap<String, Join> graph = new HashMap<String, Join>();
	HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
	ArrayList<String> tables = new ArrayList<String>();
	LinkedList<String> bfs = new LinkedList<String>();
	
	public void AddTable(String newTable)
	{
		tables.add(newTable);
		graph.put(newTable, null);
		visited.put(newTable, false);
	}
	
	public void Join(String table1, String table2, String column)
	{
		Connect(table1, table2, column);
		Connect(table2, table1, column);
	}
	
	void Connect(String table1, String table2, String column)
	{
		if (graph.get(table1) == null)
			graph.put(table1, new Join(table2, column));
		else
		{
			Join current = graph.get(table1);
			while(current.next != null)
				current = current.next;
			current.next = new Join(table2, column);
		}
		
	}
	
	public String Find(String table1, String table2)
	{
		visited.put(table1, true);
		Join current = graph.get(table1);
		
		while(current != null)
		{
			if (current.table.equalsIgnoreCase(table2))
				return "T: " + table1 + "; C: " + current.column + " T: " + current.table;
			
			current = current.next;
		}
		
		return null;
	}
	
	public void Display()
	{
		for(String table : tables)
		{
			System.out.print(table + "->");
			Join current = graph.get(table);
			while(current != null)
			{
				System.out.print(current.table+","+current.column+"->");
				current = current.next;
				
			}
			
			System.out.println();
			
		}
	}
	
	public static void main(String args[])
	{
		Graph g = new Graph();
		g.Display();
		System.out.println();
		System.out.println(g.Find("disease", "cluster"));
	}
}