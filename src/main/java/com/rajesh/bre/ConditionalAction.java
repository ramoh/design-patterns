package com.rajesh.bre;

public interface ConditionalAction {
    boolean evaluate(Facts facts);

    void perform(Facts facts);
}
