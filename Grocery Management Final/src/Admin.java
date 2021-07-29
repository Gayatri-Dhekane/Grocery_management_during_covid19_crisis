import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Admin implements ActionListener
{
	String aid,password;
	 int choice;
	Connection con;
	Statement st;
	JFrame f;
	 JButton b, b1, b2,ba,b1a,b2a,signup,rb1,rb2,rb3; 
	 JPanel p,p1,p2,pa,p1a,p2a,p3,af,dusers,dshops,shopo,orderp,chatp,shopp;
	 JLabel connect,bgimage,l1,luname,lpasswd,dmsg1,dmsg2,info;
	JPanel areq;
	 JTextArea uname;
	 JPasswordField passwd;
	 JLabel luname1,lpasswd1,lUsername,lplace;
     JTextArea uname1,Username,place;
     JPasswordField passwd1;
     JList<Shop> list,lists;
     JList<User> listu;
     DefaultListModel ll,ll2 ;
     
     JTable jtbl1;
 	DefaultListModel ll1;
 	DefaultTableModel model;
 	 Shop s1=null;
	 
	
	Admin()
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
	void createFrame() {
		 //create frame;
		 
		  
		 //frame panel
		 f=new JFrame("GROCERY SUPPY DURING COVID-19 CRISIS");//creating instance of JFrame  
		JPanel p = new JPanel(); 
		JPanel p2 = new JPanel(); 
		
		
		//color and fonts
		 Color c1 = new Color(235, 251, 255); 
		 Font f1 = new Font("TimesRoman",Font.BOLD,25);
		 ImageIcon adminIcon=new ImageIcon("src\\icons\\Admin1.PNG");
		 ImageIcon shopIcon=new ImageIcon("src\\icons\\shop2.PNG");
		 ImageIcon userIcon=new ImageIcon("src\\icons\\user1.PNG");
		 ImageIcon bg=new ImageIcon("src\\icons\\bg7.jpeg");
		 
        JLabel bgimage=new JLabel(bg);
        bgimage.setBounds(0,0,350,550);
		JLabel l1=new JLabel("GROCERY MANAGEMENT SYSTEM"); 
		JLabel l2=new JLabel(" DURING COVID-19 CRISIS ");
		l1.setFont(f1);
		l2.setFont(f1);
		l1.setBounds(20,10,820,120);
		l2.setBounds(50,50,820,140);
		
		
		 b = new JButton(adminIcon); 
		 b.setText(" ADMIN");
	     b1 = new JButton(shopIcon);  b1.setText("SHOP OWNER");
	     b2 = new JButton(userIcon);  b2.setText("CUSTOMER");
	     b.setBounds(150,180,170,50);
	     b1.setBounds(150,280,170,50);
	     b2.setBounds(150,380,170,50);
	     b.addActionListener(this); b1.addActionListener(this); b2.addActionListener(this);
	     Border blackline = BorderFactory.createLineBorder(Color.black);
		
	     
		p2.add(l1);
		p2.add(l2);
		p2.add(b); 
        p2.add(b1); 
        p2.add(b2); 
        p.add(bgimage);
        p.setBackground(Color.black); 
        p2.setBackground(c1);
        f.add(p); 
        p.setSize(350,550);
        p.setBorder(blackline); p2.setBorder(blackline);
        p2.setBounds(350, 0, 500, 550);  
        f.add(p2); 
		  
        p.setLayout(null);p2.setLayout(null);
		f.setSize(850,550);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
		
	}
	
	void loginSignup()
	{
		 f=new JFrame("LOGIN REGISTER");
		 pa = new JPanel(); 
		 p1a = new JPanel(); 
		 p2a = new JPanel(); 
		 p3=new JPanel();
		
		//color and fonts
		 Color c1 = new Color(235, 251, 255); 
		 Color c2=new Color(17, 240, 245);
		 Color c3=new Color(15, 60, 61);
		 Color c4=new Color(11, 167, 219);
		 Font f1 = new Font("TimesRoman",Font.BOLD,25);

		 ImageIcon bg=new ImageIcon("src\\icons\\c7.jpg");
		 Border blackline = BorderFactory.createLineBorder(Color.black);
		 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
		
		 //login
		 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
		 JButton back=new JButton(backi);
		 back.setBounds(5,5,20,20);
		 back.addActionListener(new ActionListener() {@Override
		public void actionPerformed(ActionEvent e) {

			 f.dispose();
			 createFrame();
			
		}});
		 
		
		dmsg1=new JLabel();
		//dmsg1.setBorder(blackline);
	    dmsg1.setFont(f2);
	    dmsg2=new JLabel();
		//dmsg2.setBorder(blackline);
	    dmsg2.setFont(f2);
		
        bgimage=new JLabel(bg);
       bgimage.setBounds(0,0,350,550);
       bgimage.setText("HELOOOOOO");
       bgimage.setForeground(Color.white);
		 l1=new JLabel("LOGIN"); 
	
		l1.setFont(f1);
	
		l1.setBounds(20,10,820,120);
		
		 luname=new JLabel("UserName"); lpasswd=new JLabel("Password");
		 uname=new JTextArea(1,20);	
		 passwd=new JPasswordField (20);	
		luname.setBounds(100, 20, 200, 25);
		lpasswd.setBounds(100,110, 200, 25);
		 uname.setBounds(100,50,200,25);
		 passwd.setBounds(100,140,200,25);
		 dmsg1.setBounds(100,250,200,25);
		 dmsg2.setBounds(100,300,200,25);
		 
		 uname.setBorder(blackline);
		 uname.setFont(f2);
		 passwd.setBorder(blackline);
		 passwd.setFont(f2);
    
		 ba = new JButton("SignIn"); 		
	     b1a = new JButton("SignUp"); 
	     b2a = new JButton("LOGIN");
	     ba.setBounds(270,60,100,30);
	     b1a.setBounds(350,60,100,30);
	     b2a.setBounds(140,200,120,30);
	    b2a.setBackground(c2);
	     	  
	    if(choice==1)
	       {
	    	   p3.hide();
				b1a.hide();
	       }
	    
	 
	     ba.setBackground(c2);
	     b1a.setBackground(c3);
	     ba.setForeground(c3);
	     b1a.setForeground(c4);
	     b1a.addActionListener(this);  
	     ba.addActionListener(this);  
	     b2a.addActionListener(this); 
	     
	    
	     
	     //sign up
	    
	         luname1=new JLabel("User ID"); lpasswd1=new JLabel("Password");
			 uname1=new JTextArea(1,20);
			 lUsername=new JLabel("User Name");
			 lplace=new JLabel("Place");
			 place=new JTextArea(1,20);
			 Username=new JTextArea(1,20);
			 passwd1=new JPasswordField (20);	
		   
			luname1.setBounds(100, 20, 200, 25);
			lpasswd1.setBounds(100,80, 200, 25);
			lUsername.setBounds(100,140, 200, 25);
			lplace.setBounds(100,200,200,25);
			
			 uname1.setBounds(100,50,200,25);
			 passwd1.setBounds(100,110,200,25);
			 Username.setBounds(100,170,200,25);
			 place.setBounds(100,230,200,25);
			 uname1.setBorder(blackline);
			 uname1.setFont(f2);
			 passwd1.setBorder(blackline);
			 passwd1.setFont(f2);
			 Username.setBorder(blackline);
			 Username.setFont(f2);
			 place.setBorder(blackline);
			 place.setFont(f2);
			 
	     
			 
		     signup = new JButton("REGISTER");
		    
		     signup.setBounds(140,270,120,30);
		     signup.setBackground(c2);
		     signup.addActionListener(this);
		     	   
		    
		 
		     //end
	    
	     //add into panels
		     //p2
		p2a.add(l1);
		p2a.add(ba); 
        p2a.add(b1a); 
        p2a.add(back);
       
       //p1
       p1a.add(luname);p1a.add(uname);p1a.add(lpasswd);p1a.add(passwd);p1a.add(b2a);
       p1a.add(dmsg1);
       //p3
       p3.add(luname1);p3.add(uname1);p3.add(lpasswd1);p3.add(passwd1);
       p3.add(lUsername);p3.add(Username);p3.add(lplace);p3.add(place);
       p3.add(signup);p3.add(dmsg2);
       
       //p
       
       pa.add(bgimage);
       pa.setBackground(Color.black); 
       p2a.setBackground(c1); p1a.setBackground(c1); p3.setBackground(c1);
       p1a.setBounds(0, 150, 500, 550);
       p3.setBounds(0, 150, 500, 550);
       f.add(pa); 
       p2a.add(p1a);
       p2a.add(p3);
       pa.setSize(350,550);
       pa.setBorder(blackline); p2a.setBorder(blackline);//p3.setBorder(blackline);p1.setBorder(blackline);
       p2a.setBounds(350, 0, 500, 550);  
       f.add(p2a); 
		  
       pa.setLayout(null);p2a.setLayout(null);p1a.setLayout(null);p3.setLayout(null);
       p3.setVisible(false);
       
      
		f.setSize(850,550);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
		
	}
	//admin
	@SuppressWarnings("deprecation")
	void adminFunctions()
	{
	
		p2a.hide();
	   
		af=new JPanel(); 
		 Color c1 = new Color(235, 251, 255); 
		 af.setBackground(c1);
		//border fonts
		 Border blackline = BorderFactory.createLineBorder(Color.black);
		 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 26);
		 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
		
		 Color c2=new Color(17, 240, 245);
		 Color c3=new Color(15, 60, 61);
		 Color c4=new Color(11, 167, 219);
		 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
		 JButton back=new JButton(backi);
		 back.setBounds(5,5,20,20);
		 back.addActionListener(new ActionListener() {@Override
		public void actionPerformed(ActionEvent e) {
			
			 af.hide();
			 p2a.show();


		}});
		 
		 JLabel title=new JLabel("ADMIN FUNCTIONS.");
		 title.setBounds(100,50,300,50);
		 title.setFont(f2);
		System.out.println("Created panel..");
		rb1=new JButton("Approve Orders");    
		rb1.setBounds(150,200,150,30);      
		rb2=new JButton("Display shops");    
		rb2.setBounds(150,270,150,30);
		rb3=new JButton("Display users");    
		rb3.setBounds(150,340,150,30); 
		
		rb1.addActionListener(this);rb2.addActionListener(this);
		rb3.addActionListener(this);
//		  
//		bookitems=new JTextArea("");
//		bookitems.setBounds(50,50,350,270);  
//		bookitems.setFont(f3);
//		bookitems.setEditable(false);
//		
//		displayAllBooks();
		   
		rb1.setBorder(blackline);
		rb1.setFont(f3);
		rb2.setBorder(blackline);
		rb2.setFont(f3);
		rb3.setBorder(blackline);
		rb3.setFont(f3);
		
		rb1.setBackground(c2);rb2.setBackground(c2);rb3.setBackground(c2);
		System.out.println("Created button..");
		af.add(title);
		af.add(rb1);af.add(rb2);af.add(rb3);af.add(back);
		p2a.setVisible(false);
		af.setVisible(true);
		af.setLayout(null);
		af.setBounds(350, 0, 500, 550); 
		System.out.println("Created layoutssss..");
		f.getContentPane().add(af);
		System.out.println("ADDDEDDDDD");
		
				
	}
	
	//login
	boolean login(String aid,String passwd)
	{
	   System.out.println(aid+"     "+passwd);	
		if(aid.equals("a") && passwd.equals("a"))
		{
			//System.out.println("\n\t\t\t\t<<<<<< LOGIN SUCCESSFUL >>>>>>");
			return true;
		}else return false;
	}
	//Register user
	void registerUser(String u,String p,String un,String pl) throws ClassNotFoundException, SQLException
	{
		User u1=new User(u,un,p,pl);
		u1.register(f);
		//users.add(u);		
		
	}
	
	//RegisterShop
	void registerShop(String u,String p,String un,String pl)
	{	
			Shop s=new Shop(u,p,un,pl,f);
		//	s.register();
			//requests.add(s);
			
			try {
				//create statement object
				PreparedStatement stmt=con.prepareStatement("insert into requests(sid,passwd,shopName,place) values(?,?,?,?)");  
			    stmt.setString(1,s.sid);//1 specifies the first parameter in the query  
			    stmt.setString(2,s.shopName);
			    stmt.setString(3,s.passwd);
			    stmt.setString(4,s.place);
			    
			int i=stmt.executeUpdate(); 
			
			System.out.println(i+" records inserted..");
			
			
			System.out.println("\t\t\t\t>>> Your registration request it sent for approval >>>");					
			System.out.println("\t\t\t\t-----------------------------------------------------------");
			dmsg2.setText("Registration succesful");
			dmsg2.setText("Registration succesful");
			JOptionPane.showMessageDialog(f, "Registration successful..Sent for approval",  
                     "Message",  
                     JOptionPane.INFORMATION_MESSAGE);
		     
			}catch(SQLException e)
			{
				e.printStackTrace();
				dmsg2.setText("Registration Failed..Try another");
				JOptionPane.showMessageDialog(f, "Try another id...",  
	                    "ERROR", JOptionPane.ERROR_MESSAGE); 
			}	
							
	}
	void loadList(DefaultListModel ll)
	{
		        
		System.out.println("\n\t\t\t\t\t\tList created---------------------------------------");
		ll.removeAllElements();
		try {
			//create statement object
		PreparedStatement stmt=con.prepareStatement("select sid,passwd,shopName,place from requests");  
		//1 specifies the first parameter in the query  

	
		ResultSet rs=stmt.executeQuery();
		System.out.println("\n\t\t\t\t\tquery executed---------------------------------------");
		if(rs.next()) {
	    rs.beforeFirst();
		while(rs.next())
		{
			Shop s=new Shop(rs.getString("sid"),rs.getString("passwd"),rs.getString("shopName"),rs.getString("place"),f);
			 System.out.println(s+"Created");
			 //approval
					System.out.println("\t\t\t\t\t\tSHOP info:");	
					System.out.println("\t\t\t\t\t\t"+s.sid);
					System.out.println("\t\t\t\t\t\t"+s.shopName);
					System.out.println("\t\t\t\t\t\t"+s.place);
					ll.addElement(s);
			 					 
		}	
		}else
		{
			info.setText("No requests pending..");
		

		}
		}catch(SQLException e)
		{
			System.out.println("SQLEXCEPTION");
			JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                    "ERROR", JOptionPane.ERROR_MESSAGE); 
		
		}	
		
	}
	//approve registration request by shops
	void approveRequest()
	{
		
		//gui
		af.hide();
		areq=new JPanel();
		Color c1 = new Color(235, 251, 255); 
		areq.setBackground(c1);
		
		 Border blackline = BorderFactory.createLineBorder(Color.black);
		 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
		 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);

		 Color c2=new Color(17, 240, 245);
		 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
		 JButton back=new JButton(backi);
		 back.setBounds(5,5,20,20);
		 back.addActionListener(new ActionListener() {@Override
		public void actionPerformed(ActionEvent e) {
			
			 areq.hide();
			 af.show();
	}});
		 
		 JLabel title=new JLabel("Approve Request");
		 title.setBounds(140,20,200,50);
		 title.setFont(f2);
		 		
		 ll = new DefaultListModel<>();  
		  list = new JList<Shop>();  
		  list.setModel(ll);
		  list.setCellRenderer(new MyListCellRenderer());
        list.setBounds(100,100, 300,300);  
        info=new JLabel();
		  info.setBounds(200, 450, 200, 20);
		loadList(ll);
		
		
		Color red=new Color(242, 51, 83);
		Color blue=new Color(51, 245, 213);
		
		JButton approve=new JButton("Approve");
		approve.setBounds(250,400,130,30);
		approve.setFont(f3);
		approve.setBackground(blue);
		approve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex();
				 Shop s =list.getSelectedValue();
				 if(s==null)
				 {
					 info.setText("NO REQUESTS PENDING..");
					 JOptionPane.showMessageDialog(f, "No requests pending..",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);
				 }else {
				 try {
				 PreparedStatement stmt1=con.prepareStatement("insert into shop(sid,passwd,shopName,place) values(?,?,?,?)");  
				    stmt1.setString(1,s.sid);//1 specifies the first parameter in the query  
				    stmt1.setString(2,s.shopName);
				    stmt1.setString(3,s.passwd);
				    stmt1.setString(4,s.place);
				    
				    int i=stmt1.executeUpdate(); 
					
					System.out.println(i+" records inserted..");
					System.out.println("\n\t\t\t\tRegistration Successful of shop\n\t\t\t\t..");
					
					PreparedStatement stmt2=con.prepareStatement("delete from requests where sid=?");  
				    stmt2.setString(1,s.sid);//1 specifies the first parameter in the query  
				    
				    
				     i=stmt2.executeUpdate(); 
					
					System.out.println(i+" records deleted..");
					System.out.println("\n\t\t\t\t\t\t\t !!!! APPROVED "+s.shopName+" !!!!");
					info.setText("!!!! APPROVED "+s.shopName+" !!!!");
					 JOptionPane.showMessageDialog(f, "!!!! APPROVED "+s.shopName+" !!!!",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);
					loadList(ll);
					
				 }catch(SQLException ee)
				 {
					 ee.printStackTrace();
					 JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                             "ERROR", JOptionPane.ERROR_MESSAGE); 
				 }
				 }
				
			}
		});
		System.out.println("\n\t\t\t\tapprove created---------------------------------------");
		JButton disapprove=new JButton("DisApprove");
		disapprove.setBounds(120,400,130,30);
		disapprove.setFont(f3);
		disapprove.setBackground(red);
		disapprove.setForeground(Color.white);
		disapprove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex();
				 Shop s =list.getSelectedValue();
				 if(s==null)
				 {
					 info.setText("NO REQUESTS PENDING..");
					 JOptionPane.showMessageDialog(f, "No requests pending..",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);
				 }else {
				 try {
				System.out.println("\n\t\t\t\t\t\t !!!! Disapproved : "+s.shopName+"   !!!!");
				
				PreparedStatement stmt2=con.prepareStatement("delete from requests where sid=?");  
			    stmt2.setString(1,s.sid);//1 specifies the first parameter in the query  
			    
			    
			    int i=stmt2.executeUpdate(); 
			    info.setText("!!!! DISAPPROVED "+s.shopName+" !!!!");
			    JOptionPane.showMessageDialog(f, "!!!! DISAPPROVED "+s.shopName+" !!!!",  
	                     "Message",  
	                     JOptionPane.INFORMATION_MESSAGE);
			    loadList(ll);
				System.out.println(i+" records deleted..");
				 }catch(SQLException ee)
				 {
					 ee.printStackTrace();
					 JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                             "ERROR", JOptionPane.ERROR_MESSAGE); 
				 }
				}
			}
		});
		
		
		
		//
		   areq.add(title);areq.add(list);areq.add(back);areq.add(approve);areq.add(disapprove);areq.add(info);
	          areq.setVisible(true);
	          areq.setLayout(null);
	          areq.setBounds(350, 0, 500, 550); 
				System.out.println("Created layoutssss..");
				f.getContentPane().add(areq);
				System.out.println("ADDDEDDDDD");
		
				
		
	}
	
	//display all registered users.
	void displayUsers()
	{
		System.out.println("\n\n\t\t\t\t\t\t-------------- U S E R S -------------------");
		
		//gui
		af.hide();
		dusers=new JPanel();
		Color c1 = new Color(235, 251, 255); 
		dusers.setBackground(c1);
		 Border blackline = BorderFactory.createLineBorder(Color.black);
		 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
		 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);

		 Color c2=new Color(17, 240, 245);
		 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
		 JButton back=new JButton(backi);
		 back.setBounds(5,5,20,20);
		 back.addActionListener(new ActionListener() {@Override
		public void actionPerformed(ActionEvent e) {
			
			 dusers.hide();
			 af.show();


		}});
		 JLabel title=new JLabel("CUSTOMER NAMES");
		 title.setBounds(140,20,200,50);
		 title.setFont(f2);
//		    JTextArea users=new JTextArea("");
//		    users.setBounds(50,100,350,270);  
//		    users.setFont(f3);
//		    users.setEditable(false);
		 
		 ll2 = new DefaultListModel<>();  
		  listu = new JList<User>();  
		  listu.setModel(ll2);
		  listu.setCellRenderer(new MyListCellRendererUser());
       listu.setBounds(100,100, 300,300);  
       info=new JLabel();
		  info.setBounds(200, 450, 200, 20);
		//loadList(ll2);
		  
		
	  //
		try {
			PreparedStatement stmt2=con.prepareStatement("select * from users");
			ResultSet rs=stmt2.executeQuery(); 
		//	users.setText("uid    user name     address");
			while(rs.next())
			{
				User u=new User(rs.getString("uid"),rs.getString("uname"),rs.getString("passwd"),rs.getString("address"));
				//users.append("\n"+rs.getString("uid")+"  "+" "+rs.getString("uname")+" "+" "+rs.getString("address"));
				ll2.addElement(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 dusers.add(title);dusers.add(listu);dusers.add(back);
		    dusers.setVisible(true);
		    dusers.setLayout(null);
		    dusers.setBounds(350, 0, 500, 550); 
			System.out.println("Created layoutssss..");
			f.getContentPane().add(dusers);
			System.out.println("ADDDEDDDDD");
		System.out.println("\n\t\t\t\t\t\t---------------------------------------");
		
	}
	
	//display all registered shops.
		void displayShops()
		{
			System.out.println("\n\n\t\t\t\t\t\t-------------- S H O P S ---------------------");
			af.hide();
			dshops=new JPanel();
			Color c1 = new Color(235, 251, 255); 
			dshops.setBackground(c1);
			 Border blackline = BorderFactory.createLineBorder(Color.black);
			 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
			 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
			 Color c2=new Color(17, 240, 245);
			 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
			 JButton back=new JButton(backi);
			 back.setBounds(5,5,20,20);
			 back.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				
				 dshops.hide();
				 af.show();


			}});
			 
			 JLabel title=new JLabel("SHOP NAMES");
			 title.setBounds(140,20,200,50);
			 title.setFont(f2);
//			    JTextArea users=new JTextArea("");
//			    users.setBounds(50,100,350,270);  
//			    users.setFont(f3);
//			    users.setEditable(false);
			 
			 ll = new DefaultListModel<>();  
			  lists = new JList<Shop>();  
			  lists.setModel(ll);
			  lists.setCellRenderer(new MyListCellRenderer());
	       lists.setBounds(100,100, 300,300);  
	       info=new JLabel();
			  info.setBounds(200, 450, 200, 20);
			loadList(ll);
			
			try {
				PreparedStatement stmt2=con.prepareStatement("select * from shop");
				ResultSet rs=stmt2.executeQuery(); 
				//users.setText("ShopID   ShopName   Address ");
				while(rs.next())
				{
					//users.append("\n"+rs.getString("sid")+" "+rs.getString("shopName")+" "+" "+rs.getString("place"));
					Shop s=new Shop(rs.getString("sid"),rs.getString("passwd"),rs.getString("shopName"),rs.getString("place"),f);
				System.out.println("\t\t\t\t\t  "+" "+rs.getString("shopName")+" "+" "+rs.getString("place"));
				ll.addElement(s);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 dshops.add(title);dshops.add(lists);dshops.add(back);
			 dshops.setVisible(true);
			 dshops.setLayout(null);
			 dshops.setBounds(350, 0, 500, 550); 
				System.out.println("Created layoutssss..");
				f.getContentPane().add(dshops);
				System.out.println("ADDDEDDDDD");
			System.out.println("\n\t\t\t\t\t\t---------------------------------------");
			}
	
		//login user
	User searchUser(String uid,String passwd)
	{
		
		
		//db
		try {
			//create statement object
		PreparedStatement stmt=con.prepareStatement("select uid,uname,passwd,address from users where uid=?");  
		stmt.setString(1,uid);//1 specifies the first parameter in the query  

		User u;
		ResultSet rs=stmt.executeQuery(); 
		if(rs.next())
		{
		 u=new User(rs.getString("uid"),rs.getString("uname"),rs.getString("passwd"),rs.getString("address"));
		 if(passwd.equals(u.passwd))
		        return u;
		    else 
		    	return null;
		
		}	
		else return null;
	     
		}catch(SQLException e)
		{
			System.out.println("SQLEXCEPTION");
			return null;
		}
		
//		for(int i=0;i<users.size();i++)
//		{
//		   if(users.get(i).uid.equals(uid)&& users.get(i).passwd.equals(passwd))
//		   {
//			   return users.get(i);
//		   }
//		}
		
	}
	
		
	
	//handler
	public void actionPerformed(ActionEvent e){  

		if(e.getSource()==b)//admin
		{
			choice= 1;
			System.out.println("1");
			f.dispose();
			loginSignup();
			
		}
		if(e.getSource()==b1)//shop
		{
			choice= 2;System.out.println("2");
			f.dispose();
			loginSignup();
		}
		if(e.getSource()==b2)//customer
		{
			choice=3;System.out.println("3");
			f.dispose();
			loginSignup();
		}
		if(e.getSource()==ba)//signin up
		{
			p1a.setVisible(true);
			p3.setVisible(false);
			l1.setText("LOGIN");
		
		}
		if(e.getSource()==b1a)//signup up
		{
			p1a.setVisible(false);
			p3.setVisible(true);
			l1.setText("REGISTER");
			
		}
		if(e.getSource()==b2a)//login button
		{
			if(choice==3) {
			String u=uname.getText();
			@SuppressWarnings("deprecation")
			String p=passwd.getText();
			//String pp=p.toString();
			System.out.println(u+" "+p);
			User uu=searchUser(u,p);
			if(uu==null)
			{
				//fail
				System.out.println("LOGIN FAILED");
				dmsg1.setText("LOGIN FAILED");
				uname.setText("");
				passwd.setText("");
				JOptionPane.showMessageDialog(f, "Login Failed..Try again",  
                        "ERROR", JOptionPane.ERROR_MESSAGE); 				
			}else
			{
				//proceed
				System.out.println("LOGIN Successful");
				dmsg1.setText("LOGIN SUCCESSFUL");
				uname.setText("");
				passwd.setText("");
				
				 JOptionPane.showMessageDialog(f, "Login successful..",  
	                     "Message",  
	                     JOptionPane.INFORMATION_MESSAGE);
				order(uu);
			}
			}
			else if(choice==2) {
				String u=uname.getText();
				@SuppressWarnings("deprecation")
				String p=passwd.getText();
				//String pp=p.toString();
				System.out.println(u+" "+p);
				Shop uu=searchShop(u,p);
				if(uu==null)
				{
					//fail
					System.out.println("LOGIN FAILED");
					dmsg1.setText("LOGIN FAILED");
					uname.setText("");
					passwd.setText("");
					JOptionPane.showMessageDialog(f, "Login Failed..Try again",  
	                        "ERROR", JOptionPane.ERROR_MESSAGE);
				}else
				{
					//proceed
					System.out.println("LOGIN Successful");
					dmsg1.setText("LOGIN SUCCESSFUL");
					JOptionPane.showMessageDialog(f, "Login successful..",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);
					uname.setText("");
					passwd.setText("");
					uu.shopFunctions(p2a);				
				}
				}
			else if(choice==1) {
				String u=uname.getText();
				@SuppressWarnings("deprecation")
				String p=passwd.getText();
				//String pp=p.toString();
				System.out.println(u+" "+p);
				boolean uu=login(u,p);
				if(uu==false)
				{
					//fail
					System.out.println("LOGIN FAILED");
					dmsg1.setText("LOGIN FAILED");
					JOptionPane.showMessageDialog(f, "Login Failed..Try again",  
	                        "ERROR", JOptionPane.ERROR_MESSAGE);
					uname.setText("");
					passwd.setText("");
				}else
				{
					//proceed
					System.out.println("LOGIN Successful");
					dmsg1.setText("LOGIN SUCCESSFUL");
					JOptionPane.showMessageDialog(f, "Login successful..",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);
					uname.setText("");
					passwd.setText("");
				
					adminFunctions();
				}
				}
			
			
		}
		if(e.getSource()==signup) //sign up
		{
			if(choice==2)
			{
				String u=uname1.getText();
				String p=passwd1.getText();
				String un=Username.getText();
				String pl=place.getText();
				
				registerShop(u,p,un,pl);
				
				uname1.setText("");
				passwd1.setText("");
				Username.setText("");
				place.setText("");
			
				
			}
			else if(choice==3)
			{
				String u=uname1.getText();
				@SuppressWarnings("deprecation")
				String p=passwd1.getText();
				String un=Username.getText();
				String pl=place.getText();
				
				try {
					registerUser(u,p,un,pl);
					
					uname1.setText("");
					passwd1.setText("");
					Username.setText("");
					place.setText("");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource()==rb1) //approve orders
		{
			approveRequest();
		}
		else if(e.getSource()==rb2) //shop 
		{
			displayShops();
		}
		else if(e.getSource()==rb3) //users display
		{
			displayUsers();
		}

}  
	
	//login shop
	Shop searchShop(String sid,String passwd1)
	{
		//String sid="";String passwd1="";
		try {
			//create statement object
		PreparedStatement stmt=con.prepareStatement("select sid,passwd,shopName,place from shop where sid=?");  
		stmt.setString(1,sid);//1 specifies the first parameter in the query  

		Shop u;
		ResultSet rs=stmt.executeQuery(); 
		if(rs.next())
		{
		 u=new Shop(rs.getString("sid"),rs.getString("passwd"),rs.getString("shopName"),rs.getString("place"),f);
		 
		    if(passwd1.equals(u.passwd))
		        return u;
		    else 
		    	return null;
		}	
		else return null;
	     
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("SQLEXCEPTION");
			return null;
		}
		

	}
	void loadShopList(DefaultListModel ll,String place)
	{
		        
		System.out.println("\n\t\t\t\t\t\tList created---------------------------------------");
		ll.removeAllElements();
		//display shop
		try {
			PreparedStatement stmt2=con.prepareStatement("select * from shop");
			ResultSet rs=stmt2.executeQuery(); 
			//users.setText("ShopID   ShopName   Address ");
			while(rs.next())
			{
				if(rs.getString("place").contains(place)) {
				//users.append("\n"+rs.getString("sid")+" "+rs.getString("shopName")+" "+" "+rs.getString("place"));
				Shop s=new Shop(rs.getString("sid"),rs.getString("passwd"),rs.getString("shopName"),rs.getString("place"),f);
			    System.out.println("\t\t\t\t\t  "+" "+rs.getString("shopName")+" "+" "+rs.getString("place"));
			    ll.addElement(s);
				}
			}
			
		} catch (SQLException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		
		//
		
	}
    
	
	//order
	boolean order(User u)
	{
		/////
		p2a.hide();
		
		shopo=new JPanel();
		 Color c1 = new Color(235, 251, 255); 
		 Color blue=new Color(51, 245, 213);
		 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
		 Font  sm  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
		 shopo.setBackground(c1);
		//JLabel place =new JLabel("Enter a Place:");
		 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
		 JButton back=new JButton(backi);
		 back.setBounds(5,5,20,20);
		 back.addActionListener(new ActionListener() {@Override
		public void actionPerformed(ActionEvent e) {
			
			 p2a.show();
			 shopo.hide();
		}});
		 
		 JLabel title=new JLabel("SHOP NAMES");
		 title.setBounds(140,20,200,50);
		 title.setFont(f2);
		 JLabel pl=new JLabel("Enter a Place:");
		 JTextField plt=new JTextField(20);
		  pl.setBounds(50, 90, 200, 30);
		    plt.setBounds(50, 130, 200, 30);
		    pl.setFont(sm);plt.setFont(sm);
		    
		    JButton add=new JButton("DISPLY");    
			add.setBounds(260,130,100,30);
			 JLabel info=new JLabel("hi");
			 info.setBounds(50,430,200,30);
			 add.setBackground(blue);
			 
			 DefaultListModel ll = new DefaultListModel<>();  
			 JList<Shop> lists = new JList<Shop>();  
			  lists.setModel(ll);
			  lists.setCellRenderer(new MyListCellRenderer());
	         lists.setBounds(50,200, 400,200);  
	         
	         JButton next=new JButton("NEXT");    
			 next.setBounds(130,430,130,30);
			 next.setBackground(blue);
	   
		///////
//		System.out.println("\n\n\n\t\t\t\t\t\t  ******************** ");
//		System.out.println("\n\t\t\t\t\t\t\t=========== Place an Order ==========");
//		System.out.print("\n\t\t\t\t\t\t\tEnter place:");
			 
			 add.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					String place=plt.getText().toLowerCase();
					loadShopList(ll,place);
				}
			});
			 
			 next.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 int index = lists.getSelectedIndex();
					 Shop s =lists.getSelectedValue();
					 boolean flag=false;
					
					 if(s==null)
					 {
						 info.setText("NO REQUESTS PENDING..");
						 JOptionPane.showMessageDialog(f, "No requests pending..",  
			                     "Message",  
			                     JOptionPane.INFORMATION_MESSAGE);


					 }else {
						
							
							try {
								PreparedStatement stmt2=con.prepareStatement("select * from shop where sid=?");
								stmt2.setString(1, s.sid);
								ResultSet rs=stmt2.executeQuery(); 
								if(rs.next())
								{
								s1=new Shop(rs.getString("sid"),rs.getString("passwd"),rs.getString("shopName"),rs.getString("place"),f);
								}else
								{
									System.out.println("\t\t\t\t\t\t Enter valid shop name");
									flag= false;
									info.setText("Select valid data");
									JOptionPane.showMessageDialog(f, "Select a valid data..!!!",  
			                                "ERROR", JOptionPane.ERROR_MESSAGE); 
								}
								
							} catch (SQLException ee) {
								// TODO Auto-generated catch block
								ee.printStackTrace();
								flag= false;
							}
					 }
					 
					 shopp=new JPanel();
					 Color c1 = new Color(235, 251, 255); 
					 Color blue=new Color(51, 245, 213);
					 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
					 shopp.setBackground(c1);
					 shopo.hide();
					 
					 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
					 JButton back=new JButton(backi);
					 back.setBounds(5,5,20,20);
					 back.addActionListener(new ActionListener() {@Override
					public void actionPerformed(ActionEvent e) {
						
						 p2a.hide();
						 shopo.show();
					}});
					 
					 
					 /////
					 Color c2=new Color(17, 240, 245);
					 Color c3=new Color(15, 60, 61);
					 orderp = new JPanel(); 
					 chatp = new JPanel(); 
					 
					JButton orderb = new JButton("Order"); 		
				     JButton chatb = new JButton("Chat"); 
				  
				     orderp.setBackground(c1);chatp.setBackground(c1);
				     orderp.setLayout(null); chatp.setLayout(null);
				     orderp.setVisible(true);
				     orderb.setBounds(270,60,100,30);
				     chatb.setBounds(350,60,100,30);
				     
				     orderb.setBackground(c2);
				     chatb.setBackground(c3);
				     
				     orderb.setForeground(c3);
				     chatb.setForeground(c2);
				   
				     orderp.setBounds(0, 110, 500, 550);
				     chatp.setBounds(0, 110, 500, 550);
				     
					
					 
					 orderb.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							chatp.hide();
							orderp.show();
							System.out.println("In order "+s1.sid);
							//order
							orderp.add(new JLabel("Helooooooooooo456"));
							Bill b=new Bill();b=s1.order(u,orderp);
														
							//
							
							
						}
					});
					 chatb.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								chatp.show();
								orderp.hide();
								System.out.println("In chat");
								//chat
							
								try {
									u.chat(chatp);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						});
					 /////
					 
					 shopp.add(back);shopp.add(orderb);shopp.add(chatp);shopp.add(chatb);shopp.add(orderp);
					 shopp.setVisible(true);
					 shopp.setLayout(null);
					 shopp.setBounds(350, 0, 500, 550); 
					
						System.out.println("Created layoutssss..");
						f.getContentPane().add(shopp);
						System.out.println("ADDDEDDDDD");
					
				}
			});
		
	
		System.out.println("\n\t\t\t\t\t-------------------------------------------------------");
		
		//display shops;
		
		System.out.println("\t\t\t\t\t-------------------------------------------------------");
		System.out.print("\n\t\t\t\t\t\tEnter Any one Shop name from above list:");
	
		
		//Shop s=shops.get(id-1);
		
		
//		int ch;
//		ch=sc.nextInt();
//		
//		switch(ch)
//		{
//		case 1://order
//			
//			Bill b=new Bill();
//			b=s.order(u);
//		    String str=b.returnString();
//			
//		//	s.q.add("\n\t\t\t\t\t"+u.name+" "+u.address+" "+str+"\tTotal:"+b.totalBill);
//			//s.uu.add(u);
//			
//			System.out.println("\n\t\t\t\t\t\t\tCustomer Name:-    "+u.name);
//			System.out.println("\t\t\t\t\t\t\tCustomer Address:- "+u.address);
//			System.out.println("\t\t\t\t\t\t\tShop name:-        "+s.shopName);
//			System.out.println("\t\t\t\t\t\t\tTotal bill:-       "+b.totalBill+" Rs.");
//			break;
//		case 2:
//			try {
//				u.chat();
//			}catch(Exception e)
//			{
//				System.out.println("\t\t\t\t\t\t\tException..");
//			}
//			
//		}
		 shopo.add(title);shopo.add(lists);shopo.add(back);shopo.add(next);shopo.add(pl);shopo.add(plt);shopo.add(add);
		 shopo.setVisible(true);
		 shopo.setLayout(null);
		 shopo.setBounds(350, 0, 500, 550); 
			System.out.println("Created layoutssss..");
			f.getContentPane().add(shopo);
			System.out.println("ADDDEDDDDD");
		return true;
	}
	
	
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException
	{

		
		
		Admin a=new Admin(); //aDmin
	
		
		
		
		System.out.println("\n\n\n\t\t\t\t_____________________________________________________________\n");
		System.out.println("\t\t\t\t\tWELCOME TO GROCERY SUPPLY DURING COVID-19 CRISIS");
		System.out.println("\t\t\t\t_____________________________________________________________\n");
		
		  try {
			  //
			  System.out.println("Creating frame..");
	        a.createFrame();	
	      //   System.out.println("Created.."+choice);
				 
//			 switch(choice)
//				{
//				case 1: //customer
//					System.out.println("Customer..");
//					a.loginSignup();
//						
//						
//				break;
//				case 2://Shop manager
//					 opt2:do {
//							System.out.println("\n\n\n\n\t\t\t\t\t\t **************************\n\n");	
//							System.out.println("\n\t\t\t\t\t========== S H O P  M E N U ==========\n");
//						 System.out.println("\n\t\t\t\t\t1.ALready have an account..Login..\n\t\t\t\t\t2.Register new Account\n\t\t\t\t\t3.Back to main menu."); 
//						 System.out.println("\n\t\t\t\t---------------------------------------------------");	
//							System.out.print("\t\t\t\t\tEnter Your Choice :  ");
//						 	 int ch=1;
//						 
//						  System.out.println("\n\t\t\t\t---------------------------------------------------");	
//					 switch(ch)
//						 {
//						 case 1:
//							 System.out.println("\n\n\n\n\t\t\t\t\t\t!--!--!--!--!--!--!--!--!--! LOGIN !--!--!--!--!--!--!--!--!--!");
//							 Shop s=a.searchShop();
//							 if(s!=null)
//								{
//								System.out.println("  \n\t\t\t\t<<< Login Successful >>>");
//								opt4:do {
//								System.out.println("\n\n\n\n\t\t\t\t\t\t*********************");
//								System.out.println("\n\t\t\t\t\t\t========= M E N U =========");
//								System.out.println("\n\t\t\t\t\t\t1.Add Items.\n\t\t\t\t\t\t2.Display price chart.\n\t\t\t\t\t\t3.Approve Orders."
//										+ "\n\t\t\t\t\t\t4.Update Prices.\n\t\t\t\t\t\t5.Chat with customer\n\t\t\t\t\t\t6.Back");
//								 System.out.println("\n\t\t\t\t---------------------------------------------------");	
//									System.out.print("\t\t\t\t\tEnter Your Choice :  ");
//									
//									int chh=1;
//										System.out.println("\n\t\t\t\t---------------------------------------------------");	
//								switch(chh)
//								{
//								case 1:
//									s.addItems();
//									break;
//								case 2:
//									s.displayPricechart();
//									break;
//								case 3:
//									s.approveOrder();
//									break;
//								case 4:
//									s.updatePriceChart();
//									break;
//								case 5:
//									
//										 s.chatShop();
//									
//								case 6:
//									break opt4;
//								}
//								}while(true);
//								}else
//								{
//									System.out.println("\n\t\t\t\t\t\t <<<<<Login failed >>>>>");
//								}
//							 break;
//						 case 2://register new user
//							 System.out.println("\n\n\t\t\t\t\t\t===============");
//							 System.out.println("\t\t\t\t\t\t    REGISTER");
//							 System.out.println("\t\t\t\t\t\t===============");
//								 a.registerShop();
//							 break;
//						 case 3://Back	 
//							   break opt2;
//						 }
//						 
//					   }while(true);
//				     break;
//				case 3://Admin
//					if(a.login()==true)
//					{
//						opt3: do
//					      {
//							System.out.println("\n\n\n\n\n\t\t\t\t\t*********************");
//							System.out.println("\n\t\t\t\t\t========= M E N U =========");
//						 System.out.println("\n\t\t\t\t\t1.Approve Request\n\t\t\t\t\t2.Display all Shops.\n\t\t\t\t\t3.Display all users.\n\t\t\t\t\t4.Back to main menu.\n"); 
//							
//							System.out.println("\t\t\t\t---------------------------------------------------");	
//								 
//							System.out.print("\t\t\t\t\tEnter Your Choice :  ");
//					
//						 int ch=1;
//							
//							System.out.println("\n\t\t\t\t---------------------------------------------------");	
//									
//							 switch(ch)
//							 {
//							 case 1://approve requests
//								 a.approveRequest();
//								 break;
//							 case 2://display all shops
//								 a.displayShops();
//								 break;
//							 case 3:
//								 a.displayUsers();
//								 break;
//							 case 4://Back	 
//								   break opt3;
//							 }		
//							 
//					      }while(true);
//					}
//					else {
//						System.out.println("\n\n\t\t\t\t\t\t<<<<<<< Login falied >>>>>>>");
//					}		     
//					break;
//				  }			 
//		
//			
//		  }
		  }catch(Exception e)
		  {
			  System.out.println("\n\n\t\t\t\t\t\tEnter valid data..Logging Out\n");
		  }
			
	}
		
}
 class MyListCellRenderer extends DefaultListCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public JComponent getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Shop label = (Shop) value;
        String name = label.getName();
        String sid = label.getID();
        String address = label.getAddr();
        String labelText = "<html>SID: " + sid + "<br/>Name: " + name +"<br/>Place: " + address;
        setText(labelText);

        return this;
    }

}
 class MyListCellRendererUser extends DefaultListCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	    public JComponent getListCellRendererComponent(
	            JList list, Object value, int index,
	            boolean isSelected, boolean cellHasFocus) {
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        User label = (User) value;
	        String name = label.getName();
	        String sid = label.getID();
	        String address = label.getAddr();
	        String labelText = "<html>SID: " + sid + "<br/>Name: " + name +"<br/>Place: " + address;
	        setText(labelText);

	        return this;
	    }

	}
 