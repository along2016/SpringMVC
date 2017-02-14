import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/2/14.
 */
public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.30.79", 6379);
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());
    }
}
