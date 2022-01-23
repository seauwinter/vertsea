package com.example.vertsea;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * +----------------------------------------------------------------------
 * | 宝讯融通
 * +----------------------------------------------------------------------
 * | Copyright (c)
 * +----------------------------------------------------------------------
 * | Datetime 2022/1/23 14:24
 * +----------------------------------------------------------------------
 * | Author: agridata qiuhaidong <qiuhaidong@agridata.org>
 * +----------------------------------------------------------------------
 */
public class HttpServerVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    // 创建HttpServer
    HttpServer server = vertx.createHttpServer();
    // 创建路由对象
    Router router = Router.router(vertx);
    //响应请求
    router.route("/").handler(event -> event.end("hello world111"));
    // 把请求交给路由处理
    server.requestHandler(router);
    //监听端口
    server.listen(9999).onComplete(event -> {
      if(event.succeeded()){
        System.out.println("服务器启动成功 端口:" + event.result().actualPort());
      }else{
        event.cause().printStackTrace();
      }
    });
  }
}
