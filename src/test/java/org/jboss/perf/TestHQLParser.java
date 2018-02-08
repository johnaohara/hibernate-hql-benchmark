package org.jboss.perf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHQLParser {

    private BenchmarkFactory.BenchmarkHQLParserTree hqlParser;

    @Before
    public void setup() {
        hqlParser = BenchmarkFactory.buildHqlParser();
        hqlParser.configure( null );
    }

    @Test
    public void testHqlParser() {
        Assert.assertNotNull( hqlParser.getHQLParser( "SELECT a FROM Animal a" ) );
    }
}
