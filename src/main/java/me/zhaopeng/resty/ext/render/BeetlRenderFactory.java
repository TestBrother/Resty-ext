package me.zhaopeng.resty.ext.render;

import cn.dreampie.common.Render;
import cn.dreampie.log.Logger;
import cn.dreampie.route.render.RenderFactory;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.WebAppResourceLoader;

import java.io.IOException;

/**
 * Created by mikey.zhaopeng on 2015/4/14.
 */
public class BeetlRenderFactory extends RenderFactory {
    private static final Logger logger = Logger.getLogger(BeetlRender.class);
    public static String viewExtension = ".html";
    public static GroupTemplate groupTemplate = null;

    public BeetlRenderFactory() {
        init(null);
    }

    private void init(String root) {
        if (groupTemplate != null) {
            groupTemplate.close();
        }
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            WebAppResourceLoader resourceLoader = new WebAppResourceLoader(root);
            resourceLoader.setRoot(resourceLoader.getRoot()+"WEB-INF/views/");
            groupTemplate = new GroupTemplate(resourceLoader, cfg);
        } catch (IOException e) {
            throw new RuntimeException("加载GroupTemplate失败", e);
        }
    }

    public Render getRender(String view) {
        return new BeetlRender(groupTemplate, view);
    }

    public String getViewExtension() {
        return viewExtension;
    }

}
