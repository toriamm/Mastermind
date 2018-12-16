import java.io.*;

public class Console{

    private boolean forHist = false;
    private String inputLine = "";

    public String userInput(String message){
	setHist(true);
	System.out.print(message + " ");
	try{
	    BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
	    inputLine = is.readLine();
	    if(inputLine.length() == 0){
		return null;
	    }
	}catch(IOException e){
	    System.out.println("IOException is: " + e);
	}
	//setInput(inputLine);
	if(inputLine == "history"){
	    CodeHistory ch1 = new CodeHistory();
	    System.out.print(ch1.guessHistory());
	}
	setHist(false);
	return inputLine;
    }

    public static void output(String message, boolean newLine){
	System.out.print(message);
	if(newLine){
	    System.out.println();
	}
    }

    public void setInput(String i){
	inputLine = i;
    }

    public String getInput(){
	return inputLine;
    }

    public void setHist(boolean h){
	forHist = h;
    }

    public boolean getHist(){
	return forHist;
    }
}
