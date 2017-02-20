import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/2/14.
 */
public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.30.79", 7000);
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());

        System.out.println("==========================");
        jedis.lpush("fruit-list", "apple");
        jedis.lpush("fruit-list", "orange");
        jedis.lpush("fruit-list", "banana");
        List<String> fruitList = jedis.lrange("fruit-list", 0, 5);
        for (String fruit : fruitList){
            System.out.println("The Fruit is:" + fruit);
        }
    }
}
