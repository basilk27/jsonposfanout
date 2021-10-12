package com.mbsystems.jsonposfanout.service.functions;

import com.mbsystems.jsonposfanout.model.PosInvoice;

import java.util.function.UnaryOperator;

public interface MaskInvoiceFunction {

    UnaryOperator<PosInvoice> getMaskedInvoice = x -> {
        x.setCustomerCardNo(null);
        if (x.getDeliveryType().equalsIgnoreCase("HOME-DELIVERY")) {
            x.getDeliveryAddress().setAddressLine(null);
            x.getDeliveryAddress().setContactNumber(null);
        }
        return x;
    };
}
