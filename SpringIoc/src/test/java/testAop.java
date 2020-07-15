
import AOPTest.*;
import frameworkMy.Context.XmlApplicationContext;

public class testAop {
    public static void main(String[] args) {
        XmlApplicationContext context = new XmlApplicationContext("/myspringAop.xml");
        TestTarget target = (TestTarget) context.getBean("testAOP");
        target.IOCTest();
        System.out.println("----------------");
        target.test2();
    }
}
