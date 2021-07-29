import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
public class User
{
	String  uid,name,passwd,address;

	Connection con;
	Statement st;
	ResultSet rs;
	JPanel chatuser;
	//Register
	
	User()
	{
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection object and connection url
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","root");
			System.out.println("Connection Established");
			
		 }catch(SQLException | ClassNotFoundException e)
		 {
			 System.out.println("Connection can't be esatblished..");
			
		 }
	}
	User(String  uid,String name,String passwd,String address)
	{
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection object and connection url
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","root");
			System.out.println("Connection Established");
			
		 }catch(SQLException | ClassNotFoundException e)
		 {
			 System.out.println("Connection can't be esatblished..");
		 }
		this.uid=uid;this.name=name;
		this.passwd=passwd;
		this.address=address;
	}
	
	 void register(JFrame f) throws ClassNotFoundException
	{
		   //database connectivity
			//use driver class
		
			
			//get resultset
			 
//		 Scanner sc=new Scanner(System.in);
//		System.out.print("\n\t\t\t\t\t\tEnter Userid : "); uid=sc.nextLine();
//		System.out.print("\t\t\t\t\t\tEnter name : "); name=sc.nextLine();
//		System.out.print("\t\t\t\t\t\tEnter password : "); passwd=sc.nextLine();
//		System.out.print("\t\t\t\t\t\tEnter address : "); address=sc.nextLine().toLowerCase();
		
		try {
			//create statement object
			PreparedStatement stmt=con.prepareStatement("insert into users values(?,?,?,?)");  
		stmt.setString(1,uid);//1 specifies the first parameter in the query  
		stmt.setString(2,name); 
		stmt.setString(3, passwd);
		stmt.setString(4, address);
		int i=stmt.executeUpdate(); 
		
	//	System.out.println(i+" records inserted..");
		System.out.println("\n\t\t\t\tRegistration Successful\n\t\t\t\tPress 1 to login..");
		JOptionPane.showMessageDialog(f, "Registration successful..",  
                "Message",  
                JOptionPane.INFORMATION_MESSAGE);
	     
		}catch(SQLException e)
		{
			System.out.println("\n\t\t\t\tCan't Register ..Try with another user-id ..");
			JOptionPane.showMessageDialog(f, "Try another id...",  
                    "ERROR", JOptionPane.ERROR_MESSAGE); 
		}
		
	}
	 //display user
	 void display()
		{
			System.out.println("\n\n\t\t\t\t\t-----------------------------------------------");
			System.out.println("\t\t\t\t\t\tUserid : "+uid); 
			System.out.println("\t\t\t\t\t\tEnter Userid : "+name); 
			System.out.println("\t\t\t\t\tEnter Userid : "+address); 
		}
	 
	 void chat(JPanel p4s) throws Exception
	 {
		 //gui
		 JFrame f=new JFrame("Chat Client "+name.toUpperCase());
		 chatuser=new JPanel();
		//	p4s.hide();
			
			  Color c1 = new Color(235, 251, 255); 
			    chatuser.setBackground(c1);
			 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
			 
			JTextArea msgall=new JTextArea(30,30);
			Border blackline = BorderFactory.createLineBorder(Color.black);
			 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
			

			 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
			 JButton back=new JButton(backi);
			 back.setBounds(5,5,20,20);
			 back.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				
				// chatuser.hide();
				// p4s.show();
		}});
			
		    msgall.setBounds(50,50,350,270);  
			msgall.setFont(f3);
			msgall.setEditable(false);
			msgall.setBorder(blackline);
			msgall.setText("CHAT\n\n");
			
	        JTextArea sendm=new JTextArea();
			
	        sendm.setBounds(50,400,250,30);  
	        sendm.setFont(f3);
	        sendm.setBorder(blackline);
			
	        JButton send=new JButton("SEND");
	        send.setBounds(310,400,100,30);  
	        send.setFont(f3);
	     
		 chatuser.add(back);		
	     chatuser.add(msgall); chatuser.add(sendm); chatuser.add(msgall);chatuser.add(send);
	     chatuser.setVisible(true);
	     chatuser.setLayout(null);
	     chatuser.setBounds(0, 0, 500, 550); 
			
			f.add(chatuser);
			f.setLayout(null);
			f.setVisible(true);
	    	f.setSize(500,550);
	    	System.out.println("Created layoutssss.client.");
			 int PORT = 8134;
			Socket s = new Socket("127.0.0.1",PORT);
			System.out.println("Socket created..");
			
			DataInputStream din;
			din = new DataInputStream (s.getInputStream () );
//			String message="";
//	    	try {
//	    		
//				while(message!="bye") {
//					
//					System.out.println("1");
//					 
//					
//			
//				}
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
        		send.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						String message = "";
				
					
				
					System.out.println("\n\n----------Chat Client Started-----------\n");
				
						DataOutputStream dout;
						BufferedReader dis= new BufferedReader(new InputStreamReader(System.in));
						message = sendm.getText();
						
						dout = new DataOutputStream (s.getOutputStream());		
						dout.writeUTF(name+": "+message);	
						msgall.append("\n"+name+": "+message);
						
						
						message = din.readUTF();
						System.out.println("Server Sent: "+message);
						msgall.append("\nServer Sent: "+message);
						
						
					
					}catch(Exception ee)
					{
						ee.printStackTrace();
					}
					
					
				}
			});
		 
		 
		 //
		// System.out.println("\n\n\n This is "+name);
			
		//	s.close();
	 }
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public String getID() {
		// TODO Auto-generated method stub
		return uid;
	}
	public String getAddr() {
		// TODO Auto-generated method stub
		return address;
	}

}