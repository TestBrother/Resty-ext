package me.zhaopeng.resty.ext.render;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikey.zhaopeng on 2015/4/14.
 */
public class ModelView {
    private Map para = new HashMap();
    private String view;
    private String redirect;

    public void setPara(String key, Object val) {
        this.para.put(key, val);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }


    public Map getPara() {
        return para;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
