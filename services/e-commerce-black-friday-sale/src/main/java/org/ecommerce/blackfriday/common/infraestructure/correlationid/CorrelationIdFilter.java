package org.ecommerce.blackfriday.common.infraestructure.correlationid;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CartLogger.info("[Filter] (step 1) init");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String correlationId = httpServletRequest.getHeader(CORRELATION_ID_HEADER);

        if (Objects.isNull(correlationId)) {
            CartLogger.info("[Filter] (step 1.1) generate correlationId");
            correlationId = UUID.randomUUID().toString();
        }

        CartLogger.info("[Filter] (step 2) get correlationId: {}", correlationId);

        MDC.put("correlationId", correlationId);
        CartLogger.info("[Filter] (step 3) assign correlationId: {}", correlationId);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove("correlationId");
        }
    }
}
