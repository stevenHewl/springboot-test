package springbootTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
注册启动类
创建SpringBootTestApplication.java，继承SpringBootServletInitializer ，覆盖configure()，把启动类Application注册进去
*/

@SpringBootApplication
//@MapperScan("springbootTest.mybatis.mapperDao") // 对mybatis的mapper包的扫描，或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的
//@EnableCaching
public class SpringBootTestApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootTestApplication.class);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}
}

