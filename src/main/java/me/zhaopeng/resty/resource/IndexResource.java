package me.zhaopeng.resty.resource;

import cn.dreampie.route.core.Resource;
import cn.dreampie.route.core.annotation.API;
import cn.dreampie.route.core.annotation.GET;
import me.zhaopeng.resty.ext.render.ModelView;

/**
 * Created by mikey.zhaopeng on 2015/4/14.
 */
@API("/")
public class IndexResource extends Resource {
    @GET(value = "")
    public ModelView index() {
        ModelView view = new ModelView();
        view.setPara("name", "mikey.zhaopeng");
        view.setView("index.html");
        return view;
    }

}
