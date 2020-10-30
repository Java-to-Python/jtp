import java.util.ArrayList;

public class Interpreter {

<<<<<<< HEAD
	public static ArrayList<ArrayList<String>> Translate(ArrayList<Token> tokens) 
        {
		@SuppressWarnings("unused")
=======
	public static ArrayList Translate(ArrayList<Token> tokens) 
        {
>>>>>>> parent of f011306... Merge remote-tracking branch 'upstream/master'
		String x = "";
                int indent = -1;
                
                ArrayList<ArrayList<String>> translate = new ArrayList<>();
                
                for(int i = 0; i < tokens.size(); i++)
                { 
                    ArrayList<String> parsedTokens = new ArrayList<>();
                    
                    for (int j = 0; j < indent; j++) 
                    {
                        parsedTokens.add("\t");
                    }
                    
                    switch (tokens.get(i).getType()) 
                    {
                        case NewLine:
                        {
                            translate.get(translate.size() - 1).add("\n");
                            break;
                        }
                        
                        case Keyword:
                        {
                            switch (tokens.get(i).getText())
                            {
                                case "public":
                                {
                                    break;
                                }
                                
                                case "class":
                                {
                                    indent = 0;
                                    parsedTokens.add("class " + tokens.get(i + 2).getText() + ":");
                                    i+=2;
                                    translate.add(parsedTokens);
                                    break;
                                    
                                }
                                
                                case "main":
                                {
                                    parsedTokens.add("def main():");
                                    i+=2;
                                    translate.add(parsedTokens);
                                    while(!tokens.get(i).getText().equals(")"))
                                    {
                                        i++;
                                    }
                                    break;
                                }
                                
                                case "while":
                                {
                                    parsedTokens.add("while (");
                                    
                                    while(!tokens.get(i).getText().equals(")"))
                                    {
                                        parsedTokens.add(tokens.get(i).getText());
                                        i++;
                                    }
                                    parsedTokens.add("):");
                                    translate.add(parsedTokens);
                                    break;
                                }
                                case "for":
                                {
                                    break;
                                }
                                
                                case "println":
                                {
                                    parsedTokens.add("print");
                                    i++;
                                    while(!tokens.get(i).getText().equals(";"))
                                    {
                                        parsedTokens.add(tokens.get(i).getText());
                                        i++;
                                    }
                                    translate.add(parsedTokens);
                                    break;
                                }
                                
                                case "boolean": case "char": case "double":
                                case "int": case "long": case "short": case "String":
                                {
                                    i+=2;
                                    
                                    while(!tokens.get(i).getText().equals(";"))
                                    {
                                        parsedTokens.add(tokens.get(i).getText());
                                        i++;
                                    }
                                    
                                    translate.add(parsedTokens);   
                                    break;
                                }
                            }
                            break;
                        }
                        
                        case OpenC:
                        {
                            indent++;
                            break;  
                        }
                        
                        case CloseC:
                        {
                            indent--;
                            break;  
                        }
                       
                        case Number:
                        {
                            break;
                        }
                        case Space:
                        {
                            break;
                        }
                        case Addition:
                        {
                            if ((tokens.get(i - 1).getType().toString() == "Word") && (tokens.get(i + 1).getType().toString() == "Addition")) 
                            {
                                String temp = tokens.get(i - 1).getText();
                                parsedTokens.add(temp + " = " + temp + " + " + "1");
                                
                                while(!tokens.get(i).getText().equals(";"))
                                {
                                    i++;
                                }
                                
                            }
                            
                            translate.add(parsedTokens); 
                            break;
                        }
                        case Unknown:
                        {
                            break;
                        }
                        case Subtraction:
                        {
                            break;
                        }
                        case Multiplication:
                        {
                            break;
                        }
                        case Division:
                        {
                            break;
                        }
                        case OpenB:
                        {
                            break;
                        }
                        case OpenP:
                        {
                            break;
                        }
                        case CloseP:
                        {
                            break;
                        }
                        case CloseB:
                        {
                            break;
                        }
                        case End:
                        {
                            break;
                        }
                        case SemiColon:
                        {
                            break;
                        }
                        case Equals:
                        {
                            break;
                        }
                        case EqualsC:
                        {
                            break;
                        }
                        case Word:
                        {
                            break;
                        }
                        case String:
                        {
                            break;
                        }
                        case Tab:
                        {
                            break;
                        }
                        case Dot:
                        {
                            break;
                        }

                        default:
                            break;
                    }
                }
		return translate;		
	}
}
//