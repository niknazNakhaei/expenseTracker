package com.sample.expense.stream;

import com.sample.expense.entity.SentEvent;
import com.sample.expense.stream.impl.MonthlyExpenseValueMapper;
import com.sample.expense.stream.impl.MonthlyReportProcess;
import lombok.AllArgsConstructor;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@AllArgsConstructor
public class ExpenseStream implements Consumer<KStream<String, SentEvent>> {

    private final MonthlyExpenseValueMapper monthlyExpenseValueMapper;
    private final MonthlyReportProcess monthlyReportProcess;

    @Override
    public void accept(KStream<String, SentEvent> stream) {
        stream.flatMapValues(monthlyExpenseValueMapper)
                .foreach(monthlyReportProcess);
    }
}
