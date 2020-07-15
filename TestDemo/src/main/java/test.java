import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
	public static void main(String[] args) {
		try
		{
			ClassLoader c  = test.class.getClassLoader();  //获取Test类的类加载
			System.out.println(c);

			ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器
			System.out.println(c1);

			ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器
			System.out.println(c2);

			Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("steven.MyClassLoad");
			Class<?> clazz2 = Class.forName("steven.MyClassLoad");
			try {
				Object instance = clazz.newInstance();
				((MyClassLoad) instance).testClassMethod();

				((MyClassLoad)clazz2.newInstance()).testClassMethod();
			}
			catch (Exception es){
				es.printStackTrace();
			}

			//myClassLoad.testClassMethod();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
