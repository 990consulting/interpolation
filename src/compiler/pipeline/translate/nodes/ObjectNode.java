/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import java.util.function.Consumer;

/**
 * Created by dbborens on 2/22/15.
 */
public interface ObjectNode extends Resolvable {

    public void instantiate(Consumer callback);

    /**
     * Reports the class of the object that will be instantiated by this class.
     * @return
     */
    public Class getInstanceClass();
}
