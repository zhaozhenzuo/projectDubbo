#projectDubbo
采用 dubbo作为分布式框架
持久层:mybatis
mvc框架：spring mvc
view层：freemarker


spring boot方便开发，用于内嵌容器

项目模块按业务维度划分：
mainsite：用于用户直接访问
facade:用于组合调用各具体业务模块
framework:所有公用类都会放入在此工程
codeinfoservice:常量记录会放在此工程
security:安全框架
userservice:用户模块
productservice:商品模块
cartservice:购物车模块
orderservice:订单模块