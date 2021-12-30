package com.rajesh.bre;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
