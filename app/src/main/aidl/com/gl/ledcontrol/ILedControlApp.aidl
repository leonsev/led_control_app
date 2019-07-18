// ILedControlApp.aidl
package com.gl.ledcontrol;

// Declare any non-default types here with import statements

interface ILedControlApp {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   /* void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
*/

    /**
     * LedControl initialization
     *
     * @return result 0 if successful, nonzero otherwise.
     */
     int initLedControl();


    /**
     * LedControl setLedState
     *
     * @return result 0 if successful, nonzero otherwise.
     */
     int setLedState(int id, boolean status);


    /**
     * LedControl terminate
     *
     * @return result 0 if successful, nonzero otherwise.
     */
     int terminateLedState();
}
