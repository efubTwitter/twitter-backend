package twitter.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        // 프론트엔드의 도메인이 들어가는 파트입니다.
        // 미리 짝개발자에게 이야기해, 배포된 도메인을 받아 새로 도메인을 allow 해줍니다.
        // 만약 "https://abc.dkfj"라는 도메인을 전달받았다면, 아래 코드에 추가로 다음과 같이 입력합니다.
        // config.addAllowedOrigin("https://abc.dkfj/");
        config.addAllowedOrigin("http://localhost:3000/");


        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}