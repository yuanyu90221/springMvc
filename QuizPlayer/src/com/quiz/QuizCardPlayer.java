package com.quiz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardPlayer {

	private JTextArea display;
	//private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private QuizCard currentCard;
	private int currentCardIndex;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;
	
	public static void main(String[] args){
		QuizCardPlayer reader = new QuizCardPlayer();
		reader.go();
	}
	
	public void go(){
		//主要邏輯
		frame = new JFrame("Quiz Card Player");
		//設定關閉 frame的operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		
		display = new JTextArea(10, 20);
		display.setFont(bigFont);
		display.setLineWrap(true);
		display.setEditable(true);
		
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		nextButton = new JButton("Show Question");
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load card set");
		loadMenuItem.addActionListener(new OpenMenuListener());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640, 500);
		frame.setVisible(true);
	}
	
	public class NextCardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isShowAnswer){
				// show the answer because they've seen the question
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer = false;
			}
			else{
				// show the next question
				if( currentCardIndex < cardList.size()){
					showNextCard();
				}
				else{
					// there are no more cards
					display.setText("That was last card");
					nextButton.setEnabled(false);
					cardList.clear();
					currentCardIndex = 0;
				}
			}
		}
		
	}
	
	public class OpenMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
			nextButton.setEnabled(true);

		}
		
	}

	public void loadFile(File file){
		
		cardList = new ArrayList<QuizCard>();
		BufferedReader reader  = null;
		FileReader in = null;
		try {
			in = new FileReader(file);
			reader = new BufferedReader(in);
			String line = null;
			while((line = reader.readLine())!=null){
				makeCard(line);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File Not Found Exceptioln");
		}
		finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		showNextCard();
	}
	
	private void makeCard(String lineToParse) {
		String[] result = lineToParse.split("/");
		QuizCard card = new QuizCard(result[1],result[0]);
		cardList.add(card);
		System.out.println("mard a card "+card);		
	}

	public void showNextCard() {
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Anwser");
		isShowAnswer = true;
	}
	
}
