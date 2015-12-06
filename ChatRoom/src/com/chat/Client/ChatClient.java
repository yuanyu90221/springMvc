package com.chat.Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClient extends JFrame implements ActionListener {

	private static final int SERVER_PORT = 8888;
	String name, ip = "";
	BufferedReader reader;
	PrintStream writer;
	Socket cSocket;
	
	JTextArea messageOutput = new JTextArea(15,50);
	
	JTextField messageInput = new JTextField(20);
	JLabel jLmane = new JLabel("稱號");
	JLabel jLip = new JLabel("連線ip:");
	JTextField jFmane = new JTextField("匿名",10);
	JTextField jFip = new JTextField("127.0.0.1",10);
	JLabel state = new JLabel("請輸入稱號與連線ip");
	JMenuBar mBar = new JMenuBar();
	JMenu mFile = new JMenu("檔案");
	JMenuItem mFileSave = new JMenuItem("儲存");
	
	public ChatClient(){
		super("多人連線client端");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel maneipPanel = new JPanel();
		
		JButton setmaneip = new JButton("連線設定");
		
		setmaneip.addActionListener(this);
		
		maneipPanel.add(jLmane);
		maneipPanel.add(jFmane);
		maneipPanel.add(jLip);
		maneipPanel.add(jFip);
		
		maneipPanel.add(setmaneip);
		getContentPane().add(BorderLayout.NORTH, maneipPanel);
		JButton sendButton = new JButton("送出");
		
		sendButton.addActionListener(this);
	    messageOutput.setLineWrap(true);
	    messageOutput.setWrapStyleWord(true);
	    messageOutput.setEditable(false);
	    
	    JScrollPane qScroller = new JScrollPane(messageOutput);
	    
	    qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    JPanel mainPanel = new JPanel();
	    mainPanel.add(qScroller);
	    mainPanel.add(messageInput);
	    mainPanel.add(sendButton);
	    
	    getContentPane().add(BorderLayout.CENTER, mainPanel);
	    
	    mFileSave.addActionListener(this);
	    
	    mFile.add(mFileSave);
	    mBar.add(mFile);
	    setJMenuBar(mBar);
	    
	    getContentPane().add(BorderLayout.SOUTH, state);
	    setSize(600, 450);
	    setVisible(true);
	    
	    addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("離開聊天室");
				System.exit(0);
			}
	    	
	    });
	}
	@Override	
	public void actionPerformed(ActionEvent e) {
		String actionId = e.getActionCommand();
		if( actionId.equals("連線設定")){
			name = jFmane.getText();
			ip = jFip.getText();
			
			state.setText("稱號:"+ name +" : " + ip);
			
			establishConnection();
			
			Thread readerThread = new Thread(new MessageReader());
			
			readerThread.start();
		}
		else if( actionId.equals("送出")){
			if((ip != null) && (messageInput.getText() != "")){
				writer.println(name + " : " + messageInput.getText());
				writer.flush();
				
				messageInput.setText("");
			}
		}
		else if(actionId.equals("儲存")){
			try {
				FileWriter f = new FileWriter("D:/log.txt");
				f.write(messageOutput.getText());
				f.close();
				state.setText("已儲存檔案");
			} catch (IOException e1) {
				System.out.println("儲存檔案失敗");
			}
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
	}

	/**
	 * 建立連線
	 */
	private void establishConnection(){
		try {
			// 
			cSocket = new Socket(ip, SERVER_PORT);
			// 取的input連線
			InputStreamReader streamReader = new InputStreamReader(cSocket.getInputStream());
			
			reader = new BufferedReader(streamReader);
			
			writer = new PrintStream(cSocket.getOutputStream());
			
			state.setText("建立連線成功");
			System.out.println("建立連線成功");
		} catch (IOException e) {
			System.out.println("建立連線失敗!");
		}
	}
	
	public class MessageReader implements Runnable{
		
		@Override
		public void run() {
			String message;
			try {
				while((message = reader.readLine()) != null){
					messageOutput.append(message+"\n");
				}
			} catch (IOException e) {
				System.out.println("Message讀取失敗");
			}			
		}
		
	}
}
