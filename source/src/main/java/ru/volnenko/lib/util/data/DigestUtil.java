package ru.volnenko.lib.util.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by orion on 16.04.15.
 */
public final class DigestUtil {

    public static String getHash(
            @Nullable final String algorithm,
            @Nullable final String value
    ) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (value == null || algorithm == null) {
            return null;
        }
        final MessageDigest md = MessageDigest.getInstance(algorithm);
        final byte[] digest = md.digest(value.getBytes("UTF-8"));
        return digestToString(digest);
    }

    @Nullable
    public static String sha256(@Nullable final String value) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return getHash("SHA-256", value);
    }

    @Nullable
    public static String md5(@Nullable final String value) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return getHash("MD5", value);
    }

    @NotNull
    private static String digestToString(@NotNull final byte[] digest) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final byte anArray : digest) {
            stringBuilder.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
        }
        return stringBuilder.toString();
    }

}
