package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.models.Person;
import com.tomaszpolanski.androidsandbox.models.PersonDetails;
import com.tomaszpolanski.androidsandbox.utils.Unit;
import com.tomaszpolanski.androidsandbox.utils.option.Option;
import com.tomaszpolanski.androidsandbox.utils.option.OptionUnsafe;

import rx.functions.Action1;

public class TestPerson {

    private static void print(final String message, final Person person) {
        System.console().printf(message + person.Name);
    }

    private static void printPerson(final String name) {
        Option<Person> personOption = Person.create(name);

        if (personOption != Option.NONE) {
            Person person = OptionUnsafe.getUnsafe(personOption);
            print("Person exits: ", person);
        } else {
            System.console().printf("Was not able to just person with name: " + name);
        }
    }


    private static void printPersonIfJohn(final String name) {
        Option<Person> johnOption = Person.create(name)
                                          .filter(personName -> personName.equals("John"));

        if (johnOption != Option.NONE) {
            Person person = OptionUnsafe.getUnsafe(johnOption);
            print("Hi John! ", person);
        } else {
            System.console().printf("That's not John: " + name);
        }
    }


    // Pattern matching

    private static void betterPrintPerson(final String name) {
        Person.create(name)
              .matchAction(
                      person -> print("Person exits: ", person),
                      () -> System.console().printf("Was not able to just person with name: " + name));
    }


    // More advance


    private static void printPersonDetails(final String first, final String last) {
        PersonDetails.createCEO(first, last)
                     .map(details -> "First name: " + details.FirstName + ", last name: " + details.LastName)
                     .iter(formattedDetails -> System.console().printf(formattedDetails));
    }

    private static String getBoss(Option<PersonDetails> firstPersonOption) {
        return firstPersonOption.flatMap(firstPerson -> firstPerson.Boss
                .map(secondPerson -> "Boss: " + secondPerson.FirstName + ", " + secondPerson.LastName))
                                .orDefault(() -> "No one here");
    }


    public static void main() {
        printPerson("Kevin"); // valid
        printPerson(""); //too short
        printPerson(null); // null
        printPerson("kalsdjfkljqewkfjadslkjfkleqjwjkdlsjflkasdjfkldjsaklfjadslkfjasdklfjasdlfjksd"); // too long

        printPersonIfJohn("Mike");
        printPersonIfJohn("John");

        betterPrintPerson("Tomek");
        betterPrintPerson("");
        betterPrintPerson(null);

        printPersonDetails("John", "Doe");
        printPersonDetails("John", null);

        Option<PersonDetails> boss = PersonDetails.createCEO("Bill", "Gates");
        Option<PersonDetails> john = PersonDetails.create("John", "Doe", boss);

        System.console().printf(getBoss(john));
        System.console().printf(getBoss(boss));
    }

}