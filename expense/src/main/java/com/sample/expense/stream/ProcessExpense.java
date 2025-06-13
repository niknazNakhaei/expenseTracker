package com.sample.expense.stream;

import com.sample.expense.entity.SentEvent;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.Consumer;

public class ProcessExpense implements Consumer<KStream<String, SentEvent>> {

    @Override
    public void accept(KStream<String, SentEvent> stream) {
    }
}
