

#MyLogKtKt
# Removes all the logging functions
-assumenosideeffects class net.alexandroid.utils.mylogkt.MyLogKtKt { *; }
# Removes only logD() and logV() function
-assumenosideeffects class net.alexandroid.utils.mylogkt.MyLogKtKt {
    public static *** logD$default(...);
    public static *** logV$default(...);
}