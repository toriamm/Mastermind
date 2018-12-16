public class MasterMind{
    private int keyLength = 0;
    private String playerName = "";
    private int guessLength = 10;
    private int duplicate = 0;
    private int playerType = 1;

    private String correct = " units have a white peg (the correct color in the correct place)\n";
    private String half = " units have a black peg (the correct color in the wrong place)\n";
    private String wrong = " units have no peg (not included in the code)";

    private String update;
    
    private int numberOfGuesses = 0;
    private int guessesLeft = 50;
    private boolean isAlive = true;

    public boolean isHuman;
    public boolean isRandom;
    public boolean isSmart;

    public void setupGame(){
	Console con1 = new Console();
	CodeBreaker cb1 = new CodeBreaker();
	setPlayerName(con1.userInput("\n(PRESS ENTER TO SUBMIT)\nEnter your name: "));
	System.out.println("\nHello, " + playerName + "! Welcome to Master Mind.\n'What's Master Mind,' you ask? Well...\nIt's a game! There's a secret code that needs to be guessed correctly.\nA code of colors will be generated (by me).\nAnd then that code will have to be decrypted (by either you or this computer).\nSimple, right? I'll even let you choose how hard it gets.\nLet's start!\n\nHow long would you like the code to be?");
	String length = con1.userInput("Pick a number from 2 to 10.");
	setLength(Integer.parseInt(length));
	CodeClass cc1 = new CodeClass(keyLength);
	System.out.println("\nHere's how it works:\nThe guess gets entered as a color.\nFor example, if the code is 7 units long, typing: roygbiv\nWould be guessing the code as /red/orange/yellow/green/blue/violet/\n\nAvailable colors are (*HINT* Remember these):\nr = red\no = orange\ny = yellow\ng = green\nb = blue\ni = indigo\nv = violet\np = pink\nw = white\nt = tan");
	String dup = con1.userInput("\nWould you like the code to contain duplicate colors?\n1 = yes. 0 = no.");
	setDup(Integer.parseInt(dup));
	if(duplicate == 0){
	    System.out.println("\nThe code will not contain the same color more than once.");
	}else{
	    System.out.println("\nThe code can now contain the same color more than once.\nHowever, it might not contain every available color.");
	}
	String chooseType = con1.userInput("\nNow it's time to choose a player type.\n1 = Human (You try to guess my code)\n2 = Random (This computer will try to guess my code)\n3 = Smart (I try to guess this computer's code)");
	setType(Integer.parseInt(chooseType));
	if(playerType == 1){
	    isHuman = true;
	    isRandom = false;
	    isSmart = false;
	    if(duplicate == 0){
		cc1.createBoard();
	    }else{
		cc1.createDupBoard();
	    }
	}else if(playerType == 2){
	    isHuman = false;
	    isRandom = true;
	    isSmart = false;
	    System.out.print("\nSo you're more of a watcher than a doer, is it?\nThis is my code: ");
	    if(duplicate == 0){
		cc1.createBoard();
		System.out.print(cc1.printBoard() + "\n");
	    }
	    if(duplicate == 1){
		cc1.createDupBoard();
		System.out.print(cc1.printBoard() + "\n");
	    }
	}else{
	    isHuman = false;
	    isRandom = false;
	    isSmart = true;
	    //insert code for smart guesser
	}
	String minMax = con1.userInput("\nHow many guesses do you think should be allowed?\nThe minimum is 1 and the maximum is 50.\nDon't be afraid of a little challenge!");
	setMinMax(Integer.parseInt(minMax));
	//System.out.println("\nTHIS IS THE CODE YOU ARE LOOKING FOR: " + cc1.printBoard());
	
	while(isAlive && isHuman){
	    System.out.println("\nThe code is " + keyLength + " units long and there are " + guessesLeft + " guesses left.\nIf you want to review all your guesses and responses, then type 'history' at any time.\nStart decrypting it now!");
	    String guess = cb1.makeGuess();
	    System.out.println("\nYour guess is: " + guess);
	    int[] response = cc1.checkGuess(guess);
	    numberOfGuesses++;
	    guessesLeft--;
	    if(con1.getInput().equalsIgnoreCase("history")){
		numberOfGuesses--;
		guessesLeft++;
	    }
	    if(response[0] == keyLength){
		if(guessesLeft == (Integer.parseInt(minMax) - 1)){
		    System.out.println("\nHey, " + playerName +". You totally blew my mind!\nOnly 1 guess and you cracked " + cc1.printBoard() + " as the code?!\nAmazing! You must be a super genius or something.\nMaybe I'll let you take over from here and you can create the next game!\nHa! I'm kidding.\nThat's still above your paygrade...\nJust make the code harder next time, you sly Master Mind fox.\nAnyway, congrats! You're a winner!\n");
		    isAlive = false;
		}else{
		    System.out.println("\nCongratulations, " + playerName + "! You won!\nIt took you " + numberOfGuesses + " guesses to crack the code.\nIt was: " + cc1.printBoard() + "\n");
		    isAlive = false;
		}
	    }else{
		update = "" + response[0] + correct + response[1] + half + response[2] + wrong;
		System.out.println("\nUpdate for Guess " + numberOfGuesses + ": \n" + update);
		System.out.println("\nYou have " + guessesLeft + " guesses left.");
		if(guessesLeft == 0){
		    System.out.println("\nOh no! You ran out of guesses...\nThe code was: " + cc1.printBoard() + "\n");
		    isAlive = false;
		}
	    }
	}

	while(isAlive && isRandom){
	    System.out.println("\nMy code is: " + cc1.printBoard());
	    int[] response = new int[]{-1};
	    if(duplicate == 0){
		cc1.randomGuess();
		String randomGuess = cc1.printRandGuess();
		System.out.println("The computer guessed: " + randomGuess);
		response = cc1.checkGuess(randomGuess);
	    }
	    if(duplicate == 1){
		cc1.randomDupGuess();
		String randomDupGuess = cc1.printRandDupGuess();
		System.out.println("The computer guessed: " + randomDupGuess);
		response = cc1.checkGuess(randomDupGuess);
	    }
	    numberOfGuesses++;
	    guessesLeft--;
	    if(response[0] == keyLength){
		if(guessesLeft == (Integer.parseInt(minMax) - 1)){
		    System.out.println("\nHey, " + playerName +", this computer's actually pretty smart, eh?\nOnly 1 guess and it cracked " + cc1.printBoard() + " as the code.\nCongrats, computer! You won!");
		    isAlive = false;
		}else{
		    System.out.println("\nHey, " + playerName + ", that took longer than expected, right?\nIt took the computer " + numberOfGuesses + " guesses to crack the code.\nMy code was: " + cc1.printBoard() + "\nWas that too hard?\n");
		    isAlive = false;
		}
	    }else{
		update = "" + response[0] + correct + response[1] + half + response[2] + wrong;
		System.out.println("\nUpdate for Guess " + numberOfGuesses + ": \n" + update);
		System.out.println("\nThe computer has " + guessesLeft + " guesses left.");
		if(guessesLeft == 0){
		    System.out.println("\nOh no! The computer ran out of guesses...\nThe code was: " + cc1.printBoard() + "\nMy code wasn't too hard, was it? Did you make the parameters impossible? Come one now, " + playerName + ". I expected more from you...");
		    isAlive = false;
		}
	    }
	}
	
    }
    
    public void setLength(int i){
	keyLength = i;
    }

    public int getLength(){
	return keyLength;
    }

    public void setPlayerName(String n){
	playerName = n;
    }

    public String getPlayerName(){
	return playerName;
    }

    public void setDup(int d){
	duplicate = d;
    }

    public int getDup(){
	return duplicate;
    }

    public void setType(int t){
	playerType = t;
    }

    public int getType(){
	return playerType;
    }

    public void setMinMax(int m){
	guessesLeft = m;
    }

    public int getMinMax(){
	return guessesLeft;
    }
}
