package pj1_2020_kp_par0104;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Window {

	private String title;
	private Game game;
	private GameConstant gameCon;
	JFrame frame = new JFrame(title);
	JPanel panel = new JPanel(new BorderLayout());
	JTextPane text = new JTextPane();
	JTextPane titlePane = new JTextPane();
	JButton play = new JButton();
	JButton quit = new JButton();

	public Window(GameConstant gameCon, String title, Game game) {
		this.title = title;
		this.gameCon = gameCon;
		this.game = game;
	}
	
	public void createWindow() {
		Color midnightBlue = new Color(12, 105, 128);
		Color cyan = new Color(0, 168, 168);
		titlePane.setBackground(cyan);
		text.setBackground(midnightBlue);
		SimpleAttributeSet attribsTxt = new SimpleAttributeSet();
		SimpleAttributeSet attribsTit = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribsTxt, StyleConstants.ALIGN_CENTER);
		StyleConstants.setBold(attribsTxt, true);
		StyleConstants.setAlignment(attribsTit, StyleConstants.ALIGN_CENTER);
		StyleConstants.setBold(attribsTit, true);
		StyleConstants.setFontSize(attribsTit, 64);
		StyleConstants.setFontSize(attribsTxt, 24);
		StyleConstants.setFontFamily(attribsTit, "Arial");
		text.setParagraphAttributes(attribsTxt, true);
		titlePane.setParagraphAttributes(attribsTit, true);
		text.setEditable(false);
		titlePane.setEditable(false);
		
		text.setText("Move: \n W - up, S - down, A - left,\n D - right \n\n Shoot: Spacebar");
		titlePane.setText("Tank 1990");

		frame.setPreferredSize(new Dimension(gameCon.getWidth(), gameCon.getHeight()));
		frame.setMaximumSize(new Dimension(gameCon.getWidth(), gameCon.getHeight()));
		frame.setMinimumSize(new Dimension(gameCon.getWidth(), gameCon.getHeight()));

		play.setText("Play");
		quit.setText("Quit");

		play();
		quit();

		panel.add(titlePane, BorderLayout.NORTH);
		panel.add(play, BorderLayout.WEST);
		panel.add(text, BorderLayout.CENTER);
		panel.add(quit, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.setVisible(true);
	}
		
	public void play() {
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				frame.add(game);
				frame.setVisible(true);
				game.start();
			}
		});
	}
	
	public void quit() {
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
