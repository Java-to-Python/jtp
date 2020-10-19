
public class Lexer {

    public static String lexerL;
    public static int position;
    public int lineNumber = 0;
    //public static char current;

    //Lexer object
    public Lexer(String text) {
        lexerL = text.replace("public ", "");
        lexerL = lexerL.replace("private ", "");
        lexerL = lexerL.replace("static ", "");
        lexerL = lexerL.replace("void ", "");
        lexerL = lexerL.replace("static ", "");

        lexerL = text;
        position = 0;
    }

    //Returns the current character
    char getCurrent() {

        if (position >= lexerL.length()) {
            return '\0';
        }
        return lexerL.charAt(position);
    }

    char peekFront() {
        return lexerL.charAt(position++);
    }

    char peekBack() {
        return lexerL.charAt(position--);
    }

    //changes position to the next character
    public void next() {

        position++;
    }

    public Token nextToken() {

        int start = position;

        if (position >= lexerL.length()) {
            return new Token(tokentype.End, position, "\u0000");
        }

        //Checks for Strings
        if (getCurrent() == '"') {

            //iterate through the whole number
            do {
                next();
            } while (getCurrent() != '"');
            next();

            String identity = lexerL.substring(start, position);

            return new Token(tokentype.String, start, identity);
        }

        //Checks for words
        if (Character.isLetter(getCurrent())) {

            //iterate through the whole number
            while (Character.isLetter(getCurrent())) {
                next();
            }

            String identity = lexerL.substring(start, position);
            //check for keywords
            if (keywords.compare(identity)) {
                return new Token(tokentype.Keyword, start, identity);
            }
            return new Token(tokentype.Word, start, identity);
        }

        //Checks for numbers
        if (Character.isDigit(getCurrent()) || (((getCurrent() == '.')) && (Character.isDigit(peekFront()) || Character.isDigit(peekBack())))) {
            StringBuilder textBuffer = new StringBuilder();

            //iterate through the whole number
            while (Character.isDigit(getCurrent()) || (getCurrent() == '.')) {
                textBuffer.append(getCurrent());
                next();
            }
            textBuffer.toString();
            String identity = lexerL.substring(start, position);
            return new Token(tokentype.Number, start, identity);
        }

        //Checks for spaces
        if (Character.isWhitespace(getCurrent())) {

            //iterate through all spaces
            while (Character.isWhitespace(getCurrent())) {
                next();
            }

            String whiteSpace = lexerL.substring(start, position);
            if (whiteSpace.contains("\n")) {
                return new Token(tokentype.NewLine, start, "\n");
            }
            if (whiteSpace.contains("\t")) {
                return new Token(tokentype.Tab, start, "\t");
            }
            return new Token(tokentype.Space, start, whiteSpace);
        }

        //Checks for symbols
        switch (getCurrent()) {
            case '=':
                return new Token(tokentype.Equals, position++, "=");
            case '+':
                return new Token(tokentype.Addition, position++, "+");
            case '-':
                return new Token(tokentype.Subtraction, position++, "-");
            case '*':
                return new Token(tokentype.Multiplication, position++, "*");
            case '.':
                return new Token(tokentype.Dot, position++, ".");
            case '/':
                return new Token(tokentype.Division, position++, "/");
            case '(':
                return new Token(tokentype.OpenP, position++, "(");
            case ')':
                return new Token(tokentype.CloseP, position++, ")");
            case '{':
                return new Token(tokentype.OpenC, position++, "{");
            case '}':
                return new Token(tokentype.CloseC, position++, "}");
            case '[':
                return new Token(tokentype.OpenB, position++, "[");
            case ']':
                return new Token(tokentype.CloseB, position++, "]");
            case ';':
                return new Token(tokentype.SemiColon, position++, ";");
            case '<':
                return new Token(tokentype.LessThen, position++, "<");
            case '>':
                return new Token(tokentype.GreaterThen, position++, ">");
            default:
                return new Token(tokentype.Unknown, position++, lexerL.substring(start, position));
        }
    }

}
