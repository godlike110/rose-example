package cn.focus.dc.focuswap.controllers;


import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

/**
 * 向Context中注入城市定位信息
 * @author rogantian
 *
 */
public class CityLocatedInfoInjectionInterceptor extends ControllerInterceptorAdapter {

    @Override
    protected Object after(Invocation inv, Object instruction) throws Exception {
        return super.after(inv, instruction);
    }

}

