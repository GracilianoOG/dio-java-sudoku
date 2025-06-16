package br.com.gracilianoog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService {
    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
        put(EventEnum.CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscribe(EventEnum eventType, EventListener listener) {
        List<EventListener> subscribedListeners = listeners.get(eventType);
        subscribedListeners.add(listener);
    }

    public void notify(EventEnum eventType) {
        listeners.get(eventType).forEach(l -> l.update(eventType));
    }
}
