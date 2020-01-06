<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="menu.html" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Students Application</title>
</head>
<body>
	<div class="container">
		<caption>
        <h2>
           <c:if test="${student != null}">
              Edit Student
           </c:if>
           <c:if test="${student == null}">
              Add New Student
           </c:if>
        </h2>
     	</caption>
		<c:if test="${student != null}">
            <form action="Controller?action=update" method="post" enctype="multipart/form-data">
        </c:if>
        <c:if test="${student == null}">
            <form action="Controller?action=insert" method="post" enctype="multipart/form-data">
        </c:if>
			<div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="id">ID</label>
			      <input type="text" name="id" size="5" class="form-control" value="<c:out value='${student.id}' />"/>
			    </div>
			    <div class="form-group col-md-6">
			     	<c:if test="${student != null}">
              			<img src="Controller?action=view&id=<c:out value='${student.id}'/>" border="1"  width="100" height="100">
           			</c:if>
			    	<input type="file" name="photo" class="form-control"/>
			    </div>
			    
			  </div>
			  <div class="form-row">
			    <div class="form-group col-md-12">
			      <label for="name">Name</label>
			      <input type="text" class="form-control" name="name" size="45" value="<c:out value='${student.name}' />"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputAddress">Address</label>
			    <input type="text" class="form-control" name="address" size="45" value="<c:out value='${student.address}' />"/>
			  </div>
			  <div class="form-row">
			    <div class="form-group col-md-6">
			      <label for="inputCity">CURP</label>
			      <input type="text" class="form-control" name="curp" size="45" value="<c:out value='${student.curp}' />"/>
			    </div>
			    <div class="form-group col-md-4">
			      <label for="inputState">Gender</label>
			      <select name="gender"  id="gender" class="form-control">
					<c:choose>
    					<c:when test="${student.gender == 'F'.charAt(0)}">
    						<option value="F" selected>Female</option>
			        		<option value="M">Male</option>
    					</c:when>
    					<c:when test="${student.gender == 'M'.charAt(0)}">
    						<option value="F">Female</option>
			        		<option value="M" selected>Male</option>
    					</c:when>     
    					<c:otherwise>
        					<option selected>Choose...</option>
			        		<option value="F">Female</option>
			        		<option value="M">Male</option>
    					</c:otherwise>
					</c:choose>			      
			      </select>
			    </div>
			    <div class="form-group col-md-2">
			      <label for="bd">Birthday</label>
			      <input type="Date" class="form-control" name="birthday" size="45" value="<c:out value='${student.birthday}' />"/>
			    </div>
			  </div>

			  <button type="submit" class="btn btn-info">Save</button>
		</form>	
	</div>
</body>
</html>