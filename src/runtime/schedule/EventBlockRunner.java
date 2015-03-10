/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

/**
 * Created by dbborens on 3/8/15.
 */
public class EventBlockRunner {
    private SchedulerContent content;
    public EventBlockRunner(SchedulerContent content) {
        this.content = content;
    }

    public void run(EventBlock block) {
         block.get().forEach(event -> {
             boolean stillActive = event.run();
             if (stillActive) {
                 content.add(event);
             }
         });
    }
}
