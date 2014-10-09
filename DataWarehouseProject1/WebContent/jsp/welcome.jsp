<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function ajaxQuery() {
    	var str = $("#inputForm").serialize();
        $.ajax({
        	type : "post",
            url : 'query',
            data : str,
            success : function(data) {
                $('#result').html(data);
            },
            failure : function(data) {
                $('#result').html("Ajax failed");
            }
        });
        
        return false;
    }
    
    $( document ).ready(function() {
    	//ajaxQuery();
    });
    
    $( '#inputForm' ).submit(function( event ) {
    	event.preventDefault();
    	ajaxQuery();
    });
    
</script>


</head>
<body>
	
	<div align="center">
        <br> <br> ${message} <br> <br>
        
        <form:form method="post" onsubmit="return ajaxQuery();" modelAttribute="formData" id = "inputForm">
        	<table>
        		<tr>
        			<td> username </td>
        			<td> <input name="inputMap['key']" /> </td>
        			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        			<td> password </td>
        			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        			<td> <input name="inputMap['val']" /> </td>
        		</tr>
        	</table>
        	<input type="hidden" name="inputMap['queryNum']" value="2-1" />
        	<input type="submit" value="query" />
        </form:form>
        
        <div id="result"></div>
         
        <br>
        <p>
        </p>
    </div>
</body>
</html>