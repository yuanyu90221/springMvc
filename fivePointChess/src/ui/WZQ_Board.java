package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.inf.WZQ_config;

public class WZQ_Board extends JFrame{
	public Graphics g;
	public static WZQ_Board wzq;

	/**
	 * Constructor
	 */
	public WZQ_Board(){
		
	}
	
	/**
	 * initialize UI
	 */
	private void initUI(){
		/**
		 * set up 
		 */
		this.setTitle("五子棋");
		this.setSize(new Dimension(WZQ_config.dimSize ,WZQ_config.dimSize));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		JPanel jp = new JPanel(){

			/* (non-Javadoc)
			 * @see javax.swing.JComponent#paint(java.awt.Graphics)
			 */
			public void paint(Graphics g) {
				g.setColor(Color.BLACK);
				super.paint(g);
				for(int i= 0; i < 15; i++){
					g.drawLine(20, 20 + i * WZQ_config.ChessSize, 
							   20 + WZQ_config.Column * WZQ_config.ChessSize , 20 + i * WZQ_config.ChessSize);
				}
				for(int i= 0; i < 15; i++){
					g.drawLine(20 + i * WZQ_config.ChessSize, 20 , 
							   20 + i * WZQ_config.ChessSize , 20 + WZQ_config.Row * WZQ_config.ChessSize);
				}
				
				g.setColor(Color.BLACK);
				g.fillOval(133, 133, 15, 15);
				g.fillOval(293, 133, 15, 15);
				g.fillOval(453, 133, 15, 15);
				g.fillOval(133, 293, 15, 15);
				g.fillOval(293, 293, 15, 15);
				g.fillOval(453, 293, 15, 15);
				g.fillOval(133, 453, 15, 15);
				g.fillOval(293, 453, 15, 15);
				g.fillOval(453, 453, 15, 15);
				
				
			}
			
		};
	    jp.setBackground(Color.BLACK);
		
	}
}
