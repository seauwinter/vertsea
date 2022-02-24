package com.example.vertsea;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * lanch
 * +----------------------------------------------------------------------
 * | 宝讯融通
 * +----------------------------------------------------------------------
 * | Copyright (c)
 * +----------------------------------------------------------------------
 * | Datetime 2022/2/11 11:52
 * +----------------------------------------------------------------------
 * | Author: agridata qiuhaidong <qiuhaidong@agridata.org>
 * +----------------------------------------------------------------------
 */
public class VeinBaseLauncher extends Launcher {
    public static void main(String[] args) {
      (new VeinBaseLauncher()).dispatch(args);
    }

    public void beforeStartingVertx(VertxOptions options) {
      options.setWarningExceptionTime(10000000000L);
      options.setBlockedThreadCheckInterval(3600000L);
      options.setMaxEventLoopExecuteTime(2000000000L);
      super.beforeStartingVertx(options);
    }

    public void afterStartingVertx(Vertx vertx) {
      super.afterStartingVertx(vertx);
    }
}
