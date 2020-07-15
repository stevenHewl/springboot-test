package AOPTest;

import org.springframework.aop.*;
import java.lang.reflect.Method;

/*
* 切入点，定义匹配哪些方法
* */
public class TestPointcut implements Pointcut {

    // 该方法返回一个类过滤器，由于一个类可能会被多个代理类代理，于是Spring引入了责任链模式
    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    /*
    返回一个方法匹配器，我们知道，AOPTest 的作用是代理方法，那么，Spirng 怎么知道代理哪些方法呢？
    必须通过某种方式来匹配方法的名称来决定是否对该方法进行增强，这就是 MethodMatcher 的作用
    */
    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            // 只要方法名称是test则对该方法进行增强或者说拦截。
            public boolean matches(Method method, Class<?> targetClass, Object[] args) {
                if (method.getName().equals("IOCTest")) {
                    return true;
                }
                return false;
            }
            public boolean matches(Method method, Class<?> targetClass) {
                if (method.getName().equals("IOCTest")) {
                    return true;
                }
                return false;
            }
            // 返回false表示是静态方法匹配器，返回true表示是动态方法匹配器。
            public boolean isRuntime() {
                return true;
            }
        };
    }
}
