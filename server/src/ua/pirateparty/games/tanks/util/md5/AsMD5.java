package ua.pirateparty.games.tanks.util.md5;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.security.MessageDigest;

public class AsMD5
{

    public AsMD5()
    {
    }

    public static String encode(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte bytes[] = md.digest(password.getBytes());
            return HexBin.encode(bytes).toLowerCase();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
