package ressource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ReaderConsole {

	
	public static int readIntConsole() throws IOException{
		int chiffre=0;
		BufferedReader inFromUser = new BufferedReader(
				new InputStreamReader(System.in));
		String sentence = inFromUser.readLine();
		try {
		
			chiffre = Integer.parseInt(sentence);
		} catch (Exception e) {
			if (sentence.equalsIgnoreCase("un chiffre")
					|| sentence.equalsIgnoreCase("chiffre")) {
				System.out.println("Tr�s malin...");
				return readIntConsole();
			} else {
				System.out.println("Merci d'�crire un chiffre.");
				return readIntConsole();
			}
		}
		return chiffre;
	}
	
	public static String readStringConsole() throws IOException{
		
		BufferedReader inFromUser = new BufferedReader(
				new InputStreamReader(System.in));
		String sentence = inFromUser.readLine();
		
		
		return sentence;
	}
	
}
