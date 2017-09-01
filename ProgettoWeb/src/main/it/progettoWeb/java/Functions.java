
/**
 *
 * @author mattia
 */

import java.util.Locale;

public class Functions {
    public static String reverse( String text ) {
        return new StringBuilder( text ).reverse().toString();
    }

    public static int numVowels( String text ) {
        String vowels = "aeiouAEIOU";
        int result = 0;
        for( int i = 0; i < text.length(); i++ ) {
            if( vowels.indexOf( text.charAt( i ) ) != -1 ) {
                result++;
            }
        }
        return result;
    }

    public static String caps( String text ) {
        return text.toUpperCase(Locale.ENGLISH);
    }
}
