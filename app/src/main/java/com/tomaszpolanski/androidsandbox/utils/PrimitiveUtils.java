package com.tomaszpolanski.androidsandbox.utils;
;import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

public final class PrimitiveUtils {

    public static Option<Double> parseDouble(String string) {
            return parseDoubleToResult(string).asOption();
    }

    public static Result<Double> parseDoubleToResult(String string) {
        return Result.tryAsResult(() -> Double.parseDouble(string));
    }

}
