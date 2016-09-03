package com.joketeller.android;

public class JokeTeller {


    static String[] mJokes=new String[]{
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all."
            ,"Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
            "\n" +
            "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
            "\n" +
            "Doctor: \"Nine.\"\n" +
            "\n" +
            "More jokes at: http://www.short-funny.com/#ixzz4Iz9MvH40"
            ,"A man asks a farmer near a field, “Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.”\n" +
            "\n" +
            "The farmer says, “Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.” ",
            " Anton, do you think I’m a bad mother?\n" +
                    "\n" +
                    "My name is Paul.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.\n" +
                    "\n"

            ,"Mother, “How was school today, Patrick?”\n" +
            "\n" +
            "Patrick, “It was really great mum! Today we made explosives!”\n" +
            "\n" +
            "Mother, “Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?”\n" +
            "\n" +
            "Patrick, “What school?”"
    };

    static  int mCounter;


    public static String tellMeJoke(){

        return mJokes[(mCounter++)%mJokes.length];
    }



}
