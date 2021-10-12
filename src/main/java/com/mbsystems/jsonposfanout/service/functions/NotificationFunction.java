package com.mbsystems.jsonposfanout.service.functions;

import com.mbsystems.jsonposfanout.model.Notification;
import com.mbsystems.jsonposfanout.model.PosInvoice;

import java.util.function.Function;

public interface NotificationFunction {

    Function<PosInvoice, Notification> getNotification = x -> {
        Notification notification = new Notification();
        notification.setInvoiceNumber(x.getInvoiceNumber());
        notification.setCustomerCardNo(x.getCustomerCardNo());
        notification.setTotalAmount(x.getTotalAmount());
        notification.setEarnedLoyaltyPoints(x.getTotalAmount() * 0.02);
        return notification;
    };
}
