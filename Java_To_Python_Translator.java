import java.util.ArrayList;

public class Java_To_Python_Translator
{
  public static String translate(String input)
  {
	  ArrayList<Token>tokens = new ArrayList<Token>();
	  Lexer lexer = new Lexer(input);
	  String output ="";
	  int index=0;
	  
		Token token = new Token(tokentype.Unknown, 0,"", 0);
		while(index!=input.lastIndexOf("}")) {	
				token = lexer.nextToken();
				
				if(token.getType()==tokentype.End){
					break;			
				}
				
				System.out.print(token.getType()+"Token: "+token.getText());
				tokens.add(token);

				if(token.value>0) {
					System.out.print(" Value:"+token.value+"");
				}
				
				System.out.println();
				index++;
				
				//Passed Arraylist to Interpreter
				tokens=Interpreter.Translate(tokens);
				
				//Returned array must be printed placed back into output string
				//loop tokens after interpretation for output
				
				output="";
		}
	  
		return output;
  }
}
//