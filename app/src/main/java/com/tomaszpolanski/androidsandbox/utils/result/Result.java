package com.tomaszpolanski.androidsandbox.utils.result;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tomaszpolanski.androidsandbox.models.Errors.ExceptionError;
import com.tomaszpolanski.androidsandbox.models.Errors.NullError;
import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;
import com.tomaszpolanski.androidsandbox.utils.Unit;
import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.option.OptionUnsafe;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public abstract class Result<T> {

    /**
     * Indicates if result contains value
     *
     * @return true if Result is Success, otherwise false
     */
    public abstract boolean getIsSuccess();

    /**
     * Converts inner value with @selector if value exists, otherwise does nothing
     *
     * @param selector Function that converts inner value
     * @return If value exists, returns converted value otherwise does nothing
     */
    @NonNull
    public abstract <OUT> Result<OUT> map(@NonNull final Func1<T, OUT> selector);

    /**
     * Binds result to another result
     *
     * @param selector Function that returns result to be bound to
     * @return Bound result
     */
    @NonNull
    public abstract <OUT> Result<OUT> flatMap(@NonNull final Func1<T, Result<OUT>> selector);

    /**
     * Filters results fulfilling given @predicate
     *
     * @param predicate Function returning true if the parameter should be included
     * @param failMessage Fail that should be used in case filtering fails
     * @return Success if the value checks the condition, otherwise Failure
     */
    @NonNull
    public abstract Result<T> filter(@NonNull final Func1<T, Boolean> predicate,
                                     @NonNull final Func1<T, ResultError> failMessage);

    /**
     * ATTENTION: Only use it when you know what you are doing!
     * Returns fail error if the result is Failure, otherwise throws an exception
     *
     * @return Error orResult throws exception
     */
    @NonNull
    abstract ResultError getMessageUnsafe();

    /**
     * Forsfully tries to unwrap the inner value.
     * <p>
     * Caution! Use this value only in special, justified cases!
     * Use @match instead.
     *
     * @return Value if exists, otherwise throws exception that shouldn't be caught
     * @throws IllegalStateException
     */
    @NonNull
    abstract T getUnsafe();

    /**
     * Returns result if current value is Failure
     *
     * @param f Function returning new Result
     * @return Result given by the function if current is Failure, otherwise returns current one
     */
    @NonNull
    public abstract Result<T> orResult(@NonNull final Func0<Result<T>> f);

    /**
     * Returns new Failure with @failure error
     * @param failure Error to be used to create Failure
     * @return Failure
     */
    @NonNull
    public static <A> Failure<A> failure(@NonNull final ResultError failure) {
        return new Failure<>(failure);
    }

    /**
     * Result created from given @value
     *
     * @param value Value that should be wrapped in an Result
     * @param valueName Message to be used in case @value being null
     * @return Success of the @value if it is not null, otherwise Failure with NullError as a failed
     * message
     */
    @NonNull
    public static <A> Result<A> ofObj(@Nullable final A value,
                                      @NonNull final String valueName) {
        return ofObj(value, new NullError(valueName));
    }

    /**
     * Result created from given @value
     *
     * @param value Value that should be wrapped in an Result
     * @param failMessage Error to be used in case @value fails to be converted
     * @return Success of the @value if it is not null, otherwise Failure with @failMessage error
     */
    @NonNull
    public static <A> Result<A> ofObj(@Nullable final A value,
                                      @NonNull final ResultError failMessage) {
        return ofOption(Option.ofObj(value), failMessage);
    }

    /**
     * Result created from given Option
     *
     * @param value Option that should be converted to Result
     * @param failMessage Error to be used in case @value is None
     * @return Success of the @value Some, otherwise Failure with @failMessage error
     */
    @NonNull
    public static <A> Result<A> ofOption(@NonNull final Option<A> value,
                                         @NonNull final ResultError failMessage) {
        return value.<Result<A>>match(
                success -> new Success(success),
                () -> failure(failMessage));
    }

    /**
     * Converts Result to a Option
     *
     * @return Some if the option is of Success, otherwise None
     */
    @NonNull
    public abstract Option<T> toOption();

    /**
     * Result of value returned by the function
     *
     * @param f Function that returns a value, that function could throw an exception
     * @return Result of a value returned by @f, if @f threw an exception, then returns Failure with
     * ExceptionError
     */
    @NonNull
    public static <A> Result<A> tryAsResult(@NonNull final Func0<A> f) {
        try {
            return Result.ofObj(f.call(), new NullError("Try as result error"));
        } catch (Exception e) {
            return Result.failure(new ExceptionError(e));
        }
    }

    /**
     * Matches current optional to Success orResult None and returns appropriate value
     *
     * @param fSuccess Function that will be called if value exists
     * @param fFailure Function that will be called if value does not exist
     * @return Value returned by either @fSuccess of @fFailure
     */
    @Nullable
    public abstract <OUT> OUT match(@NonNull final Func1<T, OUT> fSuccess,
                                    @NonNull final Func1<? super ResultError, OUT>  fFailure);

    /**
     * Matches current optional to Some or None and returns unit
     *
     * @param fSuccess Action that will be called if value exists
     * @param fFailure Action that will be called if value does not exist
     * @return Unit
     */
    @NonNull
    public abstract Unit matchAction(@NonNull final Action1<T> fSuccess,
                                     @NonNull final Action1<? super ResultError> fFailure);

    /**
     * Matches current optional to Success or Failure and returns appropriate value
     *
     * @param fSuccess Function that will be called if value exists
     * @param fFailure Function that will be called if value does not exist
     * @return Value returned by either @fSuccess of @fFailure
     */
    @Nullable
    public abstract <OUT> OUT matchUnsafe(@NonNull final Func1<T, OUT> fSuccess,
                                          @NonNull final Func1<? super ResultError, OUT> fFailure);

    /**
     * Combines given Options using @f
     *
     * @param resultIn Result that should be combined with current result
     * @param f       Function that combines all inner values of the results into one value
     * @return Result of Successful if all the Results were Successful, otherwise Failure
     */
    @NonNull
    public abstract <IN, OUT> Result<OUT> lift(@NonNull final Result<IN> resultIn,
                                               @NonNull final Func2<T, IN, OUT> f);

    /**
     * Identity function
     *
     * @return Current result
     */
    @NonNull
    public Result<T> id() {
        return this;
    }
}