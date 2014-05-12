package cn.focus.dc.focuswap.filter;

import cn.focus.dc.focuswap.model.EsProjInfoChild;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.Set;

public class ChooseSubJsonFilter extends SimplePropertyPreFilter {
    
    private static ChooseSubJsonFilter single=null;
    
    private static Set<String> includes = null;
    
    private Class<?> clazz = null;

    
    private ChooseSubJsonFilter() {

    }

    public static synchronized ChooseSubJsonFilter getInstance(){
        if(single == null || includes == null){
            single = new ChooseSubJsonFilter();
            SimplePropertyPreFilter father = new SimplePropertyPreFilter(EsProjInfoChild.class, "buildingUrl",
                    "projName", "url", "saleStatus", "projAddress", "avgPriceShow", "discount");
            includes = father.getIncludes();
        }
        return single;
    }
    
    public Class<?> getClazz() {
        return EsProjInfoChild.class;
    }
    
    public boolean apply(JSONSerializer serializer, Object source, String name) {
        if (source == null) {
            return true;
        }

        if (clazz != null && !clazz.isInstance(source)) {
            return true;
        }

        if (includes.size() == 0 || includes.contains(name)) {
            return true;
        }

        return false;
    }
}
