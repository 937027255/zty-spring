package com.zty.springframework.context.event;

import com.zty.springframework.context.ApplicationEvent;
import com.zty.springframework.context.ApplicationListener;

/**
 *
 * @description 事件广播器
 * @date 2022/3/13
 *  /CodeDesignTutorials
 *
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);

}
