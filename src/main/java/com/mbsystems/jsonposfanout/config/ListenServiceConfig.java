package com.mbsystems.jsonposfanout.config;

import com.mbsystems.jsonposfanout.model.HadoopRecord;
import com.mbsystems.jsonposfanout.model.Notification;
import com.mbsystems.jsonposfanout.model.PosInvoice;
import com.mbsystems.jsonposfanout.service.functions.HadoopRecordsListFunction;
import com.mbsystems.jsonposfanout.service.functions.MaskInvoiceFunction;
import com.mbsystems.jsonposfanout.service.functions.NotificationFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
@Slf4j
public class ListenServiceConfig {

//TODO bmk look into this
//    @Bean
//    public Function<KStream<String, PosInvoice>, KStream<String, PosInvoice>> delivery() {
//        Predicate<PosInvoice> homeDeliveryPredicate = x -> x.getDeliveryType().equalsIgnoreCase("HOME-DELIVERY");
//
//        return input -> input
//                .filter((key, invoice) -> homeDeliveryPredicate.test(invoice))
//                .repartition();
//    }

    @Bean
    public Function<KStream<String, PosInvoice>, KStream<String, Notification>> notification() {
      Predicate<PosInvoice> invoicePredicate = x -> x.getCustomerType().equalsIgnoreCase("PRIME");

        return input -> input
              .filter((key, invoice) -> invoicePredicate.test(invoice))
              .peek((k,v) -> System.out.println("BMK  key="+k+",   value="+v))
              .mapValues(NotificationFunction.getNotification::apply);
    }

    @Bean
    public Function<KStream<String, PosInvoice>, KStream<String, HadoopRecord>> maskedRecords() {
        return input -> input
                .mapValues(MaskInvoiceFunction.getMaskedInvoice::apply)
                .flatMapValues(HadoopRecordsListFunction.getHadoopRecords::apply);
    }
}
