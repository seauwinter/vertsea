package com.example.vertsea;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import jdk.jfr.Frequency;

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

  public void start(Future<Void> future) throws Exception {
    super.start();
    // 创建HttpServer
    HttpServer server = vertx.createHttpServer();
    // 创建路由对象
    Router router = Router.router(vertx);
    //响应请求
//    router.route("/aaa/").handler(event -> event.end("hello worldaaa"));
//    router.route("/bbb/").handler(event -> event.end("hello worldBBBB"));
//    router.route("/ccc/").handler(event -> event.end("hello worldCCC"));

    server.requestHandler(request->{
      HttpServerResponse response = request.response();

      response.putHeader("content-type","text/plain");
      System.out.println("ds");
      response.end("Hello World!");



    });
    // 把请求交给路由处理
    server.requestHandler(router);



    //监听端口
//    server.listen(9999).onComplete(event -> {
//      if(event.succeeded()){
//        System.out.println("服务器启动成功 端口:" + event.result().actualPort());
//      }else{
//        event.cause().printStackTrace();
//      }
//    });
  }

  public void  stop(Future<Void> future) throws Exception{
    super.stop();
  }
}
