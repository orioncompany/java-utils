package com.orioncompany.lib.util.object;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Created by orion on 16.04.15.
 */
public final class ObjectUtil {

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> T objectDeepCopy(@NotNull final T object) throws IOException, ClassNotFoundException {
        final String value = objectToString(object);
        return (T) objectFromString(value);
    }

    @NotNull
    public static String objectToString(@NotNull final Object object) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        final byte[] byteArrayBase64 = Base64.encodeBase64(byteArray);
        return new String(byteArrayBase64);
    }

    @NotNull
    public static Object objectFromString(@NotNull final String value) throws IOException, ClassNotFoundException {
        final byte[] objectBytes = Base64.decodeBase64(value.getBytes());
        final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(objectBytes));
        return objectInputStream.readObject();
    }

    @NotNull
    private static XStream getXStream() {
        @NotNull
        final XStream xStream = new XStream() {
            @Override
            protected MapperWrapper wrapMapper(@NotNull final MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        return definedIn != Object.class && super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        return xStream;
    }

    @NotNull
    public static String objectToXML(@NotNull final Object object) {
        return getXStream().toXML(object);
    }

    @NotNull
    public static Object objectFromXML(@NotNull final String xml) {
        return getXStream().fromXML(xml);
    }

}
