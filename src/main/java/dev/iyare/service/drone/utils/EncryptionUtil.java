package dev.iyare.service.drone.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil
{

    private static byte[] sharedkey =
            {0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11, 0x12, 0x11, 0x0D, 0x0B, 0x07, 0x02, 0x04, 0x08, 0x01, 0x02, 0x03,
                    0x05, 0x07, 0x0B, 0x0D, 0x11};

    private static byte[] sharedvector =
            {0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11};

    public synchronized String encrypt(String plaintext) throws Exception
    {
        Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(EncryptionUtil.sharedkey, "DESede"),
                new IvParameterSpec(EncryptionUtil.sharedvector));
        byte[] encrypted = c.doFinal(plaintext.getBytes("UTF-8"));
        return Base64Util.encodeBytes(encrypted);
    }

    public synchronized String decrypt(String ciphertext) throws Exception
    {
        Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(EncryptionUtil.sharedkey, "DESede"),
                new IvParameterSpec(EncryptionUtil.sharedvector));
        byte[] decrypted = c.doFinal(Base64Util.decode(ciphertext));
        return new String(decrypted, "UTF-8");
    }

//    public static void main(String args[]) throws Exception
//    {
//	Nairame Google map key
//        String mapApiKey = "AIzaSyCr-4OLh48llnp1CL9kK-0xlLGDLlWbGBg"; // UDaOlTXbGB2m8ytL8HpZjK4G1BvHI6sd0i0m+26IvRSxnCI/T5WM2Q==
//        String mapApiKey = "AIzaSyBQeTNl55TNGjf6YJ5DYArk7BRB-XQctao"; // z6c1AT1pN6nU9Xps0Op/UsGVXHQnkHX0MFarwF3xgrPEWDBiMD2XSQ==
//        String mapApiKey = "AIzaSyDZz-DFKnsxDqqTMFG_JCIpHqyKtiiDHHg"; // w74X2JMN4cEc2MtcMBIexAoFAccB4ll0Ag7EfqS9I6IulkDRGRcmEA==
    // /nimwi/ = hU4EfgKCg6k=
    // /.nimwi/ = 6Od/zqd3Hb5L/XPK8IgGCA==
//        EncryptionUtil se = new EncryptionUtil();
//        String plainString = mapApiKey;
//        System.out.println(se.encrypt(plainString));
//		System.out.println(se.decrypt("hU4EfgKCg6k=")); // OXNj845+CT+aoyfDzLUGQiJfojKUGaPuDBGfjAv356E=
//		System.out.println(se.decrypt("6Od/zqd3Hb5L/XPK8IgGCA==")); // OXNj845+CT+aoyfDzLUGQiJfojKUGaPuDBGfjAv356E=

//		String plainString = "oghon8@gmail.com+193047";//OXNj845+CT+aoyfDzLUGQox1DPCbPDTZhEP9Y4bR3NQ=
//		System.out.println(se.encrypt(plainString));

//		System.out.println(se.decrypt(
//				"O8t8c1tfDuC3z98b98nB5Q5R3pTE5yyOuaEteWzajOX/ycPAhaz07TSj1j2CPTEVaAjRPHMRpwtJ/86amC7JukqUJTn6hFj547e2Z+hxC92yijrJyia1sG4zKIiWrgRo"));
    // String plainString = "{\"Referenceid\":
    // \"00025170428100025103\",\"RequestType\": \"104\",\"Translocation\":
    // null,\"nuban\": \"0023923413\",\"otp\":\"301571\"}";
//		String encryptedString = "ycj34YaNloZ/jpl986r+byeDzqLDTw107qYgoTViICKk1yF5pCJrJu6ynLoZPwXCnAgxHuFrL5OR4bOUcoyWwerlgeuxUe5a7xvT67kKXwd/DUwN3pklvZTDqY3oWtW23EBbmXs42dXhJdDMS5pDpD7LXB1nsHvz";
//		System.out.println(se.decrypt(encryptedString));
//		System.out.println(se.encrypt(plainString));
//    }
}