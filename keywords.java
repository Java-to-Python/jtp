public enum keywords {
    ABSTRACT, ASSERT, BOOLEAN, BREAK, BYTE, CASE,
    CATCH, CHAR, CLASS, CONST, CONTINUE, DEFAULT,
    DOUBLE, DO, ELSE, ENUM, EXTENDS, FALSE,
    FINAL, FINALLY, FLOAT, FOR, GOTO, IF,
    IMPLEMENTS, IMPORT, INSTANCEOF, INT, INTERFACE, LONG,
    MAIN, NATIVE, NEW, NULL, PACKAGE, PRINTLN, PRIVATE, PROTECTED,
    PUBLIC, RETURN, SHORT, STATIC, STRING, STRICTFP, SUPER,
    SWITCH, SYNCHRONIZED, THIS, THROW, THROWS, TRANSIENT,
    TRUE, TRY, VOID, VOLATILE, WHILE;

    public static boolean compare(String c) {
        keywords[] allk = keywords.values();
        for (keywords k : allk) {
            if (k.toString().equalsIgnoreCase(c)) {
                return true;
            }
        }
        return false;
    }
}