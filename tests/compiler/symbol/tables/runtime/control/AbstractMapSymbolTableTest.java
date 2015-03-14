package compiler.symbol.tables.runtime.control;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.symbol.tables.InstantiableSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;
import test.TestBase;

import static org.junit.Assert.assertEquals;

/**
 * Created by dbborens on 3/13/15.
 */
public class AbstractMapSymbolTableTest<T extends MapSymbolTable> extends TestBase {
    protected T query;

    /**
     * Perform an integration test for a member returning a list symbol table by
     * comparing the result's behavior to a known subclass of the list's type.
     *
     */
    protected void doListSymbolTableTest(String memberName, String instanceName, Class expected) {
        ResolvingSymbolTable adapter = query.getSymbolTable(memberName);
        ASTReferenceNode value = new ASTReferenceNode(instanceName);

        ListSymbolTable resolver = (ListSymbolTable) adapter.getSymbolTable(value);
        InstantiableSymbolTable actual = resolver.getSymbolTable(value);
        assertEquals(expected, actual.getClass());
    }
}
