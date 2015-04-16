package com.orioncompany.lib.util.object;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Base64;

/**
 * Created by orion on 16.04.15.
 */
public class ObjectUtil {

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> T objectDeepCopy (
            @NotNull final T o
    ) throws IOException, ClassNotFoundException {
        final String value = objectToString(o);
        return (T) stringToObject(value);
    }

    @NotNull
    public static String objectToString(
            @NotNull final Object o
    ) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();
        objectOutputStream.close();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        final byte[] byteArrayBase64 = Base64.getEncoder().encode(byteArray);
        return new String(byteArrayBase64);
    }

    @NotNull
    public static Object stringToObject(
            @NotNull final String value
    ) throws IOException, ClassNotFoundException {
        final byte[] objectBytes = Base64.getDecoder().decode(value.getBytes());
        final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(objectBytes));
        return objectInputStream.readObject();
    }


}
