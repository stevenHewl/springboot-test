
import frameworkMy.Context.XmlApplicationContext;
import IOCTest.HelloWorld;

public class TestIoc {
    public static void main(String[] args)  {
        try {
            XmlApplicationContext context = new XmlApplicationContext("/myspring.xml");
            HelloWorld hw = (HelloWorld) context.getBean("hello01");
            hw.getUserName();
            hw.setUserName("hewenliang");
            hw.getUserName();
        } catch (Exception es){
            es.printStackTrace();
        }
    }
}
