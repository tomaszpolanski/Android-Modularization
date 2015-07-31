package com.tomaszpolanski.androidsandbox.models;

import com.tomaszpolanski.androidsandbox.utils.option.Option;

public class PersonDetails {
    public final String FirstName;
    public final String LastName;
    public final Option<PersonDetails> Boss;

    private PersonDetails(final String firstName,
                          final String lastName,
                          final Option<PersonDetails> boss) {
        FirstName = firstName;
        LastName = lastName;
        Boss = boss;
    }

    public static Option<PersonDetails> create(final String firstName,
                                               final String lastName,
                                               final Option<PersonDetails> significantOther) {
        return Option.ofObj(firstName)
                     .flatMap(first -> Option.ofObj(lastName)
                                             .map(last -> new PersonDetails(first, last, significantOther)));
    }

    public static Option<PersonDetails> createCEO(final String firstName,
                                                  final String lastName) {
        return create(firstName, lastName, Option.NONE);
    }
}
