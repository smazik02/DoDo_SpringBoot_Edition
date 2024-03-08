package com.stanley.dodospring.mappers;

public interface Mapper<A, B> {
    B mapTo(A a);

    A mapFrom(B b);
}