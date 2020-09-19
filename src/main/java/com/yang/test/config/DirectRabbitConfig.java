package com.yang.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Lyon
 * @Date: 2020/9/18 17:06
 * @Description:
 */
@Configuration
public class DirectRabbitConfig {
    Logger logger = LoggerFactory.getLogger(DirectRabbitConfig.class);

    //队列 起名：TestDirectQueue
    @Bean
    public Queue DirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        logger.info("Consumer -----> A new queue is generated : Lyon Direct Queue");
        return new Queue("Lyon Direct Queue",true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange DirectExchange() {
        logger.info("Consumer---> A new Direct Exchange is generated : Lyon Direct Exchange");
        return new DirectExchange("Lyon Direct Exchange",true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        logger.info("Consumer ---> Routing Key : Lyon Direct Routing");
        return BindingBuilder.bind(DirectQueue()).to(DirectExchange()).with("Lyon Direct Routing");
    }



    @Bean
    DirectExchange lonelyDirectExchange() {
        logger.info("Consumer --->A new lonely Exchange is generated : lonely Direct Exchange ");
        return new DirectExchange("lonely Direct Exchange");
    }



}