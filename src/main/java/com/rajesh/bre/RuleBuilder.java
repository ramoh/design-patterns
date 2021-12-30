package com.rajesh.bre;

public class RuleBuilder {
    private Condition condition;

    private RuleBuilder(final Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilder when(final Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(final Action action) {
        return new DefaultRule(this.condition, action);
    }

}
