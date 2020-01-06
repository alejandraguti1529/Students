package com.java.student;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * implementation class Controller
 */
@WebServlet("/Controller")
@MultipartConfig(maxFileSize = 16177215)  
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	protected InputStream photo = null;

	 public void init() {
	        String URL = getServletContext().getInitParameter("URL");
	        String username = getServletContext().getInitParameter("username");
	        String password = getServletContext().getInitParameter("password");
	 
	        studentDAO = new StudentDAO(URL, username, password);
	 
	    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, NullPointerException,IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 String action = ""; 
		 action = request.getParameter("action");
		System.out.println("action"+action);
	        try {
	            switch (action) {
	            case "list":
	            	listStudent(request, response);
	                break;
	            case "new":
	            	showNewForm(request, response);
	                break;
	            case "edit":
	            	showEditForm(request, response);
	                break;
	            case "insert":
	            	insertStudent(request, response);
	                break;
	            case "update":
	                updateStudent(request, response);
	                break;
	            case "delete":
	            	deleteStudent(request, response);
	                break;
	            case "view":
	            	int id = Integer.parseInt(request.getParameter("id"));
	        		System.out.print("id"+id);
	            	studentDAO.imgList(id, response);
	            default:
	            	listStudent(request, response);
	                break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> listStudent =  studentDAO.listAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("query.jsp");
        dispatcher.forward(request, response);
    }
	
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Part filePart = request.getPart("photo");
        //InputStream photo = request.getParameter("photo");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String curp = request.getParameter("curp");
        char gender = request.getParameter("gender").charAt(0);
        String birthday = request.getParameter("birthday");
        photo = filePart.getInputStream();        
        Student student = new Student(id, photo, name, address,  curp,  gender,  birthday);
        studentDAO.insertStudent(student);
        response.sendRedirect("Controler?action=list");
    }
    

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Add.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        Student eStudent = studentDAO.getStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Add.jsp");
        request.setAttribute("student", eStudent);
        dispatcher.forward(request, response);
 
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Part filePart = request.getPart("photo");
        //InputStream photo = request.getParameter("photo");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String curp = request.getParameter("curp");
        char gender = request.getParameter("gender").charAt(0);
        String birthday = request.getParameter("birthday");
 
        photo = filePart.getInputStream();
        
        Student student = new Student( id, photo, name, address, curp, gender, birthday);
        studentDAO.updateStudent(student);
        response.sendRedirect("Controler?action=list");
    }
 
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Student student = new Student(id);
        studentDAO.deleteStudent(student);
        response.sendRedirect("Controler?action=list");
 
    }
    
    
}
