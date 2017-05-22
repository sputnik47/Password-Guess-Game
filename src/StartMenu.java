import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu extends JFrame {

	public void openGame(){
		GameUI gameui = new GameUI();
		gameui.open();
	}
	
	public void open(){
		GameCode ctrl = new GameCode();
		GameUI gameui = new GameUI();
		JLabel label = new JLabel();
		
		JLabel name = new JLabel();
		JLabel buttonBG = new JLabel();
		JLabel gameTitle = new JLabel();
		JLabel instructions = new JLabel();
		JButton startGame_b = new JButton("Start Game");
		JButton instructions_b = new JButton("Instructions");
		
		JPanel instructions_panel = new JPanel(new GridLayout(11, 1));

		
		//frame initialization
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Start Menu");
		setResizable(false);
		setSize(300, 200);
		
		add(label);
		label.add(instructions_panel);
		label.add(instructions_b);
		label.add(instructions);
		label.add(startGame_b);
		label.add(gameTitle);
		label.add(buttonBG);
		label.add(name);
		
		
		name.setBounds(5, 150, 150, 25);
		buttonBG.setBounds(0, 82, 300, 70);
		gameTitle.setBounds(55, 15, 200, 50);
		startGame_b.setBounds(85, 120, 125, 25);
		instructions.setBounds(10, 155, 275, 270);
		instructions_b.setBounds(85, 90, 125, 25);
		
		
		instructions.setFont(new Font("SimSun", Font.PLAIN, 11));
		instructions.setForeground(Color.white);
		instructions.setText(ctrl.instructions);
		buttonBG.setOpaque(true);
		gameTitle.setFont(new Font("SimSun", Font.PLAIN, 25));
		gameTitle.setText("Password Guess");
		gameTitle.setForeground(Color.GREEN);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		instructions_panel.setOpaque(true);
		instructions_panel.setBackground(Color.darkGray);
		
		name.setText("Made By: Chandler Bone");
		name.setFont(new Font("System", Font.ITALIC, 10));
		
		//action listeners
		instructions_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setSize(300, 445);
				
				JLabel border = new JLabel();
				
				label.add(border);
				
				border.setBounds(0, 155, 325, 25);
				
				border.setText("_____________________________________________");
			}
		});
		
		startGame_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameui.open();
				dispose();
			}
		});
		
		setVisible(true);
	}
}
