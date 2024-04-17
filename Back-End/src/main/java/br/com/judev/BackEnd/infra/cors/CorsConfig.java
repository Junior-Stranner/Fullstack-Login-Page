package br.com.judev.BackEnd.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define as configurações de CORS para o aplicativo

        // Adiciona um mapeamento CORS para todos os endpoints (/**)
        registry.addMapping("/**")
                // Define os domínios que estão autorizados a acessar o aplicativo (origens permitidas)
                .allowedOrigins("http://localhost:4200")
                // Define os métodos HTTP que estão autorizados a serem usados pelos clientes
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }

}