package com.tomaszpolanski.androidsandbox.utils;



import com.tomaszpolanski.androidsandbox.utils.option.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import rx.functions.Func1;
import rx.functions.Func2;

public class Linq<T> extends ArrayList<T> {


    public static <T>  Linq<T> create (T arg) {
        Linq<T> list = new Linq<>();
        list.add(arg);
        return list;
    }

    /**
     * Creates Linq object from given @iterator
     *
     * @param iterator Iterator pointing to items
     * @return Newly constructed Linq object
     */
    public static <T> Linq<T> create(Iterator<T> iterator) {
        Linq<T> list = new Linq<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Checks if any elements in the list fulfils the predicate
     *
     * @param self      List to be checked
     * @param predicate Expression which is checked
     * @return true if check was fulfilled or not
     */
    public static <T> boolean any(final Iterable<T> self, final Func1<T, Boolean> predicate) {
        for (T item : self) {
            if (predicate.call(item)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Checks if any elements in the list fulfils the predicate
     *
     * @param predicate Expression which is checked
     * @return true if check was fulfilled or not
     */
    public boolean any(final Func1<T, Boolean> predicate) {
        return any(this, predicate);
    }

    public boolean any() {
        return any(this, t -> true);
    }

    public static <T> boolean all(final Iterable<T> self, final Func1<T, Boolean> predicate) {
        return !any(self, item -> !predicate.call(item));
    }

    public boolean all(final Func1<T, Boolean> predicate) {
        return all(this, predicate);
    }

    /**
     * Filters the given list according to the check.
     *
     * @param self      List to be filtered
     * @param predicate Expression which is checked
     * @return Newly created list of items that fulfilled the check
     */
    public static <T> Linq<T> filter(final Iterable<T> self, final Func1<T, Boolean> predicate) {
        Linq<T> newList = new Linq<>();
        for (T item : self) {
            if (predicate.call(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

    /**
     * Filters the given list according to the check.
     *
     * @param predicate Expression which is checked
     * @return Newly created list of items that fulfilled the check
     */
    public Linq<T> filter(final Func1<T, Boolean> predicate) {
        return filter(this, predicate);
    }

    /**
     * Returns the amount of items in the list that meets the check
     *
     * @param self      The list of items that should be counted
     * @param predicate Expression which is checked
     * @return Amount of items that fulfils the <code>predicate</code>
     */
    public static <T> int count(final Iterable<T> self, final Func1<T, Boolean> predicate) {
        return Linq.filter(self, predicate).size();
    }

    /**
     * Returns the amount of items in the list that meets the check
     *
     * @param predicate Expression which is checked
     * @return Amount of items that fulfils the <code>predicate</code>
     */
    public int count(final Func1<T, Boolean> predicate) {
        return count(this, predicate);
    }

    /**
     * Converts every item in the list to new object by using given function
     *
     * @param self     List of items that will be converted
     * @param selector The function that will create new items based on old ones
     * @param <T>      Old item type
     * @param <R>      New item type
     * @return Newly created list of items that were created using the <code>selector</code>
     * function
     */
    public static <T, R> Linq<R> map(final Iterable<T> self, final Func1<T, R> selector) {
        Linq<R> newList = new Linq<>();
        for (T item : self) {
            newList.add(selector.call(item));
        }
        return newList;
    }

    /**
     * Converts every item in the list to new object by using given function and passes current
     * index
     *
     * @param self     List of items that will be converted
     * @param selector The function that will create new items based on old ones, also contains
     *                 current index
     * @param <T>      Old item type
     * @param <R>      New item type
     * @return Newly created list of items that were created using the <code>selector</code>
     * function
     */
    public static <T, R> Linq<R> map(final Iterable<T> self, final Func2<T, Integer, R> selector) {
        Linq<R> newList = new Linq<>();
        Integer i = 0;
        for (T item : self) {
            newList.add(selector.call(item, i++));
        }
        return newList;
    }

    /**
     * Converts every item in the list to new object by using given function
     *
     * @param selector The function that will create new items based on old ones
     * @param <R>      New item type
     * @return Newly created list of items that were created using the <code>selector</code>
     * function
     */
    public <R> Linq<R> map(final Func1<T, R> selector) {
        return map(this, selector);
    }

    /**
     * Converts every item in the list to new object by using given function and passes current
     * index
     *
     * @param selector The function that will create new items based on old ones, also contains
     *                 current index
     * @param <R>      New item type
     * @return Newly created list of items that were created using the <code>selector</code>
     * function
     */
    public <R> Linq<R> map(final Func2<T, Integer, R> selector) {
        return map(this, selector);
    }

    /**
     * Creates new list by appending given list to current one
     *
     * @param second List to be appended
     * @return Newly created list containing <code>second</code> appended
     */
    public Linq<T> concat(final List<T> second) {
        Linq<T> newList = new Linq<>();
        newList.addAll(this);
        newList.addAll(second);
        return newList;
    }

    /**
     * Creates new list by prepended given list to current one
     *
     * @param first List to be prepended to current one
     * @return Newly created list containing <code>first</code> prepended
     */
    public Linq<T> reverseConcat(final Linq<T> first) {
        return first.concat(this);
    }

    /**
     * Returns a list that applies a function of your choosing to the first item returned by a source,
     * then feeds the result of that function along with the second item returned by the source
     * into the same function, and so on until all items have been returned by the source,
     * and returns the final result from the final call to your function as its sole item.
     * <p>
     * This technique, which is called "reduce" here, is sometimes called "aggregate," "fold," "accumulate,"
     * "compress," or "inject" in other programming contexts. Groovy, for instance, has an {@code inject} method
     * that does a similar operation on lists.
     *
     * @param accumulator an accumulator function to be invoked on each item returned by the source, whose
     *                    result will be used in the next accumulator call
     * @param source      items to be reduced
     * @return a single item that is the result of accumulating the items returned by
     * the source
     * @throws IllegalArgumentException if the source list has no items
     */
    public static <T> T reduce(final Iterable<T> source, Func2<T, T, T> accumulator) throws IllegalArgumentException {
        T aggregate = null;
        for (T arg : source) {
            if (aggregate == null) {
                aggregate = arg;
            } else {

                aggregate = accumulator.call(aggregate, arg);
            }
        }
        if (aggregate == null) {
            throw new IllegalArgumentException("Given list cannot be empty");
        }
        return aggregate;
    }

    /**
     * Non static version of @reduce
     *
     * @param accumulator an accumulator function to be invoked on each item returned by the source, whose
     *                    result will be used in the next accumulator call
     * @return a single item that is the result of accumulating the items returned by
     * the source
     * @throws IllegalArgumentException if the source list has no items
     */
    public T reduce(Func2<T, T, T> accumulator) throws IllegalArgumentException {
        return reduce(this, accumulator);
    }

    public static <T> Option<T> last(final Iterable<T> source, final Func1<T, Boolean> predicate) {
        Option<T> option = Option.NONE;
        for (T value : source) {
            if (predicate.call(value)) {
                option = Option.asOption(value);
            }
        }
        return option;
    }

    public Option<T> last(final Func1<T, Boolean> predicate) {
        return last(this, predicate);
    }


    public static <T> Option<T> last(final Iterable<T> source) {
        return last(source, val -> true);
    }

    public Option<T> last() {
        return last(this);
    }


    /**
     * Returns an Option of first items in @source that fulfils the @predicate, if no item is found,
     * return Option.None
     *
     * @param source    Items on which predicate will be run
     * @param predicate Expression which is checked
     * @return Single Option value
     */
    @SuppressWarnings("unchecked")
    public static <T> Option<T> first(final Iterable<T> source, final Func1<T, Boolean> predicate) {
        for (T value : source) {
            if (predicate.call(value)) {
                return Option.asOption(value);
            }
        }
        return Option.NONE;
    }

    /**
     * Non static version of @first
     *
     * @param predicate Expression which is checked
     * @return Single Option value
     */
    public Option<T> first(final Func1<T, Boolean> predicate) {
        return first(this, predicate);
    }

    /**
     * Returns Some of first element in @source, if empty, returns None
     *
     * @return Single Option value
     */
    public static <T> Option<T> first(final Iterable<T> source) {
        return first(source, val -> true);
    }

    /**
     * Non static version of @first
     *
     * @return Single Option value
     */
    public Option<T> first() {
        return first(this);
    }

    /**
     * Returns list of items from the @source that of given @type
     *
     * @param source List to be filtered
     * @param type   Type of items we want to get from lit @source
     * @param <T>    Input type of elements in @source
     * @param <R>    Result type of items
     * @return Filtered list
     */
    public static <T, R> Linq<R> ofType(final Iterable<T> source, final Class<R> type) {
        return Linq.filter(source, type::isInstance).map(type::cast);
    }

    /**
     * Non static version of @ofType
     *
     * @param type Type of items we want to get from lit @source
     * @param <R>  Result type of items
     * @return Filtered list
     */
    public <R> Linq<R> ofType(final Class<R> type) {
        return ofType(this, type);
    }

    @SuppressWarnings("unchecked")
    public Option<T> getOption(int index) {
        try {
            return Option.asOption(get(index));
        } catch (IndexOutOfBoundsException e) {
            return Option.NONE;
        }

    }

    public Linq<List<T>> buffer(int count, int skip) {
        Linq<List<T>> result = new Linq<>();
        List<T> buffer = new ArrayList<>(count);
        int skipped = 0;
        while(skipped < size()) {
            int position = skipped;
            while (buffer.size() < count && position < size()) {
                buffer.add(get(position++));
            }
            result.add(buffer);
            buffer = new ArrayList<>(count);
            skipped += skip;
        }
        return result;
    }

    @Override
    public String toString() {
        return "[" + map(Object::toString).reduce((f, s) -> f + ", " + s) + "]";
    }
}
