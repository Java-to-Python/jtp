import java.util.ArrayList;

public class Interpreter {

	public static ArrayList Translate(ArrayList<Token> tokens) {
		
		String main="";
		String fucntion="";
		String parameter="";
		int position=0;
		int indent=0;
		
		ArrayList<String>parsedTokens = new ArrayList<String>();
		
		//read tokens in ArrayList 
		for(Token i : tokens){
			
			if(i.getType() == tokentype.NewLine) {indent++;}
			if(i.getType() == tokentype.CloseC) {indent--;}
			for(Token z : tokens) {
				parsedTokens.add("\t");
				
				switch(i.getType()) {
				
				//case Keyword:
				//	break;
				
				}
				
				
			}				
			
			//if()
			}
						
		//return parsedTokens;
		return tokens;
	}
}
//