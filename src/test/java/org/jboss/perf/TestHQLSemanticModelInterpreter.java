package org.jboss.perf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHQLSemanticModelInterpreter {

    private BenchmarkFactory.BenchmarkHQLSemanticModelInterpreter hqlInterpreter;

    @Before
    public void setup() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

            try ( Session session = sessionFactory.openSession() ) {
                session.clear();
            }

            hqlInterpreter = BenchmarkFactory.buildHqlSemanticModelInterpreter();
            hqlInterpreter.configure( sessionFactory );
        } catch ( Throwable t ) {
            t.printStackTrace();
        }
    }

    @Test
    public void testHqlInterpreter() {
        Assert.assertNotNull( hqlInterpreter.getSemanticModel( "SELECT a FROM Animal a" ) );
    }
}
