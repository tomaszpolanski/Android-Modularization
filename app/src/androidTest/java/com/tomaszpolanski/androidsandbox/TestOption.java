package com.tomaszpolanski.androidsandbox;



import com.tomaszpolanski.androidsandbox.models.Errors.detail.ArgumentError;
import com.tomaszpolanski.androidsandbox.utils.SimpleTestCase;
import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.option.OptionUnsafe;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

public class TestOption extends SimpleTestCase {


    public void testOfObjSome() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj(str);

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }

    public void testOfObjNone() throws Exception {

        final String str = null;
        Option<String> op = Option.ofObj(str);

        assertFalse(op.getIsSome());
    }

    public void testMapSome() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj("")
                .map(__ -> str);

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }

    public void testMapNone() throws Exception {

        Option<String> op = Option.ofObj((String) null)
                .map(__ -> "");

        assertFalse(op.getIsSome());
    }


    public void testFilterSome() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj(str)
                .filter(val -> val.equals(str));

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }

    public void testFilterSomeFailed() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj(str)
                .filter(val -> val.equals(""));

        assertFalse(op.getIsSome());
    }

    public void testFilterNone() throws Exception {

        Option<String> op = Option.ofObj((String) null)
                .filter(val -> val.equals(""));

        assertFalse(op.getIsSome());
    }

    public void testFlatMapSome() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj("")
                .flatMap(val -> Option.ofObj(str));

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }

    public void testFlatMapSomeFailed() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj(str)
                .flatMap(val -> Option.ofObj((String) null));

        assertFalse(op.getIsSome());
    }

    public void testFlatMapNone() throws Exception {

        Option<String> op = Option.ofObj((String) null)
                .flatMap(val -> Option.ofObj(""));

        assertFalse(op.getIsSome());
    }

    public void testOrOptionSome() throws Exception {

        final String str = "Something";
        Option<String> op = Option.ofObj(str)
                .orOption(() -> Option.ofObj(""));

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }


    public void testOrOptionNone() throws Exception {
        final String str = "Something";
        Option<String> op = Option.ofObj((String) null)
                .orOption(() -> Option.ofObj(str));

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }

    public void testOrDefaultSome() throws Exception {

        final String str = "Something";
        String s = Option.ofObj(str)
                .orDefault(() -> "");

        assertEquals(str, s);
    }


    public void testOrDefaultNone() throws Exception {
        final String str = "Something";
        String s = Option.ofObj((String) null)
                .orDefault(() -> str);

        assertEquals(str, s);
    }


    public void testTryAsOptionSome() throws Exception {

        final String str = "Something";
        Option<String> op = Option.tryAsOption(() -> str);

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }


    public void testTryAsOptionNone() throws Exception {
        final Integer str = null;
        Option<String> op = Option.tryAsOption(() -> str.toString());

        assertFalse(op.getIsSome());
    }
    public void testToResultSome() throws Exception {

        final String str = "Something";
        Result<String> re = Option.ofObj(str)
                .toResult(new ArgumentError("Invalid args"));

        assertTrue(re.getIsSuccess());
        assertEquals(str, re.getUnsafe());
    }

    public void testToResultNone() throws Exception {

        final String error = "error";
        Result<String> re = Option.ofObj((String)null)
                .toResult(new ArgumentError(error));

        assertFalse(re.getIsSuccess());
        assertEquals(error, re.getMessage().getMessage());
    }


}
