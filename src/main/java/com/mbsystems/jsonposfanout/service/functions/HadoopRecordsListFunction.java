package com.mbsystems.jsonposfanout.service.functions;

import com.mbsystems.jsonposfanout.model.HadoopRecord;
import com.mbsystems.jsonposfanout.model.LineItem;
import com.mbsystems.jsonposfanout.model.PosInvoice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface HadoopRecordsListFunction {

    Function<PosInvoice, List<HadoopRecord>> getHadoopRecords = x -> {
        List<HadoopRecord> records = new ArrayList<>();

        for (LineItem i : x.getInvoiceLineItems()) {
            HadoopRecord record = new HadoopRecord();
            record.setInvoiceNumber(x.getInvoiceNumber());
            record.setCreatedTime(x.getCreatedTime());
            record.setStoreID(x.getStoreID());
            record.setPosID(x.getPosID());
            record.setCustomerType(x.getCustomerType());
            record.setPaymentMethod(x.getPaymentMethod());
            record.setDeliveryType(x.getDeliveryType());
            record.setItemCode(i.getItemCode());
            record.setItemDescription(i.getItemDescription());
            record.setItemPrice(i.getItemPrice());
            record.setItemQty(i.getItemQty());
            record.setTotalValue(i.getTotalValue());
            if (x.getDeliveryType().equalsIgnoreCase("HOME-DELIVERY")) {
                record.setCity(x.getDeliveryAddress().getCity());
                record.setState(x.getDeliveryAddress().getState());
                record.setPinCode(x.getDeliveryAddress().getPinCode());
            }
            records.add(record);
        }
        return records;
    };
}
