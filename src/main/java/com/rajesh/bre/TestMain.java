package com.rajesh.bre;

public class TestMain {

    public static void main(String[] args) {

        var env = new Facts();
        env.addFact("name", "Rajesh");
        env.addFact("jobTitle", "CEO");

        final var businessRuleEngine = new BusinessRuleEngine(env);

        final Rule ruleSendMailToSale = RuleBuilder.when(facts -> "CEO".equals(facts.getFact("jobTitle")))
                .andWhen(facts -> "Rajesh".equals(facts.getFact("name")))
                .then(facts -> {
                    var name = facts.getFact("name");
                    System.out.println("Relevant Customer!!!" + name);
                });

        businessRuleEngine.addRules(ruleSendMailToSale);
        businessRuleEngine.run();
    }
}
