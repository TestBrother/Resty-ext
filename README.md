
#Resty-ext-beetl

对resty加入beetl的支持.

使用方法:

### 1. 在Config中配置

```
  public void configConstant(ConstantLoader constantLoader) {
    BeetlRenderFactory factory = new BeetlRenderFactory();
    constantLoader.addDefaultRender("html", new BeetlRender(factory.groupTemplate,"WEB-INF/view"));
  }
```

### 2. 在java代码使用

```
    @GET(value = "")
    public ModelView index() {
        ModelView view = new ModelView();
        view.setPara("name", "mikey.zhaopeng");
        view.setView("index.html");
        return view;
    }
```

### 3. 在html代码使用

```
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
</head>
    <body>
        hello ${name}
    </body>
</html>
```

> 由于resty需要对 response 进行管理,所以需要对 beetl 的代码需要进行重写.