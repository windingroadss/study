package spring.cache.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class NameRepo {

    @Cacheable(value = "nameCache", condition = "#name.length() < 10")
    public String findByStr(String name) {
        log.info("cache find .. {}", name);
        return name;
    }

    @Cacheable(value = "nameCache", key = "#person.name", condition = "#name.length() < 10")
    public String findByObject(String name) {
        log.info("cache find .. {}", name);
        return name;
    }

    @Cacheable(value = "nameCache", keyGenerator = "customKeyGenerator", condition = "#name.length() < 10")
    public String findByKeyGenerator(String name) {
        log.info("cache find .. {}", name);
        return name;
    }
}
