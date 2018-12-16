import java.util.ArrayList;

public class CodeHistory{

    MasterMind mm2 = new MasterMind();
    int keyLength = mm2.getLength();
    CodeClass cc2 = new CodeClass(keyLength);
    Console con3 = new Console();
    
    int numberOfGuesses = mm2.getMinMax();
    String guess = con3.getInput();
    boolean isHist = con3.getHist();

    int[] response = cc2.checkGuess(guess);

    ArrayList<String> userGuess = new ArrayList<String>();
    String printHist = "";

    public void appendHistory(){
	for(int i = 1; i < numberOfGuesses && response[0] != mm2.getLength() && isHist == true; i++){
	    userGuess.add("Your guess for turn " + i + "/" + numberOfGuesses + " was " + guess + " and resulted in " + response[0] + " white pegs and " + response[1] + " black pegs.\n");
	}
    }

    public String guessHistory(){
	for(String h : userGuess){
	    printHist += h;
	}
	return printHist;
    }
}
