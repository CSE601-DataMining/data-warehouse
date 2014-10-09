package edu.buffalo.cse.dataWHProject1.controller;


import java.util.List;
import java.util.Map;


import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

//@Service("personService")
@Repository
public class DataService {
	@Autowired
	public DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> query(Map<String, String> inMap) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		String queryNum = inMap.get("queryNum");
		if(queryNum.equals("2-1")){
			return queryPartTwo1(inMap);
		}
		else if(queryNum.equals("2-2")){
			return queryPartTwo2(inMap);
		}
		else if (queryNum.equals("2-3")) {
			return queryPartTwo3(inMap);
		}
		else if (queryNum.equals("2-4")) {
			return queryPartTwo4(inMap);
		}
		else if (queryNum.equals("2-45")) {
			return queryPartTwo45(inMap);
		}
		else if (queryNum.equals("2-5")) {
			return queryPartTwo5(inMap);
		}
		else if (queryNum.equals("2-6")) {
			return queryPartTwo6(inMap);
		}
		else if (queryNum.equals("3-1")) {
			return queryPartThree1(inMap);
		}
		else if (queryNum.equals("3-15")) {
			return queryPartThree15(inMap);
		}
		else{
			return null;
		}
	}
	
	private List<Map<String, Object>> queryPartTwo1(Map<String, String> inMap){
		String queryString = "";
		String queryFor = inMap.get("key");
		String value = inMap.get("val");
		if(queryFor.equals("description")){
			queryString = "select count(p.p_id) from patient p join diagnosis dg on p.p_id = dg.p_id join "
					+ " disease ds on ds.ds_id = dg.ds_id where ds.description ='" + value + "'";
		}
		else if (queryFor.equals("type")) {
			queryString = "select p.name,p.p_id from patient p join diagnosis dg on p.p_id = dg.p_id join "
					+ " disease ds on ds.ds_id = dg.ds_id where ds.type ='" + value + "'";
		}
		else if (queryFor.equals("name")) {
			queryString = "select p.name,p.p_id from patient p join diagnosis dg on p.p_id = dg.p_id join "
					+ " disease ds on ds.ds_id = dg.ds_id where ds.name ='" + value + "'";
		}
		
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartTwo2(Map<String, String> inMap){
		String queryString = "";
		String description = inMap.get("description");
		queryString = "select distinct dr.dr_type from patient p join diagnosis dg on p.p_id = dg.p_id join "
				+ " disease ds on ds.ds_id = dg.ds_id join drug_use dgu on dgu.p_id=p.p_id join "
				+ " drug dr on dr.dr_id=dgu.dr_id where ds.description ='" + description + "'";
		
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartTwo3(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		int muID = Integer.parseInt(inMap.get("mu_id"));
		int clID = Integer.parseInt(inMap.get("cl_id"));
		queryString = "select mf.exp " +
						"from clinical_fact cf  " +
						"join disease ds on ds.ds_id = cf.ds_id  " +
						"join drug dr on dr.dr_id=cf.dr_id  " +
						"join microarray_fact mf on cf.s_id = mf.s_id " +
						"join probe pb on mf.pb_id = pb.pb_id " +
						"join gene_fact gf on pb.UID = gf.UID " +
						"where ds.name='" + name + "'" +
						"and mf.mu_id =" + muID + "" +
						"and gf.cl_id =" + clID + "";
		
		return jdbcTemplate.queryForList(queryString);
	} 
	
	private List<Map<String, Object>> queryPartTwo4(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		int goID = Integer.parseInt(inMap.get("go_id"));
		queryString = "select mf.exp "+
				"from gene_fact gf "+
				"join probe pb on gf.UID = pb.UID "+
				"join microarray_fact mf on pb.pb_id = mf.pb_id "+
				"join clinical_fact cf on mf.s_id = cf.s_id "+
				"join disease ds on ds.ds_id = cf.ds_id "+
				"where gf.go_id = "+goID+" "+
				"and ds.name='"+name+"'"; 
				
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartTwo45(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		int goID = Integer.parseInt(inMap.get("go_id"));
		queryString = "select mf.exp "+
				"from gene_fact gf "+
				"join probe pb on gf.UID = pb.UID "+
				"join microarray_fact mf on pb.pb_id = mf.pb_id "+
				"join clinical_fact cf on mf.s_id = cf.s_id "+
				"join disease ds on ds.ds_id = cf.ds_id "+
				"where gf.go_id = "+goID+" "+
				"and ds.name<>'"+name+"'"; 
				
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartTwo5(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		int goID = Integer.parseInt(inMap.get("go_id"));
		queryString = "select mf.exp "+
				"from gene_fact gf "+
				"join probe pb on gf.UID = pb.UID "+
				"join microarray_fact mf on pb.pb_id = mf.pb_id "+
				"join clinical_fact cf on mf.s_id = cf.s_id "+
				"join disease ds on ds.ds_id = cf.ds_id "+
				"where gf.go_id = "+goID+" "+
				"and ds.name='"+name+"' "+
				"order by cf.p_id"; 
				
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartTwo6(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		int goID = Integer.parseInt(inMap.get("go_id"));
		queryString = "select cf.p_id,mf.exp "+
				"from gene_fact gf "+
				"join probe pb on gf.UID = pb.UID "+
				"join microarray_fact mf on pb.pb_id = mf.pb_id "+
				"join clinical_fact cf on mf.s_id = cf.s_id "+
				"join disease ds on ds.ds_id = cf.ds_id "+
				"where gf.go_id = "+goID+" "+
				"and ds.name='"+name+"' "+
				"order by cf.p_id"; 
				
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartThree1(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		queryString = "select pb.UID,mf.exp "+
						"from probe pb  "+
						"join microarray_fact mf on pb.pb_id = mf.pb_id "+
						"join clinical_fact cf on mf.s_id = cf.s_id "+
						"join disease ds on ds.ds_id = cf.ds_id "+
						"where ds.name='"+name+"' "+
						"order by cf.p_id  ";
				
		return jdbcTemplate.queryForList(queryString);
	}
	
	private List<Map<String, Object>> queryPartThree15(Map<String, String> inMap){
		String queryString = "";
		String name = inMap.get("name");
		queryString = "select pb.UID,mf.exp "+
						"from probe pb  "+
						"join microarray_fact mf on pb.pb_id = mf.pb_id "+
						"join clinical_fact cf on mf.s_id = cf.s_id "+
						"join disease ds on ds.ds_id = cf.ds_id "+
						"where ds.name<>'"+name+"' "+
						"order by cf.p_id  ";
				
		return jdbcTemplate.queryForList(queryString);
	}


	
}
