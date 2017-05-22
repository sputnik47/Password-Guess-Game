import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUI extends JFrame{
	
	GameCode ctrl = new GameCode();
	
	public void open(){
		
		JLabel label = new JLabel();
		
		JLabel num_bg = new JLabel();
		JLabel border = new JLabel();
		JButton test_b = new JButton("Test");
		JLabel log_label = new JLabel("Log");
		JPanel log = new JPanel(new GridLayout(11, 0));
		JLabel attempts_label = new JLabel("Attempts: 0");
		JButton reset_b = new JButton("Reset");
		
		Font code_font = new Font("SimSun", Font.PLAIN, 20);
		
		//frame initialization
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Password Guess");
		setResizable(false);
		setSize(400,358);
		
		
		add(label);
		label.add(log);
		label.add(test_b);
		label.add(border);
		label.add(reset_b);
		label.add(log_label);
		label.add(attempts_label);
		
		log.setBounds(25, 150, 350, 165);
		num_bg.setBounds(0, 35, 400, 55);
		border.setBounds(0, 135, 400, 2);
		test_b.setBounds(240, 50, 75, 25);
		reset_b.setBounds(145, 5, 100, 25);
		log_label.setBounds(10, 115, 100, 25);
		attempts_label.setBounds(95, 87, 200, 50);
		

		log_label.setFont(new Font("System", Font.ITALIC, 12));
		
		log.setBackground(Color.darkGray);
		log.setOpaque(true);
		log_label.setForeground(Color.GREEN);
		border.setOpaque(true);
		border.setBackground(Color.GREEN);
		num_bg.setOpaque(true);
		num_bg.setBackground(Color.darkGray);
		label.setOpaque(true);
		label.setBackground(Color.black);
		label.setFocusable(true);
		test_b.setBackground(Color.darkGray);
		test_b.setForeground(Color.white);
		attempts_label.setForeground(Color.green);
		attempts_label.setHorizontalAlignment(JLabel.CENTER);
		reset_b.setVisible(false);
		reset_b.setBackground(Color.darkGray);
		reset_b.setForeground(Color.white);
		
		
		
		//creating labels for all of the loglines
		JLabel[] logLine = new JLabel[11];
		for(int i = 0; i < 11; i++){
			if(i < 10)
				logLine[i] = new JLabel("    [ " + Integer.toString(i) + ".  ] ");
			else
				logLine[i] = new JLabel("    [ " + Integer.toString(i) + ".] ");
			
			logLine[i].setForeground(Color.green);
			log.add(logLine[i]);
		}
		
		logLine[0].setText("Attempts     |      Code      | \u2714 Num \u2715 Position | \u2714 Num \u2714 Position");
		logLine[0].setFont(new Font("System", Font.ITALIC, 11)); //header
		logLine[0].setHorizontalAlignment(JLabel.CENTER);
		
		
		//creating all the changable numbers at the top
		JLabel[] number = new JLabel[4];
		for(int i = 0; i < 4; i++){
			number[i] = new JLabel("1");
			number[i].setForeground(Color.white);
			number[i].setFont(code_font);
			number[i].setOpaque(true);
			number[i].setHorizontalAlignment(JLabel.CENTER);
			number[i].setBackground(Color.darkGray);
			label.add(number[i]);
		}
		
		label.add(num_bg);
		number[0].setBounds(80, 45, 25, 35);
		number[1].setBounds(110, 45, 25, 35);
		number[2].setBounds(140, 45, 25, 35);
		number[3].setBounds(170, 45, 25, 35);
		number[0].setBackground(Color.gray);
		
		
		//key listening
		label.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_RIGHT){//arrow keys
					number[ctrl.numSelected].setBackground(Color.darkGray);
					ctrl.moveSelected(true);
					number[ctrl.numSelected].setBackground(Color.gray);}
					
				if(key == KeyEvent.VK_LEFT){
					number[ctrl.numSelected].setBackground(Color.darkGray);
					ctrl.moveSelected(false);
					number[ctrl.numSelected].setBackground(Color.gray);}
				
				if(key >= 97 && key <= 102){//numpad keys
					number[ctrl.numSelected].setText(Integer.toString(key - 96));
					ctrl.input[ctrl.numSelected] = (key - 97);
					number[ctrl.numSelected].setBackground(Color.darkGray);
					ctrl.moveSelected(true);
					number[ctrl.numSelected].setBackground(Color.gray);}
				
				if(key >= 49 && key <= 54){//bar keys
					number[ctrl.numSelected].setText(Integer.toString(key - 48));
					ctrl.input[ctrl.numSelected] = (key - 49);
					number[ctrl.numSelected].setBackground(Color.darkGray);
					ctrl.moveSelected(true);
					number[ctrl.numSelected].setBackground(Color.gray);
					}
					
				
				//System.out.println(key);
			}

			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		
		//action when test button is pressed
		test_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				for (int i = 0; i < 4; i++) //puts user input into gamecode input
					ctrl.input[i] = Integer.valueOf(number[i].getText());
				
				ctrl.attempts++;
				
				String space = "              ";
				String space_1 = "                "; //spacing for different areas in log labels
				String space_2 = "                               ";
				
				//makes labels stop being made when game is over
				if(ctrl.gameOver == false){
					if(ctrl.attempts < 10){//makes extra spacing on brackets to keep even between two and one digit attempts
						logLine[ctrl.attempts].setText("    [ " + (ctrl.attempts) + ".  ]"+ space + Arrays.toString(ctrl.input) + space_1 
								+ ctrl.numCorrect_noPos() + space_2 + ctrl.numCorrect_pos());
					}
					else{
						logLine[ctrl.attempts].setText("    [ " + (ctrl.attempts) + ".]"+ space + Arrays.toString(ctrl.input) + space_1 
								+ ctrl.numCorrect_noPos() + space_2 + ctrl.numCorrect_pos());
					}
				}
				
				//detects if you won or lost
				if(ctrl.attempts < 10 && !(ctrl.correctCode()))
					attempts_label.setText("Attempts: " + ctrl.attempts);
				else if(ctrl.correctCode() == true && ctrl.gameOver == false){
					attempts_label.setText("YOU WON!!!");
					ctrl.gameOver = true;
					reset_b.setVisible(true);
					}
				else if(ctrl.correctCode() == false && ctrl.gameOver == false){
					attempts_label.setText("You Lost. The code was " + Arrays.toString(ctrl.code));
					ctrl.gameOver = true;
					reset_b.setVisible(true);
					}
				
				number[ctrl.numSelected].setBackground(Color.darkGray);
				ctrl.numSelected = 0;
				number[ctrl.numSelected].setBackground(Color.gray);
				
				label.requestFocus(true);//makes label use action listener again
			}
		});
		
		//reset button
		reset_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StartMenu menu = new StartMenu();
				dispose();
				menu.openGame();
			}
		});
		ctrl.createCode();
		
		setVisible(true);
	}

}
