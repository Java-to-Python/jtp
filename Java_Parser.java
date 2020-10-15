// This file will be for the reader.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Java_Parser
{
  public static ArrayList parse(ArrayList<Token> tokens)
  {
	  String Expression="";
		
		ArrayList<Token>parsedTokens = new ArrayList<Token>();
		//read tokens in ArrayList 
		for(Token i: tokens){
			if(i.getText()=="class") {
				while(i.getType()!=tokentype.OpenC) {
						//Expression				
				}
				
					
			}
		}
			
		//}
		
		//return parsedTokens;
		return tokens;
  }
}
//