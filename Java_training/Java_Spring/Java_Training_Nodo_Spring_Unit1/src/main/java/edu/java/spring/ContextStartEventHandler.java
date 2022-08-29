package edu.java.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import java.awt.event.ActionListener;

public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {


    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("Context started at " + contextStartedEvent.getTimestamp());
    }
}
