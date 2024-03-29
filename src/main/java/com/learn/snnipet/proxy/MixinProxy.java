package com.learn.snnipet.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Snowson
 * @since 2019/11/15 16:38
 */
public class MixinProxy implements InvocationHandler {
    Map<String, Object> delegatesByMethod;

    public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
        delegatesByMethod = new HashMap<>();
        for (TwoTuple<Object, Class<?>> pair : pairs) {
            for (Method method : pair.getSecond().getMethods()) {
                String methodName = method.getName();
                // The first interface in the map
                // implements the method.
                if (!delegatesByMethod.containsKey(methodName)) {
                    delegatesByMethod.put(methodName, pair.getFirst());
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }

    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple... pairs) {
        Class[] interfaces = new Class[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            interfaces[i] = (Class) pairs[i].getSecond();
        }
        ClassLoader cl = pairs[0].getSecond().getClassLoader();
        return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
    }
}


//public class DynamicProxyMixin {
//    public static void main(String[] args) {
//        Object mixin = MixinProxy.newInstance(tuple(new BasicImp(), Basic.class),
//                tuple(new TimeStampedImp(), TimeStamped.class),
//                tuple(new SerialNumberedImp(), SerialNumbered.class));
//        Basic b = (Basic) mixin;
//        TimeStamped t = (TimeStamped) mixin;
//        SerialNumbered s = (SerialNumbered) mixin;
//        b.set("Hello");
//        System.out.println(b.get());
//        System.out.println(t.getStamp());
//        System.out.println(s.getSerialNumber());
//    }
//}
