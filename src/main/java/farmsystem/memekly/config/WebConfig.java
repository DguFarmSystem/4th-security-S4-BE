package farmsystem.memekly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // api 경로에 대해 CORS 허용
                .allowedOrigins("http://localhost:3000", "https://4th-security-s4-fe-8om6.vercel.app/", "https://4th-security-s4-fe.vercel.app") // 허용 Origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 자격 증명(쿠키, HTTP 인증) 허용 여부
                .maxAge(3600); // Preflight 요청 캐싱 시간 (초단위, 1시간)
    }
}