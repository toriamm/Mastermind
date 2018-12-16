public class TestClass{
    public static void main(String[] args){
	//call the MasterMind class for test
	MasterMind mmTest = new MasterMind();
	
	//test the setters and getters
	mmTest.setLength(8);
	System.out.println("The code length is now set to: " + mmTest.getLength());

	mmTest.setPlayerName("Your favorite coder Vicki");
	System.out.println("The player's name is now set to: " + mmTest.getPlayerName());

	mmTest.setDup(1);
	System.out.println("The duplicate option is on if this number is '1': " + mmTest.getDup());

	mmTest.setType(1);
	System.out.println("The 'Human' player type is active if this number is '1': " + mmTest.getType());

	mmTest.setMinMax(25);
	System.out.println("The number of possible guesses is now set to: " + mmTest.getMinMax());
    }
}
