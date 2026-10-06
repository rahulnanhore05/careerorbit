package io.rahulnanhore.config;

import io.rahulnanhore.jwt.JwtUtil;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

@Configuration
public class RouteConfig {

    private final JwtUtil jwtUtil;

    public RouteConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        return GatewayRouterFunctions
                .route("auth-routes")
                .route(RequestPredicates.path("/api/v1/auth/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-user-service"))
                .before(this::jwtFilter)
                .before(request -> requireRole(request, "ROLE_ADMIN"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> adminRoutes(){
        return GatewayRouterFunctions
                .route("admin-routes")
                .route(RequestPredicates.path("/api/v1/admin/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-user-service"))
                .before(this::jwtFilter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes(){
        return GatewayRouterFunctions
                .route("user-routes")
                .route(RequestPredicates.path("/api/v1/users/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-user-service"))
                .before(this::jwtFilter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> companyRoutes(){
        return GatewayRouterFunctions
                .route("company-routes")
                .route(RequestPredicates.path("/api/v1/companies/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-company-service"))
                .before(this::jwtFilter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> jobRoutes(){
        return GatewayRouterFunctions
                .route("job-routes")
                .route(RequestPredicates.path("/api/v1/jobs/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-job-service"))
                .before(this::jwtFilter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> resumeRoutes(){
        return GatewayRouterFunctions
                .route("resume-routes")
                .route(RequestPredicates.path("/api/v1/resumes/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-resume-service"))
                .before(this::jwtFilter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> applicationRoutes(){
        return GatewayRouterFunctions
                .route("application-routes")
                .route(RequestPredicates.path("/api/v1/applications/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-application-service"))
                .before(this::jwtFilter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> aiRoutes(){
        return GatewayRouterFunctions
                .route("ai-routes")
                .route(RequestPredicates.path("/api/v1/ai/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("careerorbit-ai-service"))
                .before(this::jwtFilter)
                .build();
    }

    private ServerRequest jwtFilter(ServerRequest request) {
        String authorization = request.headers().firstHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }

        String token = authorization.substring(7);
        if (!jwtUtil.isValid(token)){
            throw new RuntimeException("Invalid token");
        }

        String id = jwtUtil.extractId(token);
        String role = jwtUtil.extractRole(token);

        return ServerRequest
                .from(request)
                .header("X-User-Id", id)
                .header("X-User-Role", role)
                .build();
    }

    private ServerRequest requireRole(ServerRequest request, String roleAdmin) {
        String role = request.headers().firstHeader("X-User-Role");
        if (role == null || !role.equals(roleAdmin)) {
            throw new RuntimeException("Unauthorized");
        }
        return request;
    }

}
