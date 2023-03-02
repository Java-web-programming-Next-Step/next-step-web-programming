package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.web.Controller;
import core.web.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerExecution {
    private final Class<?> controller;
    private final Method method;

    public HandlerExecution(Class<?> controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            method.setAccessible(true);
            return (ModelAndView) method.invoke(controller.newInstance(), request, response);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            //
        }
        return null;
    }
}
