package org.allengueco.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule;
import org.allengueco.converter.BytesToGuessesConverter;
import org.allengueco.converter.GuessesToBytesConverter;
import org.allengueco.game.GameSession;
import org.allengueco.game.Guesses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import java.util.List;

@Configuration
@EnableRedisHttpSession
public class SessionConfig {
    private final Logger log = LoggerFactory.getLogger(SessionConfig.class);

    @Bean
    EclipseCollectionsModule eclipseCollectionsModule() {
        return new EclipseCollectionsModule();
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookiePath("/api");
        serializer.setCookieMaxAge(3600);
        return serializer;
    }

    @Bean
    Jackson2JsonRedisSerializer<GameSession> gameSessionSerializer(ObjectMapper mapper) {
        return new Jackson2JsonRedisSerializer<>(mapper, GameSession.class);
    }

    @Bean
    RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                      Jackson2JsonRedisSerializer<GameSession> gameSessionSerializer) {
        final RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setDefaultSerializer(gameSessionSerializer);

        return redisTemplate;
    }

    @Bean
    RedisCustomConversions redisCustomConversions(ObjectMapper mapper) {
        return new RedisCustomConversions(
                List.of(new BytesToGuessesConverter(new Jackson2JsonRedisSerializer<>(mapper, Guesses.class)),
                        new GuessesToBytesConverter(new Jackson2JsonRedisSerializer<>(mapper, Guesses.class))));
    }
}
