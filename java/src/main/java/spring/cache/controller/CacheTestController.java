package spring.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CacheTestController {
    @GetMapping("/test")
    public String test() {
        log.info("info");
        log.debug("debug");
        log.trace("trace");
        return "test";
    }
}
