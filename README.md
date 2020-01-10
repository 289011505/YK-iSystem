# YK-iSystem
基于SpringCloud 的分布式后台管理系统

### 基本架构的理解
> 数据库表的关系, 这里主要针对于用户-角色-权限的关系
* tb_system_user, tb_system_role, tb_system_authority 这三张表分别是用户表, 角色表, 权限表
它们之间分别有两张中间表关联 tb_user_role, tb_authority_role. 

* 注意这里的tb_system_authority, 还要与tb_system_api关联才是真正的权限管理, 因为tb_system_api表示所有的api接口数据
, api与authority的关系的一对一的(<font color="red">这里本身是不需要这么复杂的, 但是由于前期的多设计了一张tb_system_menu菜单表, 导致了菜单也算是权限管理的一部分, 由于是前后端分离的项目, 所以菜单/路由的控制交给了前端</font>)

* 由于上面两点, 所以整个系统的权限管理的流程为, 通过userId查找对应的角色(注意： 这里的role与user也是一对一的), 在通过roleId查找
authority-api列表

> 后端代码权限控制说明
* isystem-auth-server是整个系统的统一认证模块, 集成了oauth2, Spring Security采用密码模式进行认证的

* 由于是Spring Cloud分布式项目所以路由网关的服务是必须的, isystem-zuul就是路由网关服务, 这里路由网关的作用就是配置统一的路由列表
(<font color="red">注意: 这里的路由列表并不是前端的路由, 而是各个微服务所注册的服务名称以及别名路径</font>).
而且，获取当前登录用户的信息必须要通过zuul网关才能获取, 否则获取到的认证类型是AnonymousAuthentication, 无法获取用户信息. 因为在统一的网关服务中，配置了获取认证服务的token方法
```java
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(AppSession.buildRedisTokenServices(redisConnectionFactory));
        resources.expressionHandler(expressionHandler);
    }
```

* 权限控制是有isystem-auth-server服务与isystem-zuul服务共同完成的, 必须先启动isystem-auth-server服务, 再启动isystem-zuul服务。
通过JdbcRouteLocator加载路由列表, ResourceLocator权限资源列表, 在认证资源服务类ResourceServerConfiguration中, accessManager.check方法
就是用来判断访问用户是否有权限访问该接口
```java
@Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                //动态权限验证
                .anyRequest().access("@accessManager.check(request, authentication)")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new YkAccessDeniedHandler())
                .authenticationEntryPoint(new YkAuthenticationEntryPoint())
                .and()
                .csrf().disable();
        // 前置过滤器
        http.addFilterBefore(new PreRequestFilter(), AbstractPreAuthenticatedProcessingFilter.class);
    }
```
