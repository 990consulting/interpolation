/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import java.util.function.Supplier;

/**
 * Created by dbborens on 2/18/15.
 */
public class Symbol {

    private Supplier<TranslationHelper> translatorSupplier;
    private String description;

    public Symbol(Supplier<TranslationHelper> translatorSupplier,
                  String description) {

        this.translatorSupplier = translatorSupplier;
        this.description = description;
    }

    public TranslationHelper getTranslator() {
        return translatorSupplier.get();
    }

    public String getDescription() {
        return description;
    }
}
