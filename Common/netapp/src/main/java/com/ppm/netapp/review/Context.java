package com.ppm.netapp.review;

public class Context {

    // Context->ContextImpl, ContextWrapper
    // ContextWrapper->ContextThemeWrapper, Service, Application
    //ContextThemeWrapper->Activity

    public interface InterF {

        default void setS() {
        }
    }

    public class Impl implements InterF {

    }

    static {

    }

}
