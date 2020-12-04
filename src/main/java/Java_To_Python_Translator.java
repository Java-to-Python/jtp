
import java.util.ArrayList;

public class Java_To_Python_Translator {

	public static String translate(String input)
	{		
        ArrayList<ArrayList<String>> translatedArray = new ArrayList<>();
        ArrayList<Token> tokens = new ArrayList<>();

        Lexer lexer = new Lexer(input);
        String output = "";
        int index = -1;
        int mainClassIndex=-1;
        Token token = new Token(tokentype.Unknown, 0, "");
        
        while (token.getType()!=tokentype.End) 
        {
        	index++;
        	token = lexer.nextToken();
        	tokens.add(token);
        	
        	//Grab the index of token containing the main class's name
        	if((token.getText().contains("class") && (mainClassIndex != -1)))
        	{
        		mainClassIndex=index + 2; 
        	}
        	
        }
        
        //View input
        System.out.println(input);
        
        //View Tokens
		for(int i=0;i<tokens.size();i++) {
    		System.out.println("Token:" + tokens.get(i).getType() + "\t\t\tLiteral:" + tokens.get(i).getText() + "" );
    	}
        
        translatedArray = Interpreter.Translate(tokens);

        for (int i = 0; i < translatedArray.size(); i++) {
            for (int j = 0; j < translatedArray.get(i).size(); j++) {
                output += translatedArray.get(i).get(j);
            }
        }
        translatedArray.clear();
        index = -1;
        Interpreter.position = -1;
        
        if (mainClassIndex > 0) {
        	return output + "\n" + tokens.get(mainClassIndex).getText() +"()" + ".main()";
        } else {
        	return output; 
        }
        
    }
}