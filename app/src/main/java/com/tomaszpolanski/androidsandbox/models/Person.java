package com.tomaszpolanski.androidsandbox.models;

        import com.tomaszpolanski.androidsandbox.utils.option.Option;

public class Person {

    public final String Name;

    private Person(String name) {
        Name = name;
    }

    public static Option<Person> create(final String name) {
        return Option.asOption(name)
                     .filter(personName -> personName.length() != 0)
                     .filter(personName -> personName.length() < 20)
                     .map(personName -> new Person(personName));
    }
}
