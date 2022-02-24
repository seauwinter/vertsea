package com.example;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.*;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.types.EventBusService;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.servicediscovery.types.JDBCDataSource;
import io.vertx.servicediscovery.types.MessageSource;
import io.vertx.core.impl.ConcurrentHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * BaseServiceVerticle
 * +----------------------------------------------------------------------
 * | 宝讯融通
 * +----------------------------------------------------------------------
 * | Copyright (c)
 * +----------------------------------------------------------------------
 * | Datetime 2022/2/11 16:32
 * +----------------------------------------------------------------------
 * | Author: agridata qiuhaidong <qiuhaidong@agridata.org>
 * +----------------------------------------------------------------------
 */
public abstract class BaseServiceVerticle extends AbstractVerticle {

  protected ServiceDiscovery discovery;

  protected CircuitBreaker circuitBreaker;

  protected Set<Record> registeredRecords = (Set<Record>) new ConcurrentHashSet();

  @Override
  public void start() throws Exception {
    this.discovery = ServiceDiscovery.create(this.vertx, (new ServiceDiscoveryOptions()).setBackendConfiguration(config()));
    JsonObject cbOptions = (config().getJsonObject("circuit-breaker") != null) ? config().getJsonObject("circuit-breaker") : new JsonObject();
    this.circuitBreaker = CircuitBreaker.create(cbOptions.getString("name", "circuit-breaker"), this.vertx, (new CircuitBreakerOptions())

      .setMaxFailures(cbOptions.getInteger("max-failures", Integer.valueOf(5)).intValue())
      .setTimeout(cbOptions.getLong("timeout", Long.valueOf(10000L)).longValue())
      .setFallbackOnFailure(true)
      .setResetTimeout(cbOptions.getLong("reset-timeout", Long.valueOf(30000L)).longValue()));
  }

  @Override
  public void stop(Future<Void> future) throws Exception {
    List<Future> futures = new ArrayList<>();
    this.registeredRecords.forEach(record -> {
      Future<Void> cleanupFuture = Future.future();
      futures.add(cleanupFuture);
      this.discovery.unpublish(record.getRegistration(), cleanupFuture.completer());
    });
    if (futures.isEmpty()) {
      this.discovery.close();
      future.complete();
    } else {
      CompositeFuture.all(futures)
        .setHandler(ar -> {
          this.discovery.close();
          if (ar.failed()) {
            future.fail(ar.cause());
          } else {
            future.complete();
          }
        });
    }
  }
}
