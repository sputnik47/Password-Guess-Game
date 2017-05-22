
public class GameCode {
	
	public int[] code = {1, 1, 1, 1};
	public int[] input = new int[4];
	public int numSelected = 0;
	public int attempts = 0;
	public Boolean gameOver = false;
	
	
	public void createCode(){
		for(int x = 0; x < 4; x++)
			code[x] = (int)(Math.random() * 6) + 1;
		
	}
	
	public int numCorrect_noPos(){
		int[] numsAttempted  = {9, 9, 9, 9};
		Boolean attempted = false;
		int total = 0;
		int stored = 0;
		
		for(int x = 0; x < 4; x++){
			
			boolean[] correct = numCorrect_posBool();
			
			for(int a: numsAttempted){
				if(input[x] == a){
					attempted = true;
				}
			}
			
			if(attempted == false){
				for(int y = 0; y < 4; y++){
					if(input[x] == code[y] && x != y && correct[y] == false)
						total++;
				}}
			
			numsAttempted[stored] = input[x];
			stored++;
			attempted = false;
		}
			
		return total;
	}
	
	public int numCorrect_pos(){
		
		int total = 0;
		
		for(int x = 0; x < 4; x++){
			if(input[x] == code[x])
				total++;
		}
		return total;
	}
	
	public boolean[] numCorrect_posBool(){
		
		boolean[] correct_nums = new boolean[4];
		
		for(int x = 0; x < 4; x++){
			if(input[x] == code[x])
				correct_nums[x] = true;
			else
				correct_nums[x] = false;
		}
		return correct_nums;
	}
	
	public void changeDigit(int num){
		code[numSelected] = num;
	}
	
	public void moveSelected(Boolean move){ 
		//if move = true, right. if move = false, left.
		if(move == true && numSelected < 3)
			numSelected++;
		if(move == false && numSelected > 0)
			numSelected--;
	}
	
	public boolean correctCode(){
		int numsCorrect = 0;
		
		for(int x = 0; x < 4; x++){
			if(input[x] == code[x])
				numsCorrect++;
		}
		
		if(numsCorrect == 4)
			return true;
		else
			return false;
	}
	
	public String instructions = "<html>Instructions:<br>"
			+ "<br>"
			+ "In this game you are given a secret code<br>"
			+ "for you to solve. To find this code you<br>"
			+ "must test several guesses. Using your<br>"
			+ "keypad and the test button you will be<br>"
			+ "able to extract information out of the<br>"
			+ "log, of which tells you what numbers<br>"
			+ "are correct but in wrong positions and<br>"
			+ "numbers that are correct and in right<br>"
			+ "positions.<br>"
			+ "<br>"
			+ "You have 10 attempts before you lose, so<br>"
			+ "make your guesses wisely and use your logs<br>"
			+ "to gain advantage.<br>"
			+ "Good Luck :)</html>";
	
}
