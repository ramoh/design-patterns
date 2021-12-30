package com.rajesh.bre;

public class Report {

    private final ConditionalAction conditionalAction;
    private final Facts facts;
    private final boolean isPositive;

    public Report(final ConditionalAction conditionalAction, final Facts facts, final boolean isTrue) {
        this.conditionalAction = conditionalAction;
        this.facts = facts;
        this.isPositive = isTrue;
    }

    public ConditionalAction getConditionalAction() {
        return conditionalAction;
    }

    public Facts getFacts() {
        return facts;
    }

    public boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        return "Report{"
                + "conditionalAction=" + this.conditionalAction
                + ", facts=" + facts
                + ", result=" + isPositive
                + "}";
    }

}
