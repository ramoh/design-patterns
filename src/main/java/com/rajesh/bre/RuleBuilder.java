package com.rajesh.bre;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
    private List<Condition> conditions = new ArrayList<>();

    private RuleBuilder(final Condition condition) {
        this.conditions.add(condition);
    }

    public static RuleBuilder when(final Condition condition) {
        return new RuleBuilder(condition);
    }

    public RuleBuilder andWhen(final Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public Rule then(final Action action) {
        return new DefaultRule(action, this.conditions.toArray(new Condition[this.conditions.size()]));
    }

}
