package AOPTest;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

// Advice (通知，定义在切入点做什么)
public class TestAfterAdvice implements AfterReturningAdvice {

    // 目标方法执行之后会回调该方法
    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {

        System.out.println("after " + target.getClass().getSimpleName() + "." + method.getName() + "()");
        System.out.println("这里是切入点，可以在此处理个性化服务");
    }
}
