package com.rajesh.bre;

public class TestMain {

    public static void main(String[] args) {

        Rule ruleSendEmailToSalesWhenCEO = RuleBuilder.when(facts -> "CEO".equals(facts.getFact("jobTitle")))
                .then(facts -> {
                    var name = facts.getFact("name");
                    System.out.println("Sending mail to sales@company to follow up " + name);
                });
        Facts env = new Facts();
        env.addFact("name", "Rajesh Mohanty");
        env.addFact("jobTitle", "CEO");

        ruleSendEmailToSalesWhenCEO.perform(env);
    }
}
