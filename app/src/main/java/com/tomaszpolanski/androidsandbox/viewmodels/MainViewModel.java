package com.tomaszpolanski.androidsandbox.viewmodels;

import com.tomaszpolanski.androidsandbox.kotlintutorial.model.PersonJ;
import com.tomaszpolanski.androidsandbox.kotlintutorial.model.PersonK;

public class MainViewModel extends BaseViewModel {


    public MainViewModel() {
        primitiveObsession();
    }


    private static void primitiveObsession() {
        PersonJ personj = new PersonJ("Joe", "Smith", 25, null);
        PersonK personK = new PersonK("Jane", "Smith", 26, null);

        String perJ = personj.toString();
        String perK = personK.toString();
    }
}
