<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="menu.html" %> 
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.ObjectOutputStream"%>
<html>
<head>
    <title>Students Application</title>
</head>
	<div class="container">
			<body>
		    <div align="center">
		    <caption><h2>List of Students</h2></caption>
		    
		        <table class="table table-striped table-bordered">
		            
		            <tr>
		                <th scope="col">ID</th>
		                <th scope="col">name</th>
		                <th scope="col">birthday</th>
		                <th scope="col">address</th>
		                <th scope="col">curp</th>
		                <th scope="col">gender</th>
		                <th scope="col">photo</th>
		                <th scope="col">Actions</th>
		            </tr>
		            <c:forEach var="student" items="${listStudent}">
		                <tr scope="row">
		                    <td><c:out value="${student.id}" /></td>
		                    <td><c:out value="${student.name}" /></td>
		                    <td><c:out value="${student.birthday}" /></td>
		                    <td><c:out value="${student.address}" /></td>
		                    <td><c:out value="${student.curp}" /></td>
		                    <td><c:out value="${student.gender}" /></td>
		                    <td>
		                    <img src="Controller?action=view&id=<c:out value='${student.id}'/>" border="1"  width="100" height="100">
		                    </td>
		                     <td>
                        		<a href="Controller?action=edit&id=<c:out value='${student.id}' />">Edit</a>
                        		&nbsp;&nbsp;&nbsp;&nbsp;
                        		<a href="Controller?action=delete&id=<c:out value='${student.id}' />">Delete</a>                     
                    		</td>
		                </tr>
		               
		            </c:forEach>
		        </table>
		    </div>   
		</body>
	</div>

</html>