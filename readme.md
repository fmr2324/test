### 一、配置项修改

1. application.yml文件中图片存储路径可以根据自己的系统重新配置

2. WebMvcConfig配置文件中图片的访问路径前缀'public'可以自行修改，addResourceLocations方法中
的本地磁盘映射路径也可以修改

        registry.addResourceHandler("/public/**").addResourceLocations("file:/D:/image/cms1010/");

### 二、运行
1. 后台访问路径
    
        地址：http://localhost:9000/cms/a
        默认账户/密码：admin/111111

2. 前台访问路径
    
        地址：http://localhost://9000/cms/f
    
