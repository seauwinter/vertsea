package com.example.vertsea;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

/**
 * HelloCommandFactory
 * +----------------------------------------------------------------------
 * | 宝讯融通
 * +----------------------------------------------------------------------
 * | Copyright (c)
 * +----------------------------------------------------------------------
 * | Datetime 2022/2/11 16:08
 * +----------------------------------------------------------------------
 * | Author: agridata qiuhaidong <qiuhaidong@agridata.org>
 * +----------------------------------------------------------------------
 */
public class HelloCommandFactory extends DefaultCommandFactory<HelloCommand> {
  public HelloCommandFactory() {
    super(HelloCommand.class);
  }
}
