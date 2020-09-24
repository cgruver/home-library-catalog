package org.cgruver.home_library.catalog.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

@Audited
@Interceptor
public class AuditInterceptor {
    
    @AroundInvoke
    public Object audit(InvocationContext context) throws Exception {
        final Logger LOG = Logger.getLogger(AuditInterceptor.class);
        LOG.info("Hello before method invocation!");
        Object result = context.proceed();
        LOG.info("Hello after method invocation!");
        return result;
    }
}
