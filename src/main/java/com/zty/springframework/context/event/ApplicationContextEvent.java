package com.zty.springframework.context.event;

import com.zty.springframework.context.ApplicationContext;
import com.zty.springframework.context.ApplicationEvent;

/**
 *
 * @description Base class for events raised for an <code>ApplicationContext</code>.
 * @date 2022/3/13
 *  /CodeDesignTutorials
 *
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
