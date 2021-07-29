import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class Shop implements ActionListener
{
	String sid,passwd,shopName,place;
	//HashMap<String,Integer> priceChart=new HashMap<String,Integer>();
	//Queue<String> q=new LinkedList<String>();
	//Queue<User> uu=new LinkedList<User>();
	//ArrayList<User> ulist=new ArrayList<User>();
	Connection con;
	Statement st;
	ResultSet rs;
	ArrayList<ClientHandler> clients=new ArrayList<ClientHandler>();
	JFrame f;
	JPanel sf,addi,updatei,app;
	JButton rb1,rb2,rb3,rb4,rb5,add,update;

	DefaultTableModel model,model1;
	JTable jtbl,jtbl1;
	DefaultListModel ll;
	JList<Shop> list;
	JLabel info;
	 float totalsum;
	 JTextField qt,namet;
	 
	Shop()
	{
		sid=passwd=shopName=place=null;
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
	Shop(String s1,String s2,String s3,String s4,JFrame f)
	{
		sid=s1;
		passwd=s2;
		shopName=s3;
		place=s4;
		this.f=f;
		
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
	void shopFunctions(JPanel p2a)
	{
		//add items, display , approve oder , update price chart, chat.
		
		p2a.hide();
		   
		sf=new JPanel(); 
		 Color c1 = new Color(235, 251, 255); 
		 sf.setBackground(c1);
		
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
			
			 sf.hide();
			 p2a.show();


		}});
		 
		
		 
		 JLabel title=new JLabel("SHOP FUNCTIONS.");
		 title.setBounds(100,50,300,50);
		 title.setFont(f2);
		System.out.println("Created panel..");
		rb1=new JButton("Add New");    
		rb1.setBounds(70,400,100,30);      
		rb2=new JButton("Remove");    
		rb2.setBounds(190,400,100,30);
		rb3=new JButton("Update");    
		rb3.setBounds(310,400,100,30); 
		rb4=new JButton("Approve Order");    
		rb4.setBounds(100,450,150,30); 
		rb5=new JButton("Chat");    
		
		rb5.setBounds(260,450,100,30); 
		
		
		 displayPricechart(sf);
		
		rb1.addActionListener(this);//rb2.addActionListener(this);
		rb3.addActionListener(this);rb4.addActionListener(this);
	   
		rb1.setBorder(blackline);
		rb1.setFont(f3);
		rb2.setBorder(blackline);
		rb2.setFont(f3);
		rb3.setBorder(blackline);
		rb3.setFont(f3);
		rb4.setBorder(blackline);
		rb4.setFont(f3);
		rb5.setBorder(blackline);
		rb5.setFont(f3);
	
		
		rb1.setBackground(c2);rb2.setBackground(c2);rb3.setBackground(c2);rb4.setBackground(c2);
		rb5.setBackground(c2);
		System.out.println("Created button..");
		
		rb5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					chatShop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//		af.add(title);
//		af.add(rb1);af.add(rb2);af.add(rb3);af.add(back);
//		p2a.setVisible(false);
//		af.setVisible(true);
//		af.setLayout(null);
//		af.setBounds(350, 0, 500, 550); 
//		System.out.println("Created layoutssss..");
//		f.getContentPane().add(af);
		System.out.println("ADDDEDDDDD");
		sf.add(title);sf.add(back);sf.add(rb1);sf.add(rb2);sf.add(rb3);sf.add(rb4);sf.add(rb5);
		 sf.setVisible(true);
		 sf.setLayout(null);
		 sf.setBounds(350, 0, 500, 550); 
		f.add(sf);
		
	}
	//Register
	void register()
	{
		 Scanner sc=new Scanner(System.in);
		System.out.print("\n\t\t\t\t\t\tEnter shop id : "); sid=sc.nextLine();
		System.out.print("\n\t\t\t\t\t\tEnter Shop name : "); shopName=sc.nextLine();
		System.out.print("\n\t\t\t\t\t\tEnter password : "); passwd=sc.nextLine();
		System.out.print("\n\t\t\t\t\t\tEnter Address : "); place=sc.nextLine().toLowerCase();
		 System.out.println("\n\t\t\t\t\t-------------------------------------------------------");
			
	}
	//diaplay categories
	void dispCat()
	{
System.out.println("\n\n\t\t\t\t\tCATEGORIES:\n\n");
		
		try {
			PreparedStatement stmt2=con.prepareStatement("select * from category");
			
			ResultSet rs=stmt2.executeQuery(); 
			int i=1;
			while(rs.next())
			{
			System.out.println("\t\t\t\t\t\t"+rs.getString("cid")+"   "+rs.getString("cname"));
			i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//add items of their particular shops.
	void addItems()
	{
		
		///////
		addi=new JPanel(); 
		 Color c1 = new Color(235, 251, 255); 
		 addi.setBackground(c1);
		 
		 sf.hide();
		
		//border fonts
		 Border blackline = BorderFactory.createLineBorder(Color.black);
		 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 26);
		 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
		 
		 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
		 JButton back=new JButton(backi);
		 back.setBounds(5,5,20,20);
		 back.addActionListener(new ActionListener() {@Override
		public void actionPerformed(ActionEvent e) {
			
			 addi.hide();
			 sf.show();


		}});
		 JLabel name,price;
		 JTextField namet,pricet;
		    name=new JLabel("Item name:");
		    namet=new JTextField(20);
		    price=new JLabel("Price:");
		    pricet=new JTextField(20);
		    
		    name.setBounds(50, 50, 200, 30);
		    namet.setBounds(50, 80, 200, 30);
		    price.setBounds(50, 110, 200, 30);
		    pricet.setBounds(50, 140, 200, 30);
		    
		    
		    JLabel categories=new JLabel("Select Category:");
		    categories.setBounds(50,200,200,30);
		    JRadioButton rb1,rb2,rb3; 
		    rb1=new JRadioButton("Food Grains");    
			rb1.setBounds(80,230,200,30);      
			rb2=new JRadioButton("Oil and spices");    
			rb2.setBounds(80,260,200,30);
			rb3=new JRadioButton("Beverages");    
			rb3.setBounds(80,290,200,30); 
			
			ButtonGroup bg=new ButtonGroup();    
			bg.add(rb1);bg.add(rb2); bg.add(rb3);
			add=new JButton("ADD ITEM");    
			add.setBounds(130,330,100,30);
			 JLabel info=new JLabel("");
			    info.setBounds(50,430,200,30);
			    
			    addi.add(back);addi.add(info);addi.add(name);addi.add(namet);addi.add(price);addi.add(pricet);
			    addi.add(categories);addi.add(rb1);addi.add(rb2);addi.add(rb3);addi.add(add);
		 
		 addi.setVisible(true);
		 addi.setLayout(null);
		 addi.setBounds(350, 0, 500, 550); 
		f.add(addi);
		 
		 ///////
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String iname;
				float iprice;
				int cid=0;
				if(rb1.isSelected()) cid=1;
				if(rb2.isSelected()) cid=2;
				if(rb3.isSelected()) cid=3;
				try {
				// TODO Auto-generated method stub
				iname=namet.getText();
				iprice=Float.parseFloat(pricet.getText());
				
				//else cid=0;
				
				
				
					PreparedStatement stmt=con.prepareStatement("insert into pricechart(pname,price,sid,category) values(?,?,?,?)");  
				    stmt.setString(1,iname);//1 specifies the first parameter in the query  
				    stmt.setFloat(2,iprice);
				    stmt.setString(3,sid);
				    stmt.setInt(4, cid);
				 
					int i=stmt.executeUpdate();
					
					System.out.println("\n\t\t\t\t\t\t"+i+" item added..");
					info.setText("Item added..");
					reloadTable(model);
				} catch (SQLException ee) {
					// TODO Auto-generated catch block
					System.out.println("\n\t\t\t\t\t\tCan't add item");
					info.setText("Something went wrong.");
				} 	
				catch(NumberFormatException | NullPointerException ee)
				{
					info.setText("Enter valid data..");
				}
				
				
			}
		});

	}
	//reload user orders
	
	//Display chart
	
	void reloadTable(DefaultTableModel model)
	{
  		model = (DefaultTableModel) jtbl.getModel();
		model.setRowCount(0);
		System.out.println("\n\n");
		
		try {
			PreparedStatement stmt1=con.prepareStatement("select * from category");
			ResultSet rs2=stmt1.executeQuery(); 
			int i=1;
			int cat;
			String cname;
			while(rs2.next())
			{
				cat=rs2.getInt("cid");
				cname=rs2.getString("cname");
				System.out.println("\n\t\t\t\t\tCategory:"+cname);
			PreparedStatement stmt2=con.prepareStatement("select * from pricechart where sid=? and category=?");
			stmt2.setString(1, sid);
			stmt2.setInt(2,cat);
			ResultSet rs=stmt2.executeQuery(); 
			 i=1;
			while(rs.next())
			{
			System.out.println("\t\t\t\t\t\t"+i+" "+rs.getString("pname")+"  "+" "+rs.getString("price"));
			   model.addRow(new Object[]{rs.getString("pname"),rs.getInt("price"),cname});
			i++;
			}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void displayPricechart(JPanel sf)
	{
		
		  model = new DefaultTableModel();
	  	     jtbl = new JTable(model);
	
		  model.addColumn("Item name");
	        model.addColumn("Price");
	        model.addColumn("Category");
	        jtbl.setBounds(100,100,300,250);
	    
	        Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 16);
	        jtbl.setFont(f2);
	        jtbl.setRowHeight(jtbl.getRowHeight() + 16);
	    	reloadTable(model);
		
		jtbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Color red=new Color(242,104,127);
				
				rb2.setBackground(red);
							
			}
		});
		

		rb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row=jtbl.getSelectedRow();
				String value=jtbl.getModel().getValueAt(row, 0).toString();
				
				
				try
				{
					PreparedStatement stmt1=con.prepareStatement("delete from pricechart where pname=? and sid=?");
					stmt1.setString(1, value);stmt1.setString(2, sid);
					int i=stmt1.executeUpdate();
					System.out.println(i+"Record deleted..");
					reloadTable(model);
//					displayPricechart();
				}catch(SQLException ee)
				{
					ee.printStackTrace();
				}
				
			}
		});
		
		JScrollPane pg = new JScrollPane(jtbl);
		sf.add(jtbl);
        sf.add(pg);
	}
	
	void displayPricechartCust(JPanel orderp)
	{
		
		  model = new DefaultTableModel();
	  	    jtbl = new JTable(model);
	
		  model.addColumn("Item name");
	        model.addColumn("Price");
	        model.addColumn("Category");
	        jtbl.setBounds(10,10,350,200);
	    
	        
	        Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 16);
	        jtbl.setFont(f2);
	        jtbl.setRowHeight(jtbl.getRowHeight() + 16);
	    	reloadTable(model);
		
		jtbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Color red=new Color(242,104,127);
				int row=jtbl.getSelectedRow();
				String value=jtbl.getModel().getValueAt(row, 0).toString();
				
				System.out.println("Value changed..");
				namet.setText(value);qt.setText("1");
			}
		});
		
		

		JScrollPane pg = new JScrollPane(jtbl);
		orderp.add(jtbl);
		orderp.add(pg);
        System.out.println("table added..");
	}
	
	//Shop order approve
	Bill order(User u,JPanel orderp)
	{
		
		Bill b=new Bill();
		System.out.println("\n\n");
		 Color c2=new Color(17, 240, 245);
		displayPricechartCust(orderp);
		Font  sm  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
		
		JLabel namel=new JLabel("Item Name:"); namet=new JTextField();
		namel.setBounds(10,230,100,30);namet.setBounds(90,230,100,30);
		JLabel ql=new JLabel("Quantity:"); qt=new JTextField();
		ql.setBounds(200,230,100,30);qt.setBounds(280,230,100,30);
		JButton addb=new JButton("ADD");addb.setBounds(390,230,70,30);
		addb.setBackground(c2);
		JButton rmb=new JButton("REMOVE");rmb.setBounds(340,270,120,30);
		Color red=new Color(242, 51, 83);rmb.setBackground(red);rmb.setVisible(false);
		JLabel totall=new JLabel("Total:");JTextField totalt=new JTextField();
		totall.setBounds(320,310,100,30);totalt.setBounds(370,310,70,30);
		totalt.setEditable(false);
		
		JButton confirm=new JButton("CONFIRM");confirm.setBounds(340,350,120,30);
		confirm.setBackground(c2);
		
		namel.setFont(sm);namet.setFont(sm);ql.setFont(sm);qt.setFont(sm);
		totall.setFont(sm);totalt.setFont(sm);
        

		DefaultTableModel model = new DefaultTableModel();
		JTable jtbl = new JTable(model);
	
		  model.addColumn("Item name");
	        model.addColumn("Price");
	        jtbl.setBounds(10,270,300,100);
	    
	        Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 16);
	        jtbl.setFont(f2);
	        jtbl.setRowHeight(jtbl.getRowHeight() + 16);
	      
	        jtbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					Color red=new Color(242,104,127);
					
					rmb.setBackground(red);
					rmb.setVisible(true);
					rmb.setForeground(c2);
								
				}
			});
		
	        rmb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int row=jtbl.getSelectedRow();
					String value=jtbl.getModel().getValueAt(row, 0).toString();
					float value2=Float.parseFloat(jtbl.getModel().getValueAt(row, 1).toString());
					float t=Float.parseFloat(totalt.getText());
					
					try
					{
						PreparedStatement stmt=con.prepareStatement("select * from PriceChart where sid=? and pname=?");
						stmt.setString(1, sid);stmt.setString(2, value);
						ResultSet rs=stmt.executeQuery();
						if(rs.next()) {
						String pid=	rs.getString("pid");
						float price=rs.getFloat("price");
						
						PreparedStatement stmt1=con.prepareStatement("delete from Bill where pid=?");
						stmt1.setString(1, pid);
						int i=stmt1.executeUpdate();
						System.out.println(i+"Record deleted..");
						
						totalt.setText(String.valueOf(t-(value2*price)));
						
						JOptionPane.showMessageDialog(f, "Removed item.!",  
                                "MESSAGE",  
                                JOptionPane.INFORMATION_MESSAGE); 
						 model.removeRow(jtbl.getSelectedRow());
						}else {
							System.out.println("Invalid deletion");
							  JOptionPane.showMessageDialog(f, "Invalid Deletion",  
	                                   "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
				
					}catch(SQLException ee)
					{
						ee.printStackTrace();
						
						  JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                                 "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
					
					
				}
			});
		orderp.add(namel);orderp.add(namet);orderp.add(ql);orderp.add(qt);orderp.add(addb);
		orderp.add(rmb);orderp.add(totalt);orderp.add(totall);orderp.add(confirm);
		
		JScrollPane pg = new JScrollPane(jtbl);
		orderp.add(jtbl);
		orderp.add(pg);
		totalsum=0;
	
		addb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String c,item;int qty;float price;int pid;
					System.out.print("\n\t\t\t\t\t\tEnter name of item :");item=namet.getText().toLowerCase();
					
					try {
						PreparedStatement stmt2=con.prepareStatement("select * from pricechart where sid=? and pname=?");
						stmt2.setString(1, sid);stmt2.setString(2, item);
						ResultSet rs=stmt2.executeQuery(); 
						if(rs.next())
						{
							System.out.print("\t\t\t\t\t\tEnter quantity : ");qty=Integer.parseInt(qt.getText());
							price=rs.getFloat("price");
							totalsum=totalsum+(price*qty);
							 pid=rs.getInt("pid");
							
							PreparedStatement stmt1=con.prepareStatement("insert ignore into bill(pid,sid,uid) values(?,?,?)");
							stmt1.setInt(1, pid);stmt1.setString(2, sid);stmt1.setString(3,u.uid);
							int i=stmt1.executeUpdate();
							
						//	System.out.println(i+" record inserted");
							 model.addRow(new Object[]{item,qty});
							System.err.println("\t\t\t\t\t\t-------------------");
							System.out.println("\t\t\t\t\t\tTotal bill : "+totalsum);
							totalt.setText(String.valueOf(totalsum));
																
							
						}else
						{
							System.out.println("\n\t\t\t\t\t\t<<<Enter valid item..<<<<");
							c="Y";
							 JOptionPane.showMessageDialog(f, "Enter valid item..",  
	                                 "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
						
					} catch (SQLException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
						c="Y";
						JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                                "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
         	}
		});
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String msg;
				try {
					//create statement object
					
				PreparedStatement stmt=con.prepareStatement("insert into orders values(?,?,?)");  
				stmt.setString(1,sid);//1 specifies the first parameter in the query  
				stmt.setString(2,u.uid);
				stmt.setFloat(3, totalsum);
				int i=stmt.executeUpdate();
				System.out.println(i+" Rows inserted");
					
				 msg="You have Successfully placed an order\nShop Name: "+shopName+"\nItem Names:";
				 
				 PreparedStatement stmt2=con.prepareStatement("update Orders set totalpurchase=? where sid=? and uid=?");
					stmt2.setString(1, totalt.getText());
					stmt2.setString(2, sid);
					stmt2.setString(3, u.uid);
					 i=stmt2.executeUpdate();
					System.out.println(i+"Record updated..Total purchase");
					
				
			     
				}catch(SQLException ee)
				{
					ee.printStackTrace();
					msg="";
					  JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                              "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
				//display items
				try {
					int pid;
					PreparedStatement stmt2=con.prepareStatement("select * from bill where sid=? and uid=?");
					stmt2.setString(1, sid);
					stmt2.setString(2, u.uid);
					ResultSet rs=stmt2.executeQuery(); 
					System.out.println("Item names:");
					while(rs.next())
					{
					pid=rs.getInt("pid");
					
					PreparedStatement stmt3=con.prepareStatement("select * from pricechart where pid=?");
					stmt3.setInt(1, pid);
					ResultSet rs2=stmt3.executeQuery(); 
					
					while(rs2.next())
					{
						System.out.println(rs2.getString("pname"));
						msg=msg+"\n    "+rs2.getString("pname");
						b.bill.put(rs2.getString("pname"), rs2.getFloat("price"));
					}
					}
					
				} catch (SQLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
					  JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                              "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
				b.totalBill=totalsum;
				 JOptionPane.showMessageDialog(f, msg,  
	                        "ORDER SHOP",  
	                        JOptionPane.INFORMATION_MESSAGE);
				 
				
			}
		});
	
		return b;
		
	}

	void reloadOrders(DefaultTableModel model1)
	{
		model1 = (DefaultTableModel) jtbl1.getModel();
		model1.setRowCount(0);
		System.out.println("\n\n");
		
		//db
		try {
			//create statement object
		PreparedStatement stmt=con.prepareStatement("select sid,uid,totalpurchase from orders where sid=?");  
		stmt.setString(1, sid);
     	ResultSet rs=stmt.executeQuery();
     	System.out.println("HHHHHHHHHHHHH");
     	String s="";
		while(rs.next())
		{
			String uid=(rs.getString("uid"));
			String sid=(rs.getString("sid"));
			float totalsum=rs.getFloat("totalpurchase");
			
			System.out.println("User id:"+uid);String str="User id:"+uid;
			//display items
			try {
				PreparedStatement stmt2=con.prepareStatement("select * from bill where sid=? and uid=?");
				stmt2.setString(1, sid);stmt2.setString(2, uid);
				ResultSet rs3=stmt2.executeQuery(); 
				System.out.println("Item names:");str=str+"Item names:\n";
				while(rs3.next())
				{
				int pid=rs3.getInt("pid");
				
				PreparedStatement stmt3=con.prepareStatement("select * from pricechart where pid=?");
				stmt3.setInt(1, pid);
				ResultSet rs2=stmt3.executeQuery(); 
				
				
				
				while(rs2.next())
				{							
					System.out.println(rs2.getString("pname")+" "+ rs2.getFloat("price"));
					s=s+"\n"+rs2.getString("pname")+" "+ rs2.getFloat("price");
					
				}
				
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                          "ERROR", JOptionPane.ERROR_MESSAGE); 
			}
			model1.addRow(new Object[]{uid,s,totalsum});
			//				
		}	     
		}catch(SQLException e)
		{
			System.out.println("SQLEXCEPTION");
			  JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                      "ERROR", JOptionPane.ERROR_MESSAGE); 
		
		}	
			
		//   
	}
	void approveOrder()
	{
		/////
		
		//gui
				sf.hide();
				app=new JPanel();
				Color c1 = new Color(235, 251, 255); 
				app.setBackground(c1);
				
				 Border blackline = BorderFactory.createLineBorder(Color.black);
				 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
				 Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);

				 Color c2=new Color(17, 240, 245);
				 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
				 JButton back=new JButton(backi);
				 back.setBounds(5,5,20,20);
				 back.addActionListener(new ActionListener() {@Override
				public void actionPerformed(ActionEvent e) {
					
					 app.hide();
					 sf.show();
			}});
				 
				 JLabel title=new JLabel("Approve Orders");
				 title.setBounds(140,20,200,50);
				 title.setFont(f1);
				 
				 JLabel info=new JLabel("");
				    info.setBounds(50,430,200,30);
				 		
				 model1 = new DefaultTableModel();
		  	     jtbl1 = new JTable(model1);
		
			   model1.addColumn("UID");
		       model1.addColumn("Items");
		       model1.addColumn("Total");
		        jtbl1.setBounds(100,100,300,250);
		    
		        Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 16);
		        jtbl1.setFont(f2);
		        jtbl1.setRowHeight(jtbl1.getRowHeight() + 16);
		        
		        System.out.println("calling fun");
		    	reloadOrders(model1);
				
				
				Color red=new Color(242, 51, 83);
				Color blue=new Color(51, 245, 213);
				
				JButton approve=new JButton("Approve");
				approve.setBounds(100,400,130,30);
				approve.setFont(f3);
				approve.setBackground(blue);
				

				JButton disapprove=new JButton("Disapprove");
				disapprove.setBounds(250,400,130,30);
				disapprove.setFont(f3);
				disapprove.setBackground(red);
				
				
				app.add(title);app.add(back);app.add(jtbl1);app.add(approve);app.add(disapprove);
				app.setVisible(true);
				app.setLayout(null);
				app.setBounds(350, 0, 500, 550); 
				f.add(app);
		
		/////
		
		System.out.println("\n\n\t\t\t\t\t========= Requests Pending ==========");
		System.out.println("\t\t\t\t\t--------------------------------------");
        Shop s;
		
		approve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row=jtbl1.getSelectedRow();
				String uid=jtbl1.getModel().getValueAt(row, 0).toString();
				Float totalsum=Float.parseFloat(jtbl1.getModel().getValueAt(row, 2).toString());
				try {
					
					PreparedStatement stmt1=con.prepareStatement("delete from orders where sid=? and uid=?");  
				    stmt1.setString(1,sid);//1 specifies the first parameter in the query  
				    stmt1.setString(2,uid);
				   							    
				    int i=stmt1.executeUpdate(); 
					
					System.out.println(i+" records deleted..");
				
					
					PreparedStatement stmt2=con.prepareStatement("insert into approvedorders values(?,?,?)");  
				    stmt2.setString(1,sid);//1 specifies the first parameter in the query  
				    stmt2.setString(2, uid);
				    stmt2.setFloat(3, totalsum);
				    
				     i=stmt2.executeUpdate(); 
					
					System.out.println(i+" records inserted..");
					System.out.println("\n\t\t\t\t\t\t\t !!!! APPROVED!!!!");
					info.setText("APPROVED !!!");
					 JOptionPane.showMessageDialog(f, "APPROVED !!!",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);

					reloadOrders(model1);
					
					} catch (SQLException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
						JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                                "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
							
				
				
			}
		});
		
		disapprove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row=jtbl1.getSelectedRow();
				String uid=jtbl1.getModel().getValueAt(row, 0).toString();
				
				try {
					
					PreparedStatement stmt1=con.prepareStatement("delete from orders where sid=? and uid=?");  
				    stmt1.setString(1,sid);//1 specifies the first parameter in the query  
				    stmt1.setString(2,uid);
				   							    
				    int i=stmt1.executeUpdate(); 
					
					System.out.println(i+" records deleted..");
					info.setText("DISAPPROVED!!!");
					 JOptionPane.showMessageDialog(f, "DISAPPROVED!!!",  
		                     "Message",  
		                     JOptionPane.INFORMATION_MESSAGE);
					reloadOrders(model1);
					//System.out.println("\n\t\t\t\tRegistration Successful of shop\n\t\t\t\t..");
				}catch (SQLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
					JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                            "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
					 				
		
	}
	
	//update priceChart
	void updatePriceChart()
	{
	///////
			updatei=new JPanel(); 
			 Color c1 = new Color(235, 251, 255); 
			 updatei.setBackground(c1);
			 
			 sf.hide();
			
			//border fonts
			 Border blackline = BorderFactory.createLineBorder(Color.black);
			 Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 26);
			 Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
			 
			 ImageIcon backi=new ImageIcon("src\\icons\\back.png");
			 JButton back=new JButton(backi);
			 back.setBounds(5,5,20,20);
			 back.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				
				 updatei.hide();
				 sf.show();


			}});
			 JLabel name,price;
			 JTextField namet,pricet;
			    name=new JLabel("Item name:");
			    namet=new JTextField(20);
			    price=new JLabel("Price:");
			    pricet=new JTextField(20);
			    
			    name.setBounds(50, 50, 200, 30);
			    namet.setBounds(50, 80, 200, 30);
			    price.setBounds(50, 110, 200, 30);
			    pricet.setBounds(50, 140, 200, 30);
			    
			    
			    JLabel categories=new JLabel("Select Category:");
			    categories.setBounds(50,200,200,30);
			    JRadioButton rb1,rb2,rb3; 
			    rb1=new JRadioButton("Food Grains");    
				rb1.setBounds(80,230,200,30);      
				rb2=new JRadioButton("Oil and spices");    
				rb2.setBounds(80,260,200,30);
				rb3=new JRadioButton("Beverages");    
				rb3.setBounds(80,290,200,30); 
				
				ButtonGroup bg=new ButtonGroup();    
				bg.add(rb1);bg.add(rb2); bg.add(rb3);
				update=new JButton("UPDATE ITEM");    
				update.setBounds(130,330,100,30);
				 JLabel info=new JLabel("");
				    info.setBounds(50,430,200,30);
				    
				    updatei.add(back);updatei.add(info);updatei.add(name);updatei.add(namet);updatei.add(price);updatei.add(pricet);
				    updatei.add(categories);updatei.add(rb1);updatei.add(rb2);updatei.add(rb3);updatei.add(update);
			 
				    updatei.setVisible(true);
				    updatei.setLayout(null);
				    updatei.setBounds(350, 0, 500, 550); 
			f.add(updatei);
			
			int row=jtbl.getSelectedRow();
			String item=jtbl.getModel().getValueAt(row, 0).toString();
			namet.setText(item);
			Float p=Float.parseFloat(jtbl.getModel().getValueAt(row, 1).toString());
			pricet.setText(p.toString());
			
			 ///////
			update.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int row=jtbl.getSelectedRow();
					String item=jtbl.getModel().getValueAt(row, 0).toString();
					try {
						PreparedStatement stmt2=con.prepareStatement("select * from pricechart where sid=? and pname=?");
						stmt2.setString(1, sid);stmt2.setString(2, item);
						ResultSet rs=stmt2.executeQuery(); 
						if(rs.next())
						{
							System.out.print("\t\t\t\t\t\tEnter price of item to update : ");
							float p=Float.parseFloat(pricet.getText());
							PreparedStatement stmt3=con.prepareStatement("update pricechart set price=? where pname=? and sid=? ");
							stmt3.setFloat(1, p);stmt3.setString(2, item);stmt3.setString(3, sid);
							 int ii=stmt3.executeUpdate(); 		
							 System.out.println(ii+" rows inserted");
							 System.out.println(" \t\t\t\t\t :::::::: SuccessFully Replaced ::::::::");
							 reloadTable(model);
							 info.setText("Updated.."+item);
							 JOptionPane.showMessageDialog(f, "Updated.."+item,  
				                     "Message",  
				                     JOptionPane.INFORMATION_MESSAGE);

						}else
						{
							System.out.println("\t\t\t\t\t\t Enter valid item name");
							JOptionPane.showMessageDialog(f, "Enter valid item name",  
	                                "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
						
					} catch (SQLException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
						System.out.println("\t\t\t\t\t\t Enter valid item name");
						JOptionPane.showMessageDialog(f, "Enter valid item name",  
                                "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
					
				}
			});	
		
	}
	
	void displayUserlist()
	{
		try {
			PreparedStatement stmt2=con.prepareStatement("select * from orders where shopName=?");
			stmt2.setString(1, sid);
			String uid;
			ResultSet rs=stmt2.executeQuery(); 
			if(rs.next())
			{
			uid=rs.getString("uid");
			
			PreparedStatement stmt3=con.prepareStatement("select uname from users where uid=?");
			stmt3.setString(1, uid);
			ResultSet rs2=stmt3.executeQuery(); 
			if(rs2.next())
			{
				System.out.println(rs2.getString("uid")+"  "+rs2.getString("uname"));
			}
			
			}else
			{
				System.out.println("\t\t\t\t\t\t Enter valid shop name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  JOptionPane.showMessageDialog(f, "Something Went Wrong",  
                      "ERROR", JOptionPane.ERROR_MESSAGE); 
		}	
	}
	
	User chatBoth()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\n\t\t\t\t\t\tUsers List...");
//		for(int i=0;i<ulist.size();i++)
//		{
//			System.out.print("\n\t\t\t\t\t\t"+(i+1) +" ");
//			System.out.println(ulist.get(i).name);
//		}
		

		displayUserlist();
		
		System.out.print("\n\t\t\t\t\t\tEnter user id to chat..:");
		String id=sc.next();
		User u;
		try {
		PreparedStatement stmt3=con.prepareStatement("select * from users where uid=?");
		stmt3.setString(1, id);
		
		ResultSet rs2=stmt3.executeQuery(); 
		if(rs2.next())
		{
			u=new User(rs.getString("uid"),rs.getString("name"),rs.getString("passwd"),rs.getString("address"));
		}else
		{
			System.out.println("\t\t\t\t\t\t Enter valid shop name");
			u=null;
		}

		}catch(SQLException e)
		{
			e.printStackTrace();
			u=null;
		}
//		User u=ulist.get(id-1);	
		return u;
	}
	
	void chatShop() throws Exception
	{
		//gui
		
		
		//
        int PORT = 8134;
		System.out.println("\n\n-------Chat Server Started...---------");
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("\n:::::::::Connecting to client..::::::::");
				
		Scanner sc=new Scanner(System.in);
			System.out.println("Server is up");
			Socket socket,socket1;
			ExecutorService pool=Executors.newFixedThreadPool(2);
			
			String ans="";
			int i=1;
//			while(i==1) {
			
				socket = ss.accept();
				System.out.println("waiting for client connection...");
			
			ClientHandler clientthread=new ClientHandler(socket,sf,shopName);
			clients.add(clientthread);
			pool.execute(clientthread);
			
			socket1 = ss.accept();
			System.out.println("waiting for client connection...");
		
		 clientthread=new ClientHandler(socket1,sf,shopName);
		clients.add(clientthread);
		pool.execute(clientthread);
//			 int result = JOptionPane.showConfirmDialog(f,"New Customer!! Do you want to continue?", "Question",
//		               JOptionPane.YES_NO_OPTION,
//		               JOptionPane.QUESTION_MESSAGE);
//			 if(result == JOptionPane.YES_OPTION){
//	              i=1;
//	            }else if (result == JOptionPane.NO_OPTION){
//	               i=2;
//	            }else {
//	              i=0;
//	            }
//			 System.out.println("3");
//			}

	}
	public String getName() {
		// TODO Auto-generated method stub
		return shopName;
	}
	public String getID() {
		// TODO Auto-generated method stub
		return sid;
	}
	public String getAddr() {
		// TODO Auto-generated method stub
		return place;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==rb1)
		{
			addItems();
		}
		
		if(e.getSource()==rb3)
		{
			updatePriceChart();
		}
		if(e.getSource()==rb4)
		{
			approveOrder();
		}
		
	}

}
class MyListCellRendererChart extends DefaultListCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public JComponent getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      //  User label = (User) value;
//        String name = label.getName();
//        String sid = label.getID();
//        String address = label.getAddr();
//        String labelText = "<html>SID: " + sid + "<br/>Name: " + name +"<br/>Place: " + address;
//        setText(labelText);

        return this;
    }

}
