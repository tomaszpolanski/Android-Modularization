package com.tomaszpolanski.androidsandbox.viewmodels;

import com.tomaszpolanski.androidsandbox.kotlintutorial.model.PersonJ;
import com.tomaszpolanski.androidsandbox.kotlintutorial.model.PersonK;
import com.tomaszpolanski.androidsandbox.kotlintutorial.services.DeclarativeService;

import java.util.List;

public class MainViewModel extends BaseViewModel {


    public MainViewModel() {
        primitiveObsession();
        declarativeCode();
        interop();
    }

    private static void interop() {
        String something = "Test";
        DeclarativeService.awesome(something);
        DeclarativeService.doSomethingWithObjects("asdfasdf", "kk");
    }

    private static void declarativeCode() {
        final List<String> list = DeclarativeService.getSomeNumbers();
    }

    private static void primitiveObsession() {
        PersonJ personj = new PersonJ("Joe", "Smith", 25, null);
        PersonK personK = new PersonK("Jane", "Smith", 26, null);

        String perJ = personj.toString();
        String perK = personK.toString();
    }
}
