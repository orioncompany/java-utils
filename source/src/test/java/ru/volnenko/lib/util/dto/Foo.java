package ru.volnenko.lib.util.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Created by orion on 16.04.15.
 */
public class Foo implements Serializable {

    @Nullable
    public String a = "";

    @Nullable
    public String b = "";

    public Foo() {
    }

    public Foo(@Nullable final String a, @Nullable final String b) {
        this.a = a;
        this.b = b;
    }
}
