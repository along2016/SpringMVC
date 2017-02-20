import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by alion on 2017/2/15.
 */
public class RedisSentineTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:applicationContext-redis.xml");
        RedisTemplate<String, String> template = (RedisTemplate<String, String>) context.getBean("redisTemplate");
        template.opsForValue().set("aaa", "aaabbb");
        System.err.println(template.opsForValue().get("aaa"));
    }
}
