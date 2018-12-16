public class CodeBreaker{

    public String makeGuess(){
	Console con2 = new Console();
	String guess = con2.userInput("Enter a guess: ");
	return guess;
    }
}
