/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build;

import compiler.pipeline.translate.nodes.ObjectNode;

import java.util.function.Consumer;

/**
 * Created by dbborens on 3/15/15.
 */
public interface Builder<S extends ObjectNode, T> {

    /**
     * Use the supplied ObjectNode to construct an instance of an
     * object of type T. The properties of the ObjectNode should be
     * fully determined; however, not all members need be defined
     * (constructed by their respective builders and ready for use).
     * Calls back to the consumer when all members have been defined
     * and the object has been instantiated.
     *
     * @param node
     * @param callback
     */
    public void visit(S node, Consumer<T> callback);

    /**
     * Checks the properties of the parents and compares them with
     * constraints specific to this object. Returns true iff the
     * existing properties of the parent do not violate any of the
     * constraints of this object. Note that parent properties which
     * have not been specified are not considered, as these do not
     * constrain the properties of the object and therefore cannot
     * be incompatible.
     *
     * @param parentNode
     * @return
     */
    public boolean validate(ObjectNode parentNode);
}
