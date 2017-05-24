package com.winterbe.react;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommentDAO {
	
	public static List<Comment> getCommentsFromStore(){
		String author = "";
		String text = " "; 
		List<Comment> comments = new ArrayList<>();
		Connection conn = null;
		String url = "jdbc:postgresql://echo.db.elephantsql.com:5432/hrlhmrtu";
		Properties props = new Properties();
		props.setProperty("user", "hrlhmrtu");
		props.setProperty("password", "0ADjEQq_NSVLXdTddG0KMjL-pKr0cFo9");
		
		
		try {
			Class.forName("org.postgresql.Driver");
		
		conn = DriverManager.getConnection(url, props);

		Statement st = conn.createStatement();
		ResultSet executeQuery = st.executeQuery("SELECT * FROM public.comment");
		while (executeQuery.next()) {
			author = executeQuery.getString(1);
			text = executeQuery.getString(2);
			
			comments.add(new Comment(author, text));
			
		} 
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		return comments;
	}
	public static void insertComment(Comment c){
		
		Connection conn = null;
		String url = "jdbc:postgresql://echo.db.elephantsql.com:5432/hrlhmrtu";
		Properties props = new Properties();
		props.setProperty("user", "hrlhmrtu");
		props.setProperty("password", "0ADjEQq_NSVLXdTddG0KMjL-pKr0cFo9");
		try {
			Class.forName("org.postgresql.Driver");
		
		conn = DriverManager.getConnection(url, props);

		Statement st = conn.createStatement();
		String query = "insert into public.comment values ('"+c.getAuthor()+"', '"+c.getText()+"')";
		st.execute(query);
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		
	}

}
