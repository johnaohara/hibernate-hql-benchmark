package org.jboss.perf;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.sqm.produce.internal.hql.HqlParseTreeBuilder;
import org.hibernate.query.sqm.produce.internal.hql.SemanticQueryBuilder;
import org.hibernate.query.sqm.produce.internal.hql.grammar.HqlParser;

public class ORM6HQLSemanticModelInterpreter implements BenchmarkFactory.BenchmarkHQLSemanticModelInterpreter {

    private SessionFactoryImplementor sessionFactoryImplementor;

    @Override
    public Object getSemanticModel(String hqlString) {
        HqlParser.StatementContext statementContext = HqlParseTreeBuilder.INSTANCE.parseHql( hqlString ).statement();
        return SemanticQueryBuilder.buildSemanticModel( statementContext, sessionFactoryImplementor );
    }

    @Override
    public void configure(SessionFactory sessionFactory) {
        sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;
    }
}
