package top.yzlin.tools;

public final class StringTools {

    public static char[] StringToUpperCaseFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return chars;
    }
}
