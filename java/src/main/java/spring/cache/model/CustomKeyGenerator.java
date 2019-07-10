package spring.cache.model;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class CustomKeyGenerator implements KeyGenerator {
    private static final String KEY_DELIMITER = ".";
    private static final String PREFIX_DELIMITER = ":";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder keyBuilder = new StringBuilder();

        if (target != null) {
            keyBuilder.append(target.getClass().getSimpleName());
        }

        if (method != null) {
            keyBuilder.append(KEY_DELIMITER);
            keyBuilder.append(method.getName());
        }

        for (Object param : params) {
            if (param != null) {
                keyBuilder.append(PREFIX_DELIMITER);
                keyBuilder.append(param);
            }
        }

        String result = keyBuilder.toString();
        log.trace("CustomKeyGenerator : {}", result);

        return result;
    }
}