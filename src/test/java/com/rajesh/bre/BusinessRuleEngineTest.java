package com.rajesh.bre;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BusinessRuleEngineTest {

    @Test
    public void shouldHaveNoRulesInitially() {
        final Facts mockedFacts = Mockito.mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockedFacts);

        Assert.assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddTwoRules() {
        final Facts mockedFacts = Mockito.mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockedFacts);

        businessRuleEngine.addRules((facts) -> {
        });
        businessRuleEngine.addRules((facts) -> {
        });

        Assert.assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldExecuteRuleWithFacts() {
        final Rule mockRule = Mockito.mock(Rule.class);
        final Facts mockedFacts = Mockito.mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockedFacts);

        businessRuleEngine.addRules(mockRule);
        businessRuleEngine.run();
        Mockito.verify(mockRule).perform(mockedFacts);
    }

}
