package sample;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by luchfilip on 2/27/16.
 */
public class MD5Checksum {

    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        fis.close();
        return complete.digest();
    }

    // convert a byte array to a HEX string
    public static String getMD5Checksum(String filename) {
        byte[] b = new byte[0];
        try {
            b = createChecksum(filename);
            String result = "";
            for (byte aB : b) {
                result += Integer.toString((aB & 0xff) + 0x100, 16).substring(1);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
