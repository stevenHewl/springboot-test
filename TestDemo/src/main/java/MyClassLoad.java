
import org.springframework.stereotype.Component;

@Component
public class MyClassLoad {
	public MyClassLoad(){
		System.out.println("hello, this is a class");
	}

	public void testClassMethod(){
		String str = this.getClass().getClassLoader().getResource("").toString();
		System.out.println(str);
	}
}
