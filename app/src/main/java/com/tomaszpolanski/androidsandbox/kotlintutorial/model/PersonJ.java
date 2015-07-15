package com.tomaszpolanski.androidsandbox.kotlintutorial.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public final class PersonJ {

    @NonNull
    private final String name;
    @NonNull
    private final String lastName;
    private final int age;
    @Nullable
    private final String job;

    public PersonJ(@NonNull final String name,
                   @NonNull final String lastName,
                   final int age,
                   @Nullable final String job) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.job = job;

    }

    @NonNull
    public String getLastName( ) {
        return lastName;
    }

    public int getAge( ) {
        return age;
    }

    @Nullable
    public String getJob( ) {
        return job;
    }

    @NonNull
    public String getName( ) {

        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass( ) != o.getClass( )) return false;

        PersonJ personJ = (PersonJ) o;

        if (age != personJ.age) return false;
        if (!name.equals(personJ.name)) return false;
        if (!lastName.equals(personJ.lastName)) return false;
        return !( job != null ? !job.equals(personJ.job) : personJ.job != null );

    }

    @Override
    public int hashCode( ) {
        int result = name.hashCode( );
        result = 31 * result + lastName.hashCode( );
        result = 31 * result + age;
        result = 31 * result + ( job != null ? job.hashCode( ) : 0 );
        return result;
    }

    @Override
    public String toString( ) {
        return "PersonJ{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}
