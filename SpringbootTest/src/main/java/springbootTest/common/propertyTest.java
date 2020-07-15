package springbootTest.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//自定义属性 
@Component
public class propertyTest {
	@Value("${test.title}")
	private String title;
	@Value("${test.description}")
	private String description;

	public String getTitle() {
		return title; //new String(title.getBytes("gbk"),"utf-8");
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}