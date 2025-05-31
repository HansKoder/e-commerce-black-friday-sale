package org.ecommerce.blackfriday.cart.infraestructure.rate.interceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private Bucket buildBucket() {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(20).refillGreedy(20, Duration.ofMinutes(1))).build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String ip = request.getRemoteAddr();
            String path = normalizePath(request.getRequestURI());
            String rateLimitKey = "cart-" + ip + "-" + path;

            CartLogger.info("Request Interceptor, IP {}, Path {}, rateLimitKey {}", ip, path, rateLimitKey);

            HttpSession session = request.getSession(true);
            Bucket bucket = (Bucket) session.getAttribute(rateLimitKey);
            if (bucket == null) {
                bucket = buildBucketForPath(path);
                session.setAttribute(rateLimitKey, bucket);
            }

            if (bucket.tryConsume(1)) {
                CartLogger.info("Limit is allowed");
                return true;
            }

            CartLogger.error("Too many request");

            response.setContentType("text/plain");
            response.setStatus(429);
            response.getWriter().append("Too many requests");

            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private String normalizePath(String path) {
        if (path.matches("^/api/v1/cart/customer/[a-f0-9\\-]+$")) {
            return "/api/v1/cart/customer/{customerId}";
        }
        return path;
    }

    private Bucket buildBucketForPath(String path) {
        switch(path) {
            case "/api/v1/cart/customer/{customerId}":
                return Bucket.builder()
                        .addLimit(Bandwidth.simple(30, Duration.ofMinutes(1)))
                        .build();
            case "/api/v1/cart/items/save":
                return Bucket.builder()
                        .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
                        .build();
            default:
                return Bucket.builder()
                        .addLimit(Bandwidth.simple(20, Duration.ofMinutes(1)))
                        .build();
        }
    }

}
