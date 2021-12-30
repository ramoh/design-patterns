package com.rajesh.bre;

import java.util.Arrays;
import java.util.List;

class DefaultRule implements Rule {

    private final List<Condition> conditions;
    private final Action action;

    DefaultRule(Action action, Condition... conditions) {
        this.conditions = Arrays.asList(conditions);
        this.action = action;
    }

    @Override
    public void perform(Facts facts) {
        final boolean shoudlExecute = conditions.stream().allMatch(condition -> condition.evaluate(facts));
        if (shoudlExecute) {
            action.execute(facts);
        }
    }

}
