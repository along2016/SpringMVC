import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Created by wjl on 2017/2/13.
 */
public class RedisJava {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("onnection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        System.out.println("=========================");
        jedis.set("name","wangjiliang");
        System.out.println("name is " + jedis.get("name"));

        System.out.println("=========================");
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");

//        List<String> list = jedis.lrange("tutorial-list", 0, 5);
        Set<String> list = jedis.keys("*");
        for (String tutorial : list){
            System.out.println("Stored string in redis:: " + tutorial);
        }
    }

}
