package com.tomaszpolanski.androidsandbox.kotlintutorial.services

import kotlin.platform.platformStatic




public object DeclarativeService {
    public platformStatic fun String.awesome() : String = "$this Awesome!"
    public platformStatic fun getSomeNumbers(): List<String> =
            sequenceOf(1..100).filter { num -> num % 2 == 0 }
                    .take(10)
                    .map { "Look! I got number $it!".awesome() }
                    .map { it to it.length() }
                    .map { "${it.first}: length: ${it.second}" }
                    .toList()


    public platformStatic fun doSomethingWithObjects( notNull: String,
                                                      maybeNull: String?) {
        notNull.length() // never will fail
       // maybeNull!!.length() // here's a gun
        val len = maybeNull?.length()
        if (maybeNull != null) {
            val leng = maybeNull.length()
        }
    }
}