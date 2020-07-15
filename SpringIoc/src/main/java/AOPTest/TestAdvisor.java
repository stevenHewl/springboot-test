package AOPTest;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/*
Advisor (通知器，将 Advice 和 PointCut 结合起来)
有了对目标方法的增强接口 Advice 和 如何匹配目标方法接口 PointCut 接口后，那么我们就需要用一个对象将他们结合起来，发挥AOP 的作用，
所以Spring 设计了 Advisor（通知器），这个 Advisor 肯定依赖了 Advice 和 PointCut，我们看看接口设计:
最重要的两个方法，getAdvice，getPointcut。和我们预想的基本一致。
* */
public class TestAdvisor implements PointcutAdvisor {
    /**
     * 获取通知处理逻辑
     */
    @Override
    public Advice getAdvice() {
        return new TestAfterAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    /**
     * 获取切入点
     */
    @Override
    public Pointcut getPointcut() {
        return new TestPointcut();
    }
}
