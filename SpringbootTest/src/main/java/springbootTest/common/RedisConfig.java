package springbootTest.common;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

//annotation 注释
/*
 首先在我们的RedisConfig这个类上加上@EnableCaching这个注解。
 这个注解会被spring发现，并且会创建一个切面（aspect） 并触发Spring缓存注解的切点（pointcut） 。 
 根据所使用的注解以及缓存的状态， 这个切面会从缓存中获取数据， 将数据添加到缓存之中或者从缓存中移除某个值。
 * */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		/*
		 * keyGenerator方法帮我们注册了一个key的生成规则，就不用我们写spel表达式了，根据反射的原理读取类名+方法名+参数。
		 * 但是我们有时候还是需要结合spel的。
		 */
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/*
	 * 申明一个缓存管理器的bean，这个作用就是@EnableCaching这个切面在新增缓存或者删除缓存的时候会调用这个缓存管理器的方法
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(30));
		// 设置缓存有效期30分钟
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		// 设置value的序列化器
		// 使用Jackson 2，将对象序列化为JSON；
		Jackson2JsonRedisSerializer<Object> jack2JsonSerial = new Jackson2JsonRedisSerializer(Object.class);

		// json转对象类，不设置默认的会将json转成hashmap
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jack2JsonSerial.setObjectMapper(om);

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		// 值采用json序列化
		template.setValueSerializer(jack2JsonSerial);
		// 设置hash value序列化模式
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(jack2JsonSerial);
		template.afterPropertiesSet();

		return template;
	}

	/*
	 * 我们发现每次添加的key和value都是byte数组类型（使用很麻烦），于是spring为我们带来了redis template(模版) Spring
	 * Data Redis提供了两个模板： RedisTemplate StringRedisTemplate
	 * 首先我们先创建一个RedisTemplate模板类，类型的key是String类型，value是Object类型（
	 * 如果key和value都是String类型，建议使用StringRedisTemplate）
	 * 
	 * @frameworkMy.Bean public RedisTemplate<String, Object>
	 * redisTemplateObj(RedisConnectionFactory factory) { // 创建一个模板类
	 * RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	 * // 将刚才的redis连接工厂设置到模板类中 template.setConnectionFactory(factory); return
	 * template; }
	 */
}