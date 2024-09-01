package com.example.demo.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.Arrays;

@Configuration
public class WebConfig {


    @Bean
    public WebClient callBackWebClient12(WebClient.Builder webClientBuilder) throws SSLException {
        SslContext context = SslContextBuilder.forClient()
                .protocols("TLSv1.2")
                //.ciphers(Arrays.asList("TLS_AES_128_GCM_SHA256","TLS_AES_256_GCM_SHA384","TLS_CHACHA20_POLY1305_SHA256"))
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(context));
        webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient));
        return webClientBuilder.build();
    }

    @Bean
    public WebClient callBackWebClient13(WebClient.Builder webClientBuilder) throws SSLException {
        final SslContext sslContextForTls13 = SslContextBuilder.forClient()
                .protocols("TLSv1.3")
                //.ciphers(Arrays.asList("TLS_AES_128_GCM_SHA256","TLS_AES_256_GCM_SHA384","TLS_CHACHA20_POLY1305_SHA256"))
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        final HttpClient httpClientForTls13 = HttpClient.create()
                .secure(ssl -> ssl.sslContext(sslContextForTls13));
        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClientForTls13))
                .build();
    }
}
