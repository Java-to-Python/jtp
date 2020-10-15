public enum keywords{
		PUBLIC, CLASS, STATIC, VOID, INT, STRING, FOR ;
		
		public static boolean compare(String c) {
			keywords[] allk =keywords.values();
			for(keywords k : allk) {
				if(k.toString().equalsIgnoreCase(c)) {
					return true;
				}
			}
			return false;
		}
	}