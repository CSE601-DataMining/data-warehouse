load data infile "A:\\DataMining\\Data_For_Project1\\clinical_fact.txt" into table clinical_fact


#1 
select p.name,p.p_id from patient p join diagnosis dg on p.p_id = dg.p_id join disease ds on ds.ds_id = dg.ds_id where ds.description ='tumor'

select p.name,p.p_id from patient p join diagnosis dg on p.p_id = dg.p_id join disease ds on ds.ds_id = dg.ds_id where ds.type ='leukemia'

select dr.type,p.p_id from patient p join diagnosis dg on p.p_id = dg.p_id join disease ds on ds.ds_id = dg.ds_id where ds.name ='ALL'


#2
select distinct dr.dr_type from patient p join diagnosis dg on p.p_id = dg.p_id join disease ds on ds.ds_id = dg.ds_id join drug_use dgu on dgu.p_id=p.p_id join drug dr on dr.dr_id=dgu.dr_id where ds.description ='tumor'

select p_id from patient group by p_id having count(p_id) > 1

#3
select * from patient p join diagnosis dg on p.p_id = dg.p_id join disease ds on ds.ds_id = dg.ds_id join sample s on s.p_id = p.p_id where ds.name ='ALL'

select mf.exp
from clinical_fact cf 
join disease ds on ds.ds_id = cf.ds_id 
join drug dr on dr.dr_id=cf.dr_id 
join microarray_fact mf on cf.s_id = mf.s_id
join probe pb on mf.pb_id = pb.pb_id
join gene_fact gf on pb.UID = gf.UID
where ds.name='ALL'
and mf.mu_id = '001'
and gf.cl_id = '00002'

select * from go

#4
select mf.exp
from gene_fact gf
join probe pb on gf.UID = pb.UID
join microarray_fact mf on pb.pb_id = mf.pb_id
join clinical_fact cf on mf.s_id = cf.s_id
join disease ds on ds.ds_id = cf.ds_id
where gf.go_id = 12502
and ds.name='ALL'

select * from gene_fact 
gf.go_id = 12502