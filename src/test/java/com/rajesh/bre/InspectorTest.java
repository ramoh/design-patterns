package com.rajesh.bre;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class InspectorTest {

    @Test
    public void shouldInspectOneConditionAndEvaluateTrue() {
        final Facts facts = new Facts();
        facts.addFact("jobTitle", "CEO");

        final ConditionalAction conditionalAction = new JobTitleCondition();
        final Inspector inspector = new Inspector(conditionalAction);

        final List<Report> reportList = inspector.inspect(facts);

        Assert.assertThat(reportList, Matchers.hasSize(1));
        Assert.assertEquals(true, reportList.get(0).isPositive());

    }

    private static class JobTitleCondition implements ConditionalAction {

        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFact("jobTitle"));
        }

        @Override
        public void perform(Facts facts) {
            throw new UnsupportedOperationException();

        }
    }
}
