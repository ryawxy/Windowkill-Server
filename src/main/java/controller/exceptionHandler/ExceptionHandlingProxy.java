package controller.exceptionHandler;

import controller.exceptionHandler.ExceptionHandler;
import controller.exceptionHandler.GlobalExceptionHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlingProxy implements InvocationHandler {
    private final Object target;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final Map<Class<? extends Throwable>, Method> exceptionHandlerMap = new HashMap<>();

    public ExceptionHandlingProxy(Object target, GlobalExceptionHandler globalExceptionHandler) {
        this.target = target;
        this.globalExceptionHandler = globalExceptionHandler;
        initializeHandlerMethods();
    }

    private void initializeHandlerMethods() {
        for (Method method : globalExceptionHandler.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionHandler.class)) {
                ExceptionHandler annotation = method.getAnnotation(ExceptionHandler.class);
                for (Class<? extends Throwable> exceptionClass : annotation.value()) {
                    exceptionHandlerMap.put(exceptionClass, method);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException e) {
            handleException(e.getTargetException());
        } catch (Exception e) {
            handleException(e);
        }
        return null;
    }

    private void handleException(Throwable throwable) {
        Method handlerMethod = null;
        for (Class<? extends Throwable> exceptionClass : exceptionHandlerMap.keySet()) {
            if (exceptionClass.isAssignableFrom(throwable.getClass())) {
                handlerMethod = exceptionHandlerMap.get(exceptionClass);
                break;
            }
        }

        if (handlerMethod != null) {
            try {
                handlerMethod.invoke(globalExceptionHandler, throwable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throwable.printStackTrace();
        }
    }

    public static <T> T createProxy(Class<T> interfaceType, Object target, GlobalExceptionHandler globalExceptionHandler) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class<?>[]{interfaceType},
                new ExceptionHandlingProxy(target, globalExceptionHandler)
        );
    }
}