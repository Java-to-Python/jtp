public class Lexer{
		
		public static String lexerL;
		public static int position;
		public int lineNumber=0;
		//public static char current;
		
		//Lexer object
		public Lexer(String text) {
			lexerL=text;
			position=0;
		}		
		
		//Returns the current character
		char getCurrent(){		
			
			if(position>=lexerL.length()) {
				return '\0';
			}
			return lexerL.charAt(position);
		}
		
		//changes position to the next character
		public void next() {
			
			position++;
		}		
		
		public Token nextToken() {
			
			int start = position;
			
			if(position>=lexerL.length()) {
				return new Token(tokentype.End, position, "\u0000", 0);
			}
			
			//Checks for Strings
			if(getCurrent()=='"'){
				
				//iterate through the whole number
				do {
					next();}while(getCurrent()!='"');
				next();
				
				String identity = lexerL.substring(start, position);
				
				return new Token(tokentype.String, start, identity, 0);
			}
					
			//Checks for words
			if(Character.isLetter(getCurrent())){
				
				//iterate through the whole number
				while(Character.isLetter(getCurrent())){
					next();}
				
				String identity = lexerL.substring(start, position);
				//check for keywords
				if(keywords.compare(identity)) {
					return new Token(tokentype.Keyword, start, identity, 0);
				}
				return new Token(tokentype.Word, start, identity, 0);
			}
			
			//Checks for numbers
			if(Character.isDigit(getCurrent())){
				
				//iterate through the whole number
				while(Character.isDigit(getCurrent())){
					next();}
				
				String identity = lexerL.substring(start, position);
				int value = Integer.parseInt(identity);
				return new Token(tokentype.Number, start, identity, value);
			}
			
			//Checks for spaces
			if(Character.isWhitespace(getCurrent())){
				
				//iterate through all spaces
				while(Character.isWhitespace(getCurrent())) {next();}

				String whiteSpace = lexerL.substring(start, position);	
				if(whiteSpace.contains("\n")) {return new Token(tokentype.NewLine, start, "\n", 0);}
				if(whiteSpace.contains("\t")) {return new Token(tokentype.Tab, start, "\t", 0);}
				return new Token(tokentype.Space, start, whiteSpace, 0);
			}
			
			//Checks for symbols
			switch (getCurrent()) {
				case '=':
					return new Token(tokentype.Equals, position++, "=", 0);
				case '+':
					return new Token(tokentype.Addition, position++, "+", 0);
				case '-':
					return new Token(tokentype.Subtraction, position++, "-", 0);
				case '*':
					return new Token(tokentype.Multiplication, position++, "*", 0);
				case '.':
					return new Token(tokentype.Dot, position++, ".", 0);
				case '/':
					return new Token(tokentype.Division, position++, "/", 0);
				case '(':
					return new Token(tokentype.OpenP, position++, "(", 0);
				case ')':
					return new Token(tokentype.CloseP, position++, ")", 0);
				case '{':
					return new Token(tokentype.OpenC, position++, "{", 0);
				case '}':
					return new Token(tokentype.CloseC, position++, "}", 0);
				case '[':
					return new Token(tokentype.OpenB, position++, "[", 0);
				case ']':
					return new Token(tokentype.CloseB, position++, "]", 0);
				case ';':
					return new Token(tokentype.SemiColon, position++, ";", 0);
				default:
					return new Token(tokentype.Unknown, position++, lexerL.substring(start, position), 0);	
			}		
		}
	}