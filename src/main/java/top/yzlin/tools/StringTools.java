package top.yzlin.tools;

public final class StringTools {

    public static char[] StringToUpperCaseFirstChar(String str) {
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '.') {
                chars[i + 1] = Character.toUpperCase(chars[i + 1]);
                return chars;
            }
        }
        chars[0] = Character.toUpperCase(chars[0]);
        return chars;
    }
}
