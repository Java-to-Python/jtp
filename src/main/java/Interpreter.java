
import java.util.ArrayList;

public class Interpreter {

    public static boolean W = false;
    public static boolean I = false;
    public static boolean F = false;
    public static int indent = 0;
    public static int position = -1;
    public static int currentLineNumber=1;
    public static ArrayList<ArrayList<String>> translate = new ArrayList<>();
    public static ArrayList <String> parsedTokens = new ArrayList<>();
    
    public static ArrayList<ArrayList<String>> Translate(ArrayList<Token> tokens) {
 
    	    	
        while (position < tokens.size()) {
            position++;
            parsedTokens = new ArrayList<>();

            
            if (position >= tokens.size()) {
                return translate;
            }
            switch (tokens.get(position).getType()) 
            {
            	case Comment:{
            		translate.get(translate.size() - 1).add(tokens.get(position).getText());
            		break;
            	}
                case NewLine: {
                    try {
                    	currentLineNumber++;
                        translate.get(translate.size() - 1).add("\n");
                        for (int j = 0; j < indent; j++) {
                        	translate.get(translate.size() - 1).add("\t");
                        }
                    } catch (Exception e) {

                    }
                    break;
                }

                case Keyword: {
                    switch (tokens.get(position).getText()) {
                        case "public": {
                
                            break;
                        }

                        case "private": {
                            break;
                        }
                        case "class": {
                        	
                            indent = 0;
                            parsedTokens.add("class " + tokens.get(position + 2).getText() + ":");
                            position += 2;
                            translate.add(parsedTokens);
                            break;

                        }

                        case "main": {
                            parsedTokens.add("def main(self):");
                            position += 2;
                            translate.add(parsedTokens);
                            while (!tokens.get(position).getText().equals(")")) {
                                position++;
                            }
                            break;
                        }

                        case "if": {
                            I = true;

                            parsedTokens.add("if ");

                            translate.add(parsedTokens);

                            while (!tokens.get(position).getText().equals(")")) {
                                Translate(tokens);
                            }

                            I = false;
                            break;
                        }
                        
                        

                        case "while": {
                            W = true;
                            parsedTokens.add("while ");
                            position+=2;
                            while (!tokens.get(position).getText().equals(")")) 
                            {
                            	parsedTokens.add(tokens.get(position).getText());
                                
                            	position++;
                            }
                            parsedTokens.add(":");
                            translate.add(parsedTokens);
                            W = false;
                            break;
                        }
                        case "for": {
                        	//Translates for-each loops
                            F = true;
                            parsedTokens.add("for");
                            
                            position+=2;
                            while (!tokens.get(position).getText().equals(")")) 
                            {	
                            	//Skip ( token
                            	if(tokens.get(position).getText() == "(") {
                            		position++;
                            		continue;
                            	}
                            	//Translate : token
                            	if(tokens.get(position).getText() == ":") {
                            		parsedTokens.add("in");
                            		position++;
                            		continue;
                            	}
                            	//Skip : variables
                            	if(tokens.get(position).getType() == tokentype.Keyword) {
                            		position++;
                            		continue;
                            	}
                            	
                            	//Skip Variables
//                            	if(tokens.get(position).getText() == "(") {
//                            		position++;
//                            		continue;
//                            	}
                            	parsedTokens.add(tokens.get(position).getText());
                            	position++;
                            }
                            parsedTokens.add(":");
                            translate.add(parsedTokens);
                            // for(int i = 0; i < 6; i++) is not a thing in Python
                            // for x in range(1, 6):
                            //translates for statement here
                            F = false;
                            break;
                        }
                        case "print":{
                        	parsedTokens.add("print(");
                        	translate.add(parsedTokens);
                            position++;
                            while (!tokens.get(position).getText().equals(")")) {
                            	Translate(tokens);
                            }
                            parsedTokens.add(")");
                            translate.add(parsedTokens);
                            break;
                        }
                        case "println": {
                        	parsedTokens.add("print(");
                        	translate.add(parsedTokens);
                            position++;
                            while (!tokens.get(position).getText().equals(")")) {
                            	Translate(tokens);
                            }
                            parsedTokens.add(")");
                            translate.add(parsedTokens);
                            break;
                        }

                        case "boolean":
                        case "char":
                        case "double":
                        case "int":
                        case "long":
                        case "short":
                        case "String":
                            if(tokens.get(position + 1).getText() == "[" && tokens.get(position + 2).getText() == "]")
                            {
                                //array is being created
                                position+=4;
                                String temp = "";
                                while(!tokens.get(position).getText().equals(";"))
                                {
                                    temp += tokens.get(position).getText();
                                    position++;
                                }
                                temp = temp.replace("{", "[");
                                temp = temp.replace("}", "]");
                                
                                parsedTokens.add(temp);
                                translate.add(parsedTokens);
                                break;
                            }
                            
                            position +=2;
                            
                            while (!tokens.get(position).getText().equals(";")) {
                                parsedTokens.add(tokens.get(position).getText());
                                position++;
                            }

                            translate.add(parsedTokens);
                            break;
                        
                        	// {
//                        	parsedTokens.add(tokens.get(position).getText());
//                        	translate.add(parsedTokens);
//                        	break;
//                            position +=2;
//                            
//                            while (!tokens.get(position).getText().equals(";")) {
//                                parsedTokens.add(tokens.get(position).getText());
//                                position++;
//                            }
//                            translate.add(parsedTokens);
//                            break;
                        	//break;
//                        }
                        //case "else if":{}
                        
                        case "else":
                        {
                        	indent--;
                            parsedTokens.add("else:");
                            translate.add(parsedTokens);
                            indent++;
                            break;
                        }
                        default : {
                        
                        	
                        }
                    }
                    break;
                }

                case OpenC: {
                    indent++;
                    Translate(tokens);
                    break;
                }

                case CloseC: {
                    indent--;
                    return translate;
                }

                case Number: {
                    parsedTokens.add(tokens.get(position).getText());
                    translate.add(parsedTokens);
                    break;
                }
                case Equals: {
                    parsedTokens.add(tokens.get(position).getText());

                    translate.add(parsedTokens);

                    break;
                }
                case Space: {
                	
//                	parsedTokens.add(tokens.get(position).getText());
//                    translate.add(parsedTokens);
                    break;
                }
                case Addition: {
                	//System.out.println(" idk " + stringVariable);
                	int placeHolder = position - 1;
                	while(tokens.get(placeHolder).getType() != tokentype.SemiColon) {
	                	if( (keywords.compare(tokens.get(placeHolder).getText(), keywords.Print) 
	                			|| keywords.compare(tokens.get(placeHolder).getText(), keywords.PRINTLN) )) {
	                		parsedTokens.add(",");
	                        translate.add(parsedTokens);
	                        break;
	                	}
	                	
                		placeHolder--;
                	}
//                	if(tokens.get(position-1).getType()==tokentype.String ||tokens.get(position-2).getType()==tokentype.String || (tokens.get(position-2).getType()==tokentype.Word || (tokens.get(position-1).getType()==tokentype.Word ))) 
//                    {
//                        parsedTokens.add(" , ");
//	                    translate.add(parsedTokens);
//	                    break;
//	            	}
                	
                	//x++
                	if(tokens.get(position-1).getType()==tokentype.Word && tokens.get(position+1).getType()==tokentype.Addition ) {
                		parsedTokens.add("+=");
                		parsedTokens.add("1");
                        translate.add(parsedTokens);
                        position++;
                        break;
                	}
                    break;
                }
                case Unknown: {
                    break;
                }
                case Subtract: {
                	
                	//x--
                	if(tokens.get(position-1).getType()==tokentype.Word && tokens.get(position+1).getType()==tokentype.Subtract ) {
                		parsedTokens.add("-=");
                		parsedTokens.add("1");
                        translate.add(parsedTokens);
                        position++;
                	}
                	
                    break;
                }
                case Multiplication: {
                    break;
                }
                case Division: {
                    break;
                }
                case OpenB: {
                    parsedTokens.add(" = [");
                    
                    if (tokens.get(position + 2).getText().equals(";")) {
                        break;
                    }
                    
                    while (!tokens.get(position).getText().equals("{")) {                       
                        position++;
                            }
                    
                    while (!tokens.get(position).getText().equals("}")) {
                                parsedTokens.add(tokens.get(position).getText());
                                position++;
                            }
                    
                    //int ar[] = { 1,2,3};
                    break;
                }
                case OpenP: {
                    break;
                }
                case CloseP: {
                    if (W) {
                    parsedTokens.add(" ):");
                    translate.add(parsedTokens);
                    }
                    else if (I) {
                    parsedTokens.add(" :");
                    translate.add(parsedTokens);
                    }
                    else if (F) {
                    parsedTokens.add(" :");
                    translate.add(parsedTokens);    
                    }

                    return translate;
                }
                case CloseB: {
                    break;
                }
                case End: {
                    break;
                }
                case SemiColon: {
                    break;
                }
                case Colon: {
                	parsedTokens.add("in");
           		 	translate.add(parsedTokens);
                    break;
                }
                case EqualsC: {
                    break;
                }
                case Word: {

                	if(tokens.get(position-1).getType()!=tokentype.Dot && tokens.get(position+1).getType()!=tokentype.Dot) {
                		 parsedTokens.add(tokens.get(position).getText());
                		 translate.add(parsedTokens);    
                	}
                
                    break;
                }
                case String: {
                	parsedTokens.add(tokens.get(position).getText());
           		 	translate.add(parsedTokens);
                    break;
                }
                case Tab: {
                	parsedTokens.add(tokens.get(position).getText());
           		 	translate.add(parsedTokens);
                    break;
                }
                case Dot: {

                    break;
                }
                case LessThan: {
                	parsedTokens.add(tokens.get(position).getText());
           		 	translate.add(parsedTokens);
                    break;
                }
                case GreaterThan: {
                	parsedTokens.add(tokens.get(position).getText());
           		 	translate.add(parsedTokens);
                    break;
                }
                default:
                    break;
            }
        }
        
        return translate;
    }
}
