package com.example.vertsea;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

/**
 * 一切从FP开始
 * +----------------------------------------------------------------------
 * | 宝讯融通
 * +----------------------------------------------------------------------
 * | Copyright (c)
 * +----------------------------------------------------------------------
 * | Datetime 2022/1/27 9:49
 * +----------------------------------------------------------------------
 * | Author: agridata qiuhaidong <qiuhaidong@agridata.org>
 * +----------------------------------------------------------------------
 */
public class FPStartVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    EventBus eventBus = vertx.eventBus();

    MessageConsumer<JsonObject> consumer = eventBus.consumer("receiver");
    consumer.handler(message -> {
      JsonObject jsonMessage = message.body();
      System.out.println(jsonMessage.getValue("message_from_sender_verticle"));
      JsonObject jsonReply = new JsonObject().put("reply", "666 !");
      message.reply(jsonReply);
    });
  }
}
