package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class ChatServer {
	private static final int SERVER_PORT = 8888;
	Vector connectInfo;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatServer().go();
	}

	
	public void go(){
		connectInfo = new Vector();
		try {
			ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
			while(true){
				// 
				Socket cSocket = serverSocket.accept();
				// OutputStream
				PrintStream writer = new PrintStream(cSocket.getOutputStream());
				// writer Object
				System.out.println(writer);
				// connectionInfo
				connectInfo.add(writer);
				// receive info
				Thread t = new Thread(new ServerProc(cSocket));
				t.start();
				System.out.println(cSocket.getLocalSocketAddress()+"have a "+(t.activeCount()-1)+"Connection");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ServerProc
	 * @author user
	 *
	 */
	public class ServerProc implements Runnable{
		
		// BufferReader
		BufferedReader reader;
		// client socket
		Socket clientSocket;
		// serverProc
		public ServerProc(Socket cSocket){
			clientSocket = cSocket;
			// 
			try {
				InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
				
				reader = new BufferedReader(isReader);
			} catch (IOException e) {
				System.out.println("faile to Connect Procces");
			}			
		}
		// 
		@Override
		public void run() {
			String message;
			
			try {
				while((message = reader.readLine())!= null){
					System.out.println("received : "+message);
					//client message
					broadcast(message);
				}
			} catch (IOException e) {
				System.out.println("one connection closed");
			}
		}
		
		public void broadcast(String message){
			// Iterator clientInfo
			Iterator it = connectInfo.iterator();
			
			while(it.hasNext()){
				PrintStream writer = (PrintStream) it.next();
				writer.println(message);
				writer.flush();
			}
		}
		
	}
	
}
