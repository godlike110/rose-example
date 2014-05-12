package cn.focus.dc.focuswap.controllers;


import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import static cn.focus.dc.focuswap.config.AppConstants.*;

@Path("flush")
public class FlushCacheController {

    @Autowired
    private MemcachedClient cacheClient;

    @Get("all")
    @Post("all")
    public String flushCacheAll(Invocation
            inv) {
        String auth = ServletRequestUtils.getStringParameter(inv.getRequest(), "auth", "");
        String callBack = ServletRequestUtils.getStringParameter(inv.getRequest(), "auth", "callback");
        String msg = StringUtils.EMPTY;
        if (FLUSH_CACHE_AUTH.equalsIgnoreCase(auth)) {
            try {
                cacheClient.flushAll();
            } catch (Exception e) {
                msg = "error:" + e.getMessage();
            }
            msg = "flushall_success";
        } else { 
            msg = "error: auth";
        }
        return "@" + callBack + "(" + msg + ")";
    }

}

