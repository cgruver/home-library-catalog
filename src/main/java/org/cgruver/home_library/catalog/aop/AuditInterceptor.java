package org.cgruver.home_library.catalog.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Audited
@Interceptor
public class AuditInterceptor {
    
    @AroundInvoke
    public Object audit(InvocationContext context) throws Exception {
        System.out.println("Hello before method invocation!");
        Object result = context.proceed();
        System.out.println("Hello after method invocation!");
        return result;
    }
}
