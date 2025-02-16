//Included class Sessionid to package gatlingdemostore
package gatlingdemostore;


//Imported needed Java-lib for execution
import java.util.concurrent.ThreadLocalRandom;



//Created public class SessionId
public class SessionId {


    //Announced variable CANDIDATES with array convertion
    private static final char[] CANDIDATES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();


    //Announced variable SESSION_ID_LENGTH
    private static final int SESSION_ID_LENGTH = 10;


    //Created function which returns random SessionID
    static String random() {
        StringBuilder buffer = new StringBuilder(SESSION_ID_LENGTH);
        for (int i = 0; i < SESSION_ID_LENGTH; i++) {
            buffer.append(CANDIDATES[ThreadLocalRandom.current().nextInt(CANDIDATES.length)]);
        }
        return buffer.toString();
    }
}
