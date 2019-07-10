package spring.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import spring.SpringBootStudyApplicationTest;
import spring.cache.repository.NameRepo;

@Slf4j
public class NameRepoTest extends SpringBootStudyApplicationTest {

    @Autowired
    private NameRepo nameRepo;

    @Test
    public void test() {
        nameRepo.findByKeyGenerator("test1");
        nameRepo.findByKeyGenerator("test1");
        nameRepo.findByStr("test2");
        nameRepo.findByStr("test2");
        nameRepo.findByStr("test2");

        log.trace("trace");
        log.debug("debug");
        log.info("debug");
    }
}