package com.nixcraft.employee_management.kafka.consumer;

import com.nixcraft.employee_management.kafka.event.EmployeeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class EmployeeConsumer
{

    private static final Logger log = LoggerFactory.getLogger(EmployeeConsumer.class);


    @KafkaListener(topics = "employee-topic", groupId = "employee-group")
    public void consume(EmployeeEvent event)
    {
        log.info("Received Event: " + event.getEventType()
                + " for Employee ID: " + event.getId());
    }
}
