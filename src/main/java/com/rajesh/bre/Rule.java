package com.rajesh.bre;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}
