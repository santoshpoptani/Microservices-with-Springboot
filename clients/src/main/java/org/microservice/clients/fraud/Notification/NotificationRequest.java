package org.microservice.clients.fraud.Notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
        ) {
}
