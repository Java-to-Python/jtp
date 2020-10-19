
import java.util.ArrayList;

public class Java_To_Python_Translator {

    public static String translate(String input) {
        ArrayList<ArrayList<String>> translated = new ArrayList<>();
        ArrayList<Token> tokens = new ArrayList<>();

        Lexer lexer = new Lexer(input);
        String output = "";
        int index = 0;

        Token token = new Token(tokentype.Unknown, 0, "");
        while (index != input.lastIndexOf("}")) {
            token = lexer.nextToken();

            if (token.getType() == tokentype.End) {
                break;
            }

            tokens.add(token);

            System.out.println();
            index++;
        }

        translated = Interpreter.Translate(tokens);

        for (int i = 0; i < translated.size(); i++) {
            for (int j = 0; j < translated.get(i).size(); j++) {
                output += translated.get(i).get(j).toString();
            }

        }
        return output;
    }
}
//
