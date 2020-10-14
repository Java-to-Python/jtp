public class Token{
		tokentype type;
		int position;
		String text;
		int value;

		public Token(tokentype type, int position, String text, int value){
			this.type=type;
			this.position=position;
			this.text=text;
			this.value=value;
		}
		
		public Token() {
			// TODO Auto-generated constructor stub
		}

		public tokentype getType() {return type;}
		public int getPosition(){return position;}
		public String getText() {return text;}
	}