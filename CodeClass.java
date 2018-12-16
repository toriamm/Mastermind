import java.util.*;

public class CodeClass{

    private int boardLength = 0;
    private String[] colors = {"r", "o", "y", "g", "b", "i", "v", "p", "w", "t"};
    private ArrayList<String> randomGuess = new ArrayList<String>();
    private ArrayList<String> randomDupGuess = new ArrayList<String>();
    private ArrayList<String> board = new ArrayList<String>();
    private ArrayList<String> tempBoard = new ArrayList<String>();

    public CodeClass(int i){
	boardLength = i;
    }

    public int getBoardLength(){
	return boardLength;
    }

    public boolean addUnique(){
	String save = randomColor();
	if(!board.contains(save)){
	    board.add(save);
	    return true;
	}
	return false;
    }

    public boolean addUniqueGuess(){
	String saveGuess = randomColor();
	if(!randomGuess.contains(saveGuess)){
	    randomGuess.add(saveGuess);
	    return true;
	}
	return false;
    }

    public void createBoard(){
	for(int j = 0; j < boardLength; j++){
	    while(!addUnique()){
	    }
	}
    }
    
    public void createDupBoard(){
	for(int k = 0; k < boardLength; k++){
	    board.add(randomColor());
	}
    }

    public void randomGuess(){
	randomGuess.clear();
	for(int m = 0; m < boardLength; m++){
	    while(!addUniqueGuess()){
	    }
	}
    }

    public void randomDupGuess(){
	randomDupGuess.clear();
	for(int n = 0; n < boardLength; n++){
	    randomDupGuess.add(randomColor());
	}
    }

    private String randomColor(){
	int a = (int)(Math.random()*7);
	String r = colors[a];
	return r;
    }

    public String printBoard(){
	String result = "";
	for(String s : board){
	    result += s;
	}
	return result;
    }

    public String printRandGuess(){
	String finalGuess = "";
	for(String s : randomGuess){
	    finalGuess += s;
	}
	return finalGuess;
    }

    public String printRandDupGuess(){
	String finalDupGuess = "";
	for(String s : randomDupGuess){
	    finalDupGuess += s;
	}
	return finalDupGuess;
    }

    private void makeTempBoard(){
	tempBoard.clear();
	for(String b : board){
	    tempBoard.add(b);
	}
    }

    public int[] checkGuess(String g){
	int[] response = new int[3];
	int numberOfCorrect = 0;
	int numberOfHalf = 0;
	int numberOfWrong = 0;
	makeTempBoard();
	//System.out.println(tempBoard.size());
	String key = printBoard();

	for(int k = 0; k < g.length(); k++){
	    //System.out.println(g);
	    //System.out.println(k);

	    if(g.charAt(k) == key.charAt(k)){
		numberOfCorrect++;
	    }else if(key.contains("" + g.charAt(k))){ //<--'""(empty quotes) +' turns a single character into a String
		numberOfHalf++;
	    }else{
		numberOfWrong++;
	    }
	}
	response[0] = numberOfCorrect;
	response[1] = numberOfHalf;
	response[2] = numberOfWrong;
	return response;
    }
}
