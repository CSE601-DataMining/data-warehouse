<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<style>
body{
	background-color: #eeeeee;
}

table {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    width: 100%;
    border-collapse: collapse;
}

td, th {
    font-size: 1em;
    border: 1px solid #98bf21;
    padding: 3px 7px 2px 7px;
}

th {
    font-size: 1.1em;
    text-align: left;
    padding-top: 5px;
    padding-bottom: 4px;
    background-color: #939393;
    color: #ffffff;
}

</style>

  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  
  <script type="text/javascript">
	  $(function() {
		    $( "#menu" ).menu();
		});
	  
	  $(function() {

		  $("#first-choice").change(function() {
		  //    var str="<option value="+"description"+">Disease Description</option>";
		    var val = $('#first-choice :selected').val();
		    var res = "";
		    if(val == "description"){
		    	res = populateDropDown("2-1-pop-desc", "second-choice");
		    }
		    else if(val == "type"){
		    	res = populateDropDown("2-1-pop-type", "second-choice");
		    }
		    else if(val == "name"){
		    	res = populateDropDown("2-1-pop-name", "second-choice");
		    }
		    var first = $('#first-choice :selected').val();
		    $('#result').val(first);

		      //$("#second-choice").hide();
		      //$("#result").html("here")
		 //       $("#second-choice").append("<option>Disease Description</option>");
		 //     $("#second-choice").load("textdata/" + $(this).val() + ".txt");
		    });
		  

		});
	  
	  $(function() {
		    $("#second-choice").change(function() {
		      var second = $('#second-choice :selected').val();
//		      var $result2 = $('#result');
		      $('#result2').val(second);
		    });
		    
		    //for query 2 of part II
		    $("#first-choice_2").change(function() {
			      var second = $('#first-choice_2 :selected').val();
			      $('#result_2').val(second);
			});
		    
		    //For query 3 of part II
		    $("#mu_id").change(function() {
			      var value = $('#mu_id :selected').val();
			      $('#result_mu_id').val(value);
			});
		    $("#cl_id").change(function() {
			      var value = $('#cl_id :selected').val();
			      $('#result_cl_id').val(value);
			});
		    $("#disease_name").change(function() {
			      var value = $('#disease_name :selected').val();
			      $('#result_name').val(value);
			});
		    
		    //For query 4 of Part II
		    $("#go_id").change(function() {
			      var value = $('#go_id :selected').val();
			      $('#result_go_id').val(value);
			});
		    $("#disease_name_4").change(function() {
			      var value = $('#disease_name_4 :selected').val();
			      $('#result_name_4').val(value);
			});
		    
		  //For query 5 of Part II
		    $("#go_id_5").change(function() {
			      var value = $('#go_id_5 :selected').val();
			      $('#result_go_id_5').val(value);
			});
		    /* $("#disease_name_5").change(function() {
			      var value = $('#disease_name_5 :selected').val();
			      $('#result_name_5').val(value);
			}); */
			
			//For query 6 of Part II
		    $("#go_id_6").change(function() {
			      var value = $('#go_id_6 :selected').val();
			      $('#result_go_id_6').val(value);
			});
		    /* $("#disease_name_5").change(function() {
			      var value = $('#disease_name_5 :selected').val();
			      $('#result_name_5').val(value);
			}); */
			
			//For Part III query 1
		    $("#disease_name_31").change(function() {
			      var value = $('#disease_name_31 :selected').val();
			      $('#result_name_31').val(value);
			});
			
		  //For Part III query 2
		    $("#disease_name_32").change(function() {
			      var value = $('#disease_name_32 :selected').val();
			      $('#result_name_32').val(value);
			});
		    
		});
	  
	  function populateDropDown(popuateFor, dropdownID){
		  $.ajax({
	        	type : "post",
	            url : 'populate',
	            async: false,
	            data : popuateFor,
	            success : function(data) {
	            	var res = data.split("|");
	            	var $selectTwo = $("#" + dropdownID);
	    		    $selectTwo.find('option').remove().end();
	    		    $selectTwo.append("<option>" + "Please select" + "</option>");
	    		    $.each(res, function(index, value) {
	    			$selectTwo.append("<option>" + value + "</option>");
	    		    });
	            },
	            failure : function(data) {
	                $('#resultConsole').html("Ajax failed");
	            }
	        });
	  }
	  
	  function fillResultsInDropDown(dropDownID, res){
		  
	  }
	  
	  $( '#inputForm1' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQuery(1);
	  });
	  $( '#inputForm2' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQuery(2);
	  });
	  $( '#inputForm3' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQuery(3);
	  });
	  $( '#inputForm4' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQueryStats(4);
	  });
	  $( '#inputForm5' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQueryStats(5);
	  });
	  $( '#inputForm6' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQueryStats(6);
	  });
	  $( '#inputForm31' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQueryStats(31);
	  });
	  $( '#inputForm32' ).submit(function( event ) {
	    	event.preventDefault();
	    	ajaxQueryStats(32);
	  });
	  
	  function ajaxQuery(num) {
	    	var str = $("#inputForm" + num).serialize();
	        $.ajax({
	        	type : "post",
	            url : 'query',
	            data : str,
	            success : function(data) {
	            	var res = data.split("|");
	                $('#resultTable').html("" + res[0]);
	                $('#count').html("Number of rows: "  + res[1]);
	            },
	            failure : function(data) {
	                $('#result').html("Ajax failed");
	            }
	        });
	        
	        return false;
	    }
	  
	  function ajaxQueryStats(num) {
	    	var str = $("#inputForm" + num).serialize();
	        $.ajax({
	        	type : "post",
	            url : 'querystats' + num,
	            data : str,
	            success : function(data) {
	            	//var res = data.split("|");
	                $('#resultTable').html("" + data);
	                //$('#count').html("Number of rows: "  + res[1]);
	            },
	            failure : function(data) {
	                $('#result').html("Ajax failed");
	            }
	        });
	        
	        return false;
	    }
  </script>
  
   <script>
  $(function() {
    $( "#tabs" ).tabs({
    	activate: function(event ,ui){
            //console.log(event);
            //alert(  ui.newTab.index());
			//alert( ui.newTab.attr('li',"innerHTML")[0].getElementsByTagName("a")[0].innerHTML);
			//alert( this.text);
			$("#resultTable").empty();
			$("#count").empty();
			if(ui.newTab.index() == "2"){ //query 3
				populateDropDown("2-3-pop-clid", "cl_id");
				populateDropDown("2-3-pop-name", "disease_name");
			}
			else if(ui.newTab.index() == "3"){ //query 4
				populateDropDown("2-4-pop-goid", "go_id");
				populateDropDown("2-3-pop-name", "disease_name_4");
			}
			else if(ui.newTab.index() == "4"){ //query 5
				populateDropDown("2-4-pop-goid", "go_id_5");
				//populateDropDown("2-3-pop-name", "disease_name_5");
			}
			else if(ui.newTab.index() == "5"){ //query 6
				populateDropDown("2-4-pop-goid", "go_id_6");
				//populateDropDown("2-3-pop-name", "disease_name_5");
			}
			else if(ui.newTab.index() == "6"){ //query 3-1
				populateDropDown("2-3-pop-name", "disease_name_31");
			}
			else if(ui.newTab.index() == "7"){ //query 3-2
				populateDropDown("2-3-pop-name", "disease_name_32");
			}
        }
    });
  });
  </script>
</head>
<body>
<div id ="intro">
        <h1>Data Mining - CSE 601 </h1> 
        <h1>Project 2 - Data Warerhouse/OLAP System </h1> 
      
            <ul> 
              <li>Ankit Arora</li>
              <li>Dilip Pednekar</li>
              <li>Kaustubh Vartak</li>
            </ul>
    
    </div> 
<div id="tabs">
    <ul>
      <li><a href="#tabs-1">Query 1</a></li>
      <li><a href="#tabs-2">Query 2</a></li>
      <li><a href="#tabs-3">Query 3</a></li>
      <li><a href="#tabs-4">Query 4</a></li>
      <li><a href="#tabs-5">Query 5</a></li>
      <li><a href="#tabs-6">Query 6</a></li>
      <li><a href="#tabs-7">Part III - 1</a></li>
      <li><a href="#tabs-8">Part III - 2</a></li>
    </ul>
    <div id="tabs-1">
        <select id="first-choice">
          <option selected value="base">Please Select</option>
          <option value="description">Disease Description</option>
          <option value="name">Disease Name</option>
          <option value="type">Disease Type</option>
        </select>

        <select id="second-choice">
          <option>Please choose from above</option>
        </select>  
        
        <form:form method="post" onsubmit="return ajaxQuery(1);" modelAttribute="formData" id = "inputForm1">
                <input id="result" type="hidden" name="inputMap['key']">
                <input id="result2" type="hidden" name="inputMap['val']">
                <input type="hidden" name="inputMap['queryNum']" value="2-1" />
        		<input type="submit" value="query" />
        </form:form>
            
        <div id="result"> </div>
        <br>
        <p>
    </div>
  <div id="tabs-2">
    <p>Enter disease description</p>
    <select id="first-choice_2" required>
          <option selected value="base_2">Select description</option>
          <option value="tumor">Tumor</option>
          <option value="desc. of flue">Desp. of flue</option>
    </select> 
    <form:form method="post" onsubmit="return ajaxQuery(2);" modelAttribute="formData" id = "inputForm2">
                <input id="result_2" type="hidden" name="inputMap['description']">
                <input type="hidden" name="inputMap['queryNum']" value="2-2" />
        		<input type="submit" value="query" />
    </form:form>
  </div>
  <div id="tabs-3">
    <b>MU ID</b><select id="mu_id">
          <option>Select measure unit id</option>
          <option value="1">001</option>
          <option value="2">002</option>
    </select>
	&nbsp;&nbsp;<b>CL ID</b><select id="cl_id">
          <option>Select cluster id</option>
     </select>
     &nbsp;&nbsp;<b>Disease</b><select id="disease_name">
          <option>Select name</option>
     </select>
    <form:form method="post" onsubmit="return ajaxQuery(3);" modelAttribute="formData" id = "inputForm3">
                <input id="result_mu_id" type="hidden" name="inputMap['mu_id']">
                <input id="result_cl_id" type="hidden" name="inputMap['cl_id']">
                <input id="result_name" type="hidden" name="inputMap['name']">
                <input type="hidden" name="inputMap['queryNum']" value="2-3" />
        		<input type="submit" value="query" />
    </form:form> 
  </div>
  <div id="tabs-4">
    <b>Go ID</b><select id="go_id">
          <option>Select Go ID</option>
     </select>
     &nbsp;&nbsp;<b>Disease</b><select id="disease_name_4">
          <option>Select name</option>
     </select>
     <form:form method="post" onsubmit="return ajaxQueryStats(4);" modelAttribute="formData" id = "inputForm4">
                <input id="result_go_id" type="hidden" name="inputMap['go_id']">
                <input id="result_name_4" type="hidden" name="inputMap['name']">
                <input type="hidden" name="inputMap['queryNum']" value="2-4" />
        		<input type="submit" value="query" />
    </form:form> 
  </div>
  <div id="tabs-5">
    <b>Go ID</b><select id="go_id_5">
          <option>Select Go ID</option>
     </select>
      <!-- &nbsp;&nbsp;<b>Disease</b><select id="disease_name_5">
          <option>Select name</option>  
     </select> -->
     <form:form method="post" onsubmit="return ajaxQueryStats(5);" modelAttribute="formData" id = "inputForm5">
                <input id="result_go_id_5" type="hidden" name="inputMap['go_id']">
                <!--   <input id="result_name_4" type="hidden" name="inputMap['name']"> -->
                <input type="hidden" name="inputMap['queryNum']" value="2-5" />
        		<input type="submit" value="query" />
    </form:form> 
  </div>
  <div id="tabs-6">
    <b>Go ID</b><select id="go_id_6">
          <option>Select Go ID</option>
     </select>
      <!-- &nbsp;&nbsp;<b>Disease</b><select id="disease_name_5">
          <option>Select name</option>  
     </select> -->
     <form:form method="post" onsubmit="return ajaxQueryStats(6);" modelAttribute="formData" id = "inputForm6">
                <input id="result_go_id_6" type="hidden" name="inputMap['go_id']">
                <!--   <input id="result_name_4" type="hidden" name="inputMap['name']"> -->
                <input type="hidden" name="inputMap['queryNum']" value="2-6" />
        		<input type="submit" value="query" />
    </form:form> 
  </div>
  
  <div id="tabs-7">
    <!-- <b>Go ID</b><select id="go_id_6">
          <option>Select Go ID</option>
     </select>  -->
      <b>Disease</b><select id="disease_name_31">
          <option>Select name</option>  
     </select>
     <form:form method="post" onsubmit="return ajaxQueryStats(31);" modelAttribute="formData" id = "inputForm31">
                <input id="result_name_31" type="hidden" name="inputMap['name']">
                <input type="hidden" name="inputMap['queryNum']" value="3-1" />
        		<input type="submit" value="query" />
    </form:form> 
  </div>
  
    <div id="tabs-8">
    <!-- <b>Go ID</b><select id="go_id_6">
          <option>Select Go ID</option>
     </select>  -->
      <b>Disease</b><select id="disease_name_32">
          <option>Select name</option>  
     </select>
     <form:form method="post" onsubmit="return ajaxQueryStats(32);" modelAttribute="formData" id = "inputForm32">
                <input id="result_name_32" type="hidden" name="inputMap['name']">
                <input type="hidden" name="inputMap['queryNum']" value="3-2" />
        		<input type="submit" value="query" />
    </form:form> 
  </div>

</div>

<div id="resultConsole"></div>
<div id="count"></div>
<div id="resultTable"></div>
</body>
</html>