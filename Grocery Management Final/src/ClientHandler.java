import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ClientHandler implements Runnable {

	//int port;
	private Socket client;
	private BufferedReader in;
	private 	DataInputStream din;        //variable declaration
	private DataOutputStream dout;
	JPanel chatuser;
	JPanel sf;
	JFrame f;
	String name;
	
	public ClientHandler(Socket cs,JPanel sf,String n) throws IOException
	{
		this.client=cs;
		//in= new BufferedReader(new InputStreamReader(System.in));
		din = new DataInputStream (client.getInputStream ());
		dout = new DataOutputStream (client.getOutputStream());	
		System.out.println("added in frame..");
		name=n;
		
	}
	
	@Override
	public void run(){
		 //gui	
		chatuser=new JPanel();
		chatuser.setVisible(true);
	     chatuser.setLayout(null);
	     chatuser.setBounds(0, 0, 500, 550); 
		f=new JFrame("Chat server "+name);
		f.setVisible(true);f.setLayout(null);f.setSize(500,550);
		f.getContentPane().add(chatuser);
		 Color c1 = new Color(235, 251, 255); 
		    chatuser.setBackground(c1);
		    Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
			Border blackline = BorderFactory.createLineBorder(Color.black);
			JTextArea msgall=new JTextArea(30,30);
		    msgall.setBounds(50,50,350,270);  
					msgall.setFont(f3);
					msgall.setEditable(false);
					msgall.setBorder(blackline);
					//msgall.setBorder(blackline);
					JTextArea sendm=new JTextArea();
					
			        sendm.setBounds(50,400,250,30);  
			        sendm.setFont(f3);	
			        sendm.setBorder(blackline);
			        JButton send=new JButton("SEND");
			        send.setBounds(310,400,100,30);  
			        send.setFont(f3);
			        chatuser.add(msgall); chatuser.add(sendm); chatuser.add(msgall);chatuser.add(send);
				     
				 				
			    	System.out.println("Created layoutssss.server.");
			    	String message="";
                      send.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String message="";
							try {
							System.out.print("Enter your message here: ");
								message = sendm.getText();
								msgall.append("\nYou: "+message);	
								dout.writeUTF(message);	
								System.out.println("sent");
								
							
							} catch (IOException ee) {
								// TODO Auto-generated catch block
								ee.printStackTrace();
							}	
						}
					});
			    	
			    	while(true)
			    	{
			    		System.out.println();
			    		try {
							message = din.readUTF();
							System.out.println("Client: "+message);
							msgall.append("\nClient: "+message);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
			    	}

			    	
		    ///
	}
	

}
