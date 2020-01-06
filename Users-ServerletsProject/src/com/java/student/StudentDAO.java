package com.java.student;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



public class StudentDAO {

    private String URL;
    private String username;
    private String password;
    private Connection jdbcConnection;
    
    public StudentDAO() {}
    public StudentDAO(String URL, String username, String password) {
        this.URL = URL;
        this.username = username;
        this.password = password;
    
    }
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(URL, username, password);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    
    public boolean insertStudent(Student student) throws SQLException {
    	//insert into Students values(1, 'Alejandra Gutiérrez', '30/11/1997', 'Guillermo Prieto #20', 'GUTA971130MJCTRL17', 'F', 'NULL');
        //String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
    	String sql = "INSERT INTO Students(id, name, birthday, address, curp, gender, photo) values(?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, student.getid());
        statement.setString(2, student.getName());
        statement.setString(3, student.getBirthday());
        statement.setString(4, student.getAddress());
        statement.setString(5, student.getCurp());
        statement.setString(6, String.valueOf(student.getGender()));  
        statement.setBlob(7, student.getPhoto());
        //statement.setBinaryStream(7, student.getPhoto());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Student> listAllStudents() throws SQLException {
    	
        List<Student> listStudents = new ArrayList<>();
         
        String sql = "Select * from Students";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	//public Student(int id, String photo, String name, String address, String curp, char gender, String birthday) 
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String birthday = resultSet.getString("birthday");
            String address = resultSet.getString("address");
            String curp = resultSet.getString("curp");
            char gender = resultSet.getString("gender").charAt(0);
            //Blob photo = resultSet.getBlob("photo");
            InputStream  photo = resultSet.getBinaryStream("photo");
             
            Student student = new Student( id, photo, name, address, curp, gender, birthday);
            listStudents.add(student);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listStudents;
    }
    
    public Student getStudent(int id) throws SQLException {
        Student student = null;
        String sql = "SELECT * FROM Students WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	 String name = resultSet.getString("name");
             String birthday = resultSet.getString("birthday");
             String address = resultSet.getString("address");
             String curp = resultSet.getString("curp");
             char gender = resultSet.getString("gender").charAt(0);;
             InputStream photo = resultSet.getBinaryStream("photo");
             
              student = new Student( id, photo, name, address, curp, gender, birthday);
        }
         
        resultSet.close();
        statement.close();
         
        return student;
    }
    
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE Students set name=?, birthday=?, address=?, curp=?, gender=?, photo =?";
        sql += "WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(7, student.getid());
        statement.setString(1, student.getName());
        statement.setString(2, student.getBirthday());
        statement.setString(3, student.getAddress());
        statement.setString(4, student.getCurp());
        statement.setString(5, String.valueOf(student.getGender()));  
        statement.setBinaryStream(6, student.getPhoto());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;  
    }
    
    public boolean deleteStudent(Student student) throws SQLException {
        String sql = "DELETE FROM Students where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, student.getid());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public void imgList(int id, HttpServletResponse response) {
    	String sql = "select photo from Students where id="+id;
    	InputStream inputStream = null; 
    	OutputStream outputStream = null;
    	BufferedInputStream bufferedInputStream = null;
    	BufferedOutputStream bufferedOutputStream= null;
    	response.setContentType("image/*");
    	try {
			outputStream = response.getOutputStream();
	        connect();
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	        	inputStream = resultSet.getBinaryStream("photo");
	        }
	        bufferedInputStream = new BufferedInputStream(inputStream);
	        bufferedOutputStream = new BufferedOutputStream(outputStream);
	        int i=0;
	        while ((i=bufferedInputStream.read())!=-1) {
	        	bufferedOutputStream.write(i);
				
			}
	        
	        inputStream.close();
	        outputStream.close();
	        bufferedInputStream.close();
	        bufferedOutputStream .close();
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

}
