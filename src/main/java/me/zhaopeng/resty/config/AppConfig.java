package me.zhaopeng.resty.config;

import cn.dreampie.route.config.*;
import cn.dreampie.route.handler.cors.CORSHandler;
import me.zhaopeng.resty.ext.render.BeetlRender;
import me.zhaopeng.resty.ext.render.BeetlRenderFactory;

/**
 * Created by mikey.zhaopeng on 2015-05-08.
 */
public class AppConfig extends Config {

  public void configConstant(ConstantLoader constantLoader) {
    BeetlRenderFactory factory = new BeetlRenderFactory();
    constantLoader.addDefaultRender("html", new BeetlRender(factory.groupTemplate,"WEB-INF/view"));
  }

  public void configResource(ResourceLoader resourceLoader) {
    //设置resource的目录  减少启动扫描目录
    resourceLoader.addIncludePackages("me.zhaopeng.resty");
  }

  public void configPlugin(PluginLoader pluginLoader) {
  }

  public void configInterceptor(InterceptorLoader interceptorLoader) {
  }

  public void configHandler(HandlerLoader handlerLoader) {
    //跨域
    handlerLoader.add(new CORSHandler());
  }
}
