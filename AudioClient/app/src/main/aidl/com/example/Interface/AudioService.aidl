// PictureAudioInterface.aidl
package com.example.Interface;

// Declare any non-default types here with import statements

interface AudioService {
    void playAudio1();
    void playAudio2();
    void playAudio3();
    void playAudio4();
    void playAudio5();
    void pauseAudio();
    void resumeAudio();
    void stopAudio();


        /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         */
        //void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
}
