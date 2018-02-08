package org.jboss.perf.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target( ElementType.METHOD )
public @interface BenchmarkQueryMethod {

    String value();
    
}
