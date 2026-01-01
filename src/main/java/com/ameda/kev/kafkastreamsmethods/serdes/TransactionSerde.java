package com.ameda.kev.kafkastreamsmethods.serdes;


import com.ameda.kev.kafkastreamsmethods.events.Transaction;
import org.apache.kafka.common.serialization.Serdes;

public class TransactionSerde  extends Serdes.WrapperSerde<Transaction> {

    public TransactionSerde(){
        super(new TransactionSerializer(),new TransactionDeserializer());
    }
}
