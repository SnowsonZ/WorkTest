package com.learn.snnipet.proxy.dynamic;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @date 2020/5/3 11:44
 */
@Slf4j
public class ProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyTest instance = new ProxyTest();
        GoodsQueryService jdkProxy = instance.jdkProxy();
        log.info("{}, {}", jdkProxy.queryOne(100), jdkProxy.updateOne(101));

        GoodsQueryServiceImpl cglibProxy = instance.cglibProxy();
        cglibProxy.print();
        cglibProxy.receive("complete information.");
        log.info("query: {}, update: {}", cglibProxy.queryOne(100), cglibProxy.updateOne(100));
    }

    private GoodsQueryService jdkProxy() {
        return (GoodsQueryService) Proxy.newProxyInstance(GoodsQueryServiceImpl.class.getClassLoader(),
                GoodsQueryServiceImpl.class.getInterfaces(),
                new GoodsInvocationHandler(new GoodsQueryServiceImpl()));

    }

    private GoodsQueryServiceImpl cglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GoodsQueryServiceImpl.class);
        enhancer.setCallbacks(new Callback[]{new GoodsMethodSpecialInterceptor(),
                new GoodsMethodInterceptor(), new ReturnMethodInterceptor()});
        enhancer.setCallbackFilter(new GoodsMethodFilter());
        //构造函数不拦截
        enhancer.setInterceptDuringConstruction(false);

        return (GoodsQueryServiceImpl) enhancer.create();
    }

    @Data
    @AllArgsConstructor
    class GoodsInvocationHandler implements InvocationHandler {

        private Object target;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("invoke query method");
            return method.invoke(target, args);
        }
    }

    class GoodsMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            log.info("call {} before", method.getName());
            methodProxy.invokeSuper(o, objects);
            log.info("call {} after", method.getName());

            return o;
        }
    }

    class GoodsMethodSpecialInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            log.info("special {} before", method.getName());
            methodProxy.invokeSuper(o, objects);
            log.info("special {} after", method.getName());

            return o;
        }
    }

    class ReturnMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invokeSuper(o, objects);
        }
    }

    /**
     * 定义拦截策略
     */
    class GoodsMethodFilter implements CallbackFilter {

        @Override
        public int accept(Method method) {
            if (method.getName().contains("print")) {
                return 0;
            }else if (method.getName().contains("One")) {
                return 2;
            }
            return 1;
        }
    }
}
