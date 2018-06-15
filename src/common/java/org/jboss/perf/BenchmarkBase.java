package org.jboss.perf;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.perf.annotations.BenchmarkQuery;
import org.jboss.perf.annotations.BenchmarkQueryMethod;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;


// --- //

@BenchmarkQuery( name = "simpleSelect", query = "select a from Animal a" )
@BenchmarkQuery( name = "simpleSelect_multiple", query = "select a.serialNumber, a.mother, a.father, a.description, a.zoo from Animal a" )
@BenchmarkQuery( name = "simpleWhere", query = "select a from Animal a where a.serialNumber = '1337'" )
@BenchmarkQuery( name = "simpleWhere_multiple", query = "select a from Animal a where a.serialNumber = '1337' and a.zoo.address.city = 'London' and a.zoo.address.country = 'US'" )
public abstract class BenchmarkBase {

    private static final Logger logger = Logger.getLogger(BenchmarkBase.class.getName());
    // --- benchmarks are auto generated based on this methods and queries defined above

    //@BenchmarkQueryMethod( "Parser" )
    public Object getHQLParser(String query, BenchmarkState benchmarkState) {
        return benchmarkState.getHqlParser().getHQLParser( query );
    }

    @BenchmarkQueryMethod( "SemanticModel" )
    public Object getSemanticModel(String query, BenchmarkState benchmarkState) {
        return benchmarkState.getHQLInterpreter().getSemanticModel( query );
    }

    // --- //

    @State( Scope.Benchmark )
    public static class BenchmarkState {

        private BenchmarkFactory.BenchmarkHQLParserTree hqlParser;
        private BenchmarkFactory.BenchmarkHQLSemanticModelInterpreter hqlInterpreter;

        private SessionFactory sessionFactory;
        private Session session;

        @Setup
        public void setup() {
            try {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
                session = sessionFactory.openSession();

                hqlParser = BenchmarkFactory.buildHqlParser();
                hqlParser.configure( sessionFactory );

                hqlInterpreter = BenchmarkFactory.buildHqlSemanticModelInterpreter();
                hqlInterpreter.configure( sessionFactory );

                logger.info( "Running benchmark with HQL Parser: " + hqlParser.getClass().getName() );
                logger.info( "Running benchmark with HQL Interpreter: " + hqlInterpreter.getClass().getName() );
            } catch ( Throwable t ) {
                t.printStackTrace();
                throw t;
            }
        }

        public Session getSession() {
            return session;
        }

        public BenchmarkFactory.BenchmarkHQLParserTree getHqlParser() {
            return hqlParser;
        }

        public BenchmarkFactory.BenchmarkHQLSemanticModelInterpreter getHQLInterpreter() {
            return hqlInterpreter;
        }

        @TearDown
        public void shutdown() {
            try {
                session.close();
                sessionFactory.close();
            } catch ( Throwable t ) {
                t.printStackTrace();
                throw t;
            }
        }
    }
}
