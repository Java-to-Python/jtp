
import java.util.ArrayList;

public class Interpreter {

    public static boolean W = false;
    public static boolean I = false;
    public static boolean F = false;
    public static int indent = 0;
    public static int position = -1;
    public static ArrayList<ArrayList<String>> translate = new ArrayList<>();

    public static ArrayList Translate(ArrayList<Token> tokens) {
        while (position < tokens.size()) {
            position++;
            ArrayList<String> parsedTokens = new ArrayList<>();
            if (!W || !I || !F) {
                for (int j = 0; j < indent; j++) {
                    parsedTokens.add("\t");
                }
            }

            if (position >= tokens.size()) {
                return translate;
            }
            switch (tokens.get(position).getType()) {
                case NewLine: {
                    try {
                        translate.get(translate.size() - 1).add("\n");
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
                            parsedTokens.add("def main():");
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
                            parsedTokens.add(" :");
                            translate.add(parsedTokens);
                            I = false;
                            break;
                        }

                        case "while": {
                            W = true;
                            parsedTokens.add("while (");

                            while (!tokens.get(position).getText().equals(")")) {
                                Translate(tokens);
                            }
                            parsedTokens.add("):");
                            translate.add(parsedTokens);
                            W = false;
                            break;
                        }
                        case "for": {
                            F = true;

                            //translates for statement here
                            F = false;
                            break;
                        }

                        case "println": {
                            parsedTokens.add("print");
                            position++;
                            while (!tokens.get(position).getText().equals(";")) {
                                parsedTokens.add(tokens.get(position).getText());
                                position++;
                            }
                            translate.add(parsedTokens);
                            break;
                        }

                        case "boolean":
                        case "char":
                        case "double":
                        case "int":
                        case "long":
                        case "short":
                        case "String": {
                            position += 2;

                            while (!tokens.get(position).getText().equals(";")) {
                                parsedTokens.add(tokens.get(position).getText());
                                position++;
                            }
                            parsedTokens.add(tokens.get(position).getText());

                            translate.add(parsedTokens);
                            break;
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
                    break;
                }
                case Addition: {
                    break;
                }
                case Unknown: {
                    break;
                }
                case Subtraction: {
                    break;
                }
                case Multiplication: {
                    break;
                }
                case Division: {
                    break;
                }
                case OpenB: {
                    break;
                }
                case OpenP: {
                    break;
                }
                case CloseP: {
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
                case EqualsC: {
                    break;
                }
                case Word: {
                    break;
                }
                case String: {
                    break;
                }
                case Tab: {
                    break;
                }
                case Dot: {
                    break;
                }
                case LessThen: {
                    break;
                }
                case GreaterThen: {
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
