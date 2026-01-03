package com.ameda.kev.kafkastreamsmethods.streams;

import com.ameda.kev.kafkastreamsmethods.events.Transaction;
import com.ameda.kev.kafkastreamsmethods.serdes.TransactionSerde;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
@Slf4j
public class StreamMethodsWorking {

    //create bean
     //-> read the topic
    //-> process filter
    //-> write to dest

    /**
    * filter(),
     * filterNot(),
     * map(),
     * mapValues(),
     * flatMap(),
     * flatMapValues(),
     * branch(),
     * groupByKey(),
     * aggregate(),
     * count()
    * */

    @Bean
    public KStream<String, Transaction> workingWithStreamMethods(StreamsBuilder builder) {

       // var transactionSerde = new JsonSerde<>(Transaction.class);
        // builder.stream("transactions",Consumed.with(Serdes.String(), transactionSerde));
        KStream<String, Transaction> stream =
                builder.stream("transactions", Consumed.with(Serdes.String(), new TransactionSerde()));
//
//        stream
//                .filter((key, tx) -> tx.amount() > 10000)
//                .peek((key, tx) -> log.warn("FRAUD ALERT for {}", tx))
//                .to("fraud-alerts", Produced.with(Serdes.String(), new TransactionSerde()));
//        stream
//                .filterNot((key,tx) -> tx.amount() < 10000)
//                .peek((key,tx)-> log.warn(" Fraud alert for {}",tx))
//                .to("filter-not-topic", Produced.with(Serdes.String(), new TransactionSerde()));

        // with the map we get to edit the key plus the value, we have  that flexibility.
//        stream.map((key,tx) -> KeyValue.pair(tx.userId()," user spent "+ tx.amount()))
//                .peek((key,val)-> log.info(" User transaction summary: key: {} , value: {}", key,val));

        //Here we only have the luxury to transform the value only.
//        stream.mapValues(tx -> "Transaction of $ "+ tx.amount()+" by user "+ tx.userId())
//                .peek((key,val)-> log.info(" User transaction summary value Only: value : {} , value: {}", key,val));

        //flatmap. Works in a one to many set up kinda like what happens with its equivalent in java 8
      /*  stream.flatMap((key,tx)-> {
            List<KeyValue<String, Item>> result = new ArrayList<>();
            for (Item item : tx.items()){
                result.add(KeyValue.pair(tx.transactionId(),item));
            }
            return result;
        }).peek((transactionId, item) ->
                log.info("Item purchased : Transaction ID :{}, Item: {} ",transactionId,item));*/


        //flatMapValues. Works in a one to many set up but with it, we cannot modify the keys
//        stream.flatMapValues(Transaction::items)
//                .peek((key,item)-> log.info(" flatMapValues -- Item purchased value only: {}", item));


        // branch(). Splits a single stream into multiple sub-stream based on a condition. i.e from the transactions.json provided in
        //classpath, we have a field as 'type', we could split the records based on that.


        stream
                .split(Named.as("txn-"))
                .branch((key, txn) -> txn.type().equalsIgnoreCase("debit"),
                        Branched.withConsumer(debitKs ->
                                debitKs.peek((k,t) -> log.info(" Debit transaction key :{} and transactions : {}",k,t.amount()))
                                        .to("debit-topic")))
                .branch((key, txn)-> txn.type().equalsIgnoreCase("credit"),
                        Branched.withConsumer( creditKs ->
                                creditKs.peek((k,t)-> log.info("Credit transaction key: {} and transaction : {}",k,t.amount())).
                                        to("credit-topic")));
        return stream;

    }


}
