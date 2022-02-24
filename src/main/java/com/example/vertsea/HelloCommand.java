package com.example.vertsea;

import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.spi.launcher.Command;
import io.vertx.core.spi.launcher.DefaultCommand;
import io.vertx.core.spi.launcher.ExecutionContext;

/**
 * HelloCommand
 * +----------------------------------------------------------------------
 * | 宝讯融通
 * +----------------------------------------------------------------------
 * | Copyright (c)
 * +----------------------------------------------------------------------
 * | Datetime 2022/2/11 16:09
 * +----------------------------------------------------------------------
 * | Author: agridata qiuhaidong <qiuhaidong@agridata.org>
 * +----------------------------------------------------------------------
 */
@Name("hello-command")
@Summary("A simple hello command.")
public class HelloCommand implements Command {

  @Override
  public void setUp(ExecutionContext executionContext) throws CLIException {

  }

  @Override
  public void run() throws CLIException {
    System .out.println("hellow");
  }

  @Override
  public void tearDown() throws CLIException {

  }
}
