package com.nixcraft.employee_management.kafka.producer;

import com.nixcraft.employee_management.kafka.event.EmployeeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer
{
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    private static final String TOPIC = "employee-topic";
    public void publishEmployeeCreated(EmployeeEvent event)
    {
        kafkaTemplate.send(TOPIC, event.getId().toString(), event);
    }
}
