package com.tomaszpolanski.androidsandbox;


import com.tomaszpolanski.androidsandbox.models.Errors.NullError;
import com.tomaszpolanski.androidsandbox.models.Errors.detail.ArgumentError;
import com.tomaszpolanski.androidsandbox.utils.SimpleTestCase;
import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.option.OptionUnsafe;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

public class TestResult extends SimpleTestCase {


    public void testOfObjSuccess() throws Exception {

        final String str = "Something";
        Result<String> re = Result.ofObj(str, "str");

        assertTrue(re.getIsSuccess());
        assertEquals(str, re.getUnsafe());
    }

    public void testOfObjFailure() throws Exception {

        final String str = null;
        Result<String> re = Result.ofObj(str, "str");

        assertFalse(re.getIsSuccess());
    }

    public void testMapSuccess() throws Exception {

        final String str = "Something";
        Result<String> re = Result.ofObj("", "")
                .map(__ -> str);

        assertTrue(re.getIsSuccess());
        assertEquals(str, re.getUnsafe());
    }

    public void testMapFailure() throws Exception {

        Result<String> re = Result.ofObj((String) null, "(String) null")
                .map(__ -> "");

        assertFalse(re.getIsSuccess());
    }


    public void testFilterSuccess() throws Exception {

        final String str = "Something";
        Result<String> re = Result.ofObj(str, "str")
                .filter(val -> val.equals(str), val -> new NullError(val));

        assertTrue(re.getIsSuccess());
        assertEquals(str, re.getUnsafe());
    }

    public void testFilterSomeFailed() throws Exception {

        final String str = "Something";
        final String error = "error";
        Result<String> re = Result.ofObj(str, new ArgumentError(str))
                .filter(val -> val.equals(""), val -> new ArgumentError(error));

        assertFalse(re.getIsSuccess());
        assertEquals(error, re.getMessage().getMessage());
    }

    public void testFilterFailure() throws Exception {

        Result<String> re = Result.ofObj((String) null, "(String) null")
                .filter(val -> val.equals(""), val -> new ArgumentError(val));

        assertFalse(re.getIsSuccess());
    }

    public void testFlatMapSuccess() throws Exception {

        final String str = "Something";
        Result<String> re = Result.ofObj("", "")
                .flatMap(val -> Result.ofObj(str, "str"));

        assertTrue(re.getIsSuccess());
        assertEquals(str, re.getUnsafe());
    }

    public void testFlatMapSomeFailed() throws Exception {

        final String str = "Something";
        Result<String> re = Result.ofObj(str, "str")
                .flatMap(val -> Result.ofObj((String) null, "(String) null"));

        assertFalse(re.getIsSuccess());
    }

    public void testFlatMapFailure() throws Exception {

        Result<String> re = Result.ofObj((String) null, "(String) null")
                .flatMap(val -> Result.ofObj("", ""));

        assertFalse(re.getIsSuccess());
    }

    public void testTryAsOptionSuccess() throws Exception {

        final String str = "Something";
        Result<String> re = Result.tryAsResult(() -> str);

        assertTrue(re.getIsSuccess());
        assertEquals(str, re.getUnsafe());
    }


    public void testTryAsOptionFailure() throws Exception {
        final Integer str = null;
        Result<String> re = Result.tryAsResult(() -> str.toString());

        assertFalse(re.getIsSuccess());
    }

    public void testToOptionSuccess() throws Exception {

        final String str = "Something";
        final Option<String> op = Result.ofObj(str, "str")
                .toOption();

        assertTrue(op.getIsSome());
        assertEquals(str, OptionUnsafe.getUnsafe(op));
    }

    public void testToOptionFailure() throws Exception {
        final Option<String> re = Result.ofObj((String) null, "(String) null")
                .toOption();

        assertFalse(re.getIsSome());
    }
}
