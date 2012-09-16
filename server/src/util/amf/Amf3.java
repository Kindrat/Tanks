// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Amf3.java

package util.amf;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import flex.messaging.io.amf.Amf3Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public final class Amf3
{
    private static SerializationContext context;
    private static Amf3Output amfOut;
    private static Amf3Input amfIn;
    private static ByteArrayInputStream byIn;
    private static ByteArrayOutputStream byOut;

    static
    {
        context = SerializationContext.getSerializationContext();
        context.instantiateTypes = true;
        context.supportRemoteClass = true;
        amfIn = new Amf3Input(context);
        amfOut = new Amf3Output(context);
    }

    public Amf3()
    {
    }

    public static byte[] encode(Object obj)
    {
        SerializationContext context = SerializationContext.getSerializationContext();
        context.instantiateTypes = true;

        Amf3Output amfOut = new Amf3Output(context);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        amfOut.setOutputStream(baos);
        try
        {
            amfOut.writeObject(obj);
            amfOut.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public static Object decode(byte[] byteObj)
    {
        Object obj = new Object();

        InputStream is = new ByteArrayInputStream(byteObj);

        SerializationContext context = SerializationContext.getSerializationContext();
        context.supportRemoteClass = true;
        context.instantiateTypes = true;

        Amf3Input amfIn = new Amf3Input(context);
        amfIn.setInputStream(is);

        amfIn.reset();

        try
        {
            obj = amfIn.readObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public static Object[] decodeArray(byte[] byteObj)
    {
        ArrayList<Object> objects = new ArrayList<>();

        InputStream is = new ByteArrayInputStream(byteObj);

        SerializationContext context = SerializationContext.getSerializationContext();
        context.supportRemoteClass = true;
        context.instantiateTypes = true;

        Amf3Input amfIn = new Amf3Input(context);
        amfIn.setInputStream(is);

        amfIn.reset();

        try
        {
            while (amfIn.available() > 0) {
                objects.add(amfIn.readObject());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return objects.toArray();
    }

    private static String getMetric(int in)
    {
        return (in + " bytes");
    }

}
