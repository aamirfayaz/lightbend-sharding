package com.reactivebbq.orders

import akka.actor.{ActorRef, ActorSystem}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings}
import akka.http.scaladsl.Http
import akka.management.scaladsl.AkkaManagement
import akka.routing.RoundRobinPool
import org.slf4j.LoggerFactory

object Main extends App {
  val log = LoggerFactory.getLogger(this.getClass)

  val Opt = """-D(\S+)=(\S+)""".r
  args.toList.foreach {
    case Opt(key, value) =>
      log.info(s"Config Override: $key = $value")
      System.setProperty(key, value)
  }

  implicit val system: ActorSystem = ActorSystem("Orders")

  AkkaManagement(system).start()

  val blockingDispatcher = system.dispatchers.lookup("blocking-dispatcher")
  val orderRepository: OrderRepository = new SQLOrderRepository()(blockingDispatcher)

  // val orders: ActorRef = system.actorOf(RoundRobinPool(100).props(OrderActor.props(orderRepository)))
  //Now, Cluster Sharding
  val orders = ClusterSharding(system).start(
    typeName = "orders",
    entityProps = OrderActor.props(orderRepository),
    settings = ClusterShardingSettings(system),
    extractEntityId = OrderActor.entityIdExtractor,
    extractShardId = OrderActor.shardIdExtractor
  )

  val orderRoutes = new OrderRoutes(orders)(system.dispatcher)

  Http().bindAndHandle(orderRoutes.routes, "localhost")
}
