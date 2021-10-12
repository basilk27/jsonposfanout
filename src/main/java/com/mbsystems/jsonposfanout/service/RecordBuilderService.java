//package com.mbsystems.jsonposfanout.service;
//
//import com.mbsystems.jsonposfanout.model.HadoopRecord;
//import com.mbsystems.jsonposfanout.model.Notification;
//import com.mbsystems.jsonposfanout.model.PosInvoice;
//import com.mbsystems.jsonposfanout.service.functions.HadoopRecordsListFunction;
//import com.mbsystems.jsonposfanout.service.functions.MaskInvoiceFunction;
//import com.mbsystems.jsonposfanout.service.functions.NotificationFunction;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RecordBuilderService {
//
//    public Notification getNotification(PosInvoice invoice) {
//        return NotificationFunction.getNotification.apply(invoice);
//    }
//
//    public PosInvoice getMaskedInvoice(PosInvoice invoice) {
//        return MaskInvoiceFunction.getMaskedInvoice.apply(invoice);
//    }
//
//    public List<HadoopRecord> getHadoopRecords(PosInvoice invoice) {
//        return HadoopRecordsListFunction.getHadoopRecords.apply(invoice);
//    }
//}
