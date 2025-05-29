package org.ecommerce.blackfriday.cart.infraestructure.rate.interceptor;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private Bucket buildBucket() {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(20).refillGreedy(20, Duration.ofMinutes(1)))
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String ip = request.getRemoteAddr();
            String appKey = "ip:" + ip;

            HttpSession session = request.getSession(true);
            Bucket bucket = (Bucket) session.getAttribute("cart-" + appKey);
            if (bucket == null) {
                bucket = buildBucket();
                session.setAttribute("cart-" + appKey, bucket);
            }

            if (bucket.tryConsume(1)) {
                System.out.println("Forward");
                return true;
            }

            System.out.println("Too many request..");

            response.setContentType("text/plain");
            response.setStatus(429);
            response.getWriter().append("Too many requests");

            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
