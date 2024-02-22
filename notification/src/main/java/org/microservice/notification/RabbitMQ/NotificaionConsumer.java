package org.microservice.notification.RabbitMQ;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.clients.fraud.Notification.NotificationRequest;
import org.microservice.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificaionConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request){
        log.info("Consumed {} from queue", request);
        notificationService.send(request);
    }
}
