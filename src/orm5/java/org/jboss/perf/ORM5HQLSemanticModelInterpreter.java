package org.jboss.perf;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.HqlParser;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Collections;

public class ORM5HQLSemanticModelInterpreter implements BenchmarkFactory.BenchmarkHQLSemanticModelInterpreter {

    // using a ***STATIC FINAL*** method handle to invoke a private method without a performance penalty
    private static final MethodHandle PARSE = getMethodHandle( QueryTranslatorImpl.class, "parse", boolean.class );

    private SessionFactoryImplementor sessionFactoryImplementor;

    private static MethodHandle getMethodHandle(Class<?> theClass, String methodName, Class<?>... arguments) {
        try {
            Method theMethod = theClass.getDeclaredMethod( methodName, arguments );
            theMethod.setAccessible( true );
            return MethodHandles.lookup().unreflect( theMethod );
        } catch ( NoSuchMethodException | IllegalAccessException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getSemanticModel(String hqlString) {
        try {
            QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl( hqlString, hqlString, Collections.EMPTY_MAP, sessionFactoryImplementor );
            return ((HqlParser) PARSE.invokeExact( queryTranslator, false )).getAST();
        } catch ( Throwable throwable ) {
            throwable.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Object getSemanticModel(String hqlString) {
//        try {
//            HqlParser parser = HqlParser.getInstance( hqlString );
//            parser.statement();
//            return parser.getAST();
//        } catch ( RecognitionException | TokenStreamException e ) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public void configure(SessionFactory sessionFactory) {
        sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;
    }
}
