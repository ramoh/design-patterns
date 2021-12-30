package com.rajesh.bre;

class DefaultRule implements Rule {

    private final Condition condition;
    private final Action action;

    DefaultRule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void perform(Facts facts) {
        if (condition.evaluate(facts)) {
            action.execute(facts);
        }
    }

}
