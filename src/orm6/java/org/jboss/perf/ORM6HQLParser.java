package org.jboss.perf;

import org.hibernate.query.sqm.produce.internal.hql.HqlParseTreeBuilder;

public class ORM6HQLParser implements BenchmarkFactory.BenchmarkHQLParserTree {

    @Override
    public Object getHQLParser(String hqlString) {
        return HqlParseTreeBuilder.INSTANCE.parseHql( hqlString );
    }
}
