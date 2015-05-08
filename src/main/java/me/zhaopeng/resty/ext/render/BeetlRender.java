package me.zhaopeng.resty.ext.render;

import cn.dreampie.common.Render;
import cn.dreampie.common.http.HttpRequest;
import cn.dreampie.common.http.HttpResponse;
import cn.dreampie.log.Logger;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GroupTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by mikey.zhaopeng on 2015/4/14.
 */
public class BeetlRender extends Render {
    private static final Logger logger = Logger.getLogger(BeetlRender.class);
    private transient static final String encoding = "UTF-8";
    private transient static final String contentType = "text/html; charset=" + encoding;
    protected String view;
    GroupTemplate gt = null;

    public BeetlRender(GroupTemplate gt, String view) {
        this.gt = gt;
        this.view = view;
    }

    @Override
    public void render(HttpRequest request, HttpResponse response, Object out) {
        response.setContentType(contentType);
        if (out == null) {
            return;
        }
        HttpServletRequest httpServletRequest = request.unwrap(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = response.unwrap(HttpServletResponse.class);
        if (out instanceof ModelView) {
            ModelView modelView = (ModelView) out;

            if (StringUtils.isNotBlank(modelView.getRedirect())) {
                try {
                    httpServletResponse.sendRedirect(modelView.getRedirect());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Set<Map.Entry> entries = modelView.getPara().entrySet();
                for (Map.Entry entry : entries) {
                    httpServletRequest.setAttribute((String) entry.getKey(), entry.getValue());
                }
                WebRender webRender = new WebRender(gt);
                webRender.render(modelView.getView(), httpServletRequest, httpServletResponse);
            }
        } else if (out instanceof String) {
            if ("Unauthorized".equals(out)) {
                try {
                    httpServletResponse.sendRedirect("/account/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.write(request, response, (String) out);
            }
        }

    }
}
