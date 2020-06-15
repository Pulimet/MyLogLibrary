# MyLog - Library
Simple android logger (2020)

Java version: 
[ ![Download](https://api.bintray.com/packages/pulimet/utils/mylog/images/download.svg) ](https://bintray.com/pulimet/utils/mylog/_latestVersion)

Kotlin version: 
[ ![Download](https://api.bintray.com/packages/pulimet/utils/mylogkt/images/download.svg) ](https://bintray.com/pulimet/utils/mylogkt/_latestVersion)


 [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MyLog-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6422)      <a href="http://www.methodscount.com/?lib=net.alexandroid.utils%3Amylog%3A1.1"><img src="https://img.shields.io/badge/Methods and size-23 | 3 KB-e91e63.svg"/></a> [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0) 


# Installation

- Add the dependency from jCenter to your app's (not project) build.gradle file:

```sh
repositories {
    jcenter()
}

dependencies {
    implementation 'net.alexandroid.utils:mylog:1.5'

    // Kotlin version
    implementation 'net.alexandroid.utils:mylogkt:1.12'
}
```

- Consider adding following proguard rule:
```sh
# Remove all log* methods from prpject
-assumenosideeffects class net.alexandroid.utils.mylogkt.MyLogKtKt { *; }
# Remove specific logger methods
-assumenosideeffects class net.alexandroid.utils.mylogkt.MyLogKtKt {
    public static *** logD$default(...);
    public static *** logW$default(...);
}
```

- Next:

```sh
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Java (Mandatory)
        MyLog.init(this, "MyLog", BuildConfig.DEBUG); // Context,  Tag,   Show logs?
        
        //Kotlin from Java (Optional, to allow isPackageNameVisible = true)
        MyLogKt.INSTANCE.setPackageName(getPackageName());
        
        // Kotlin from Kotlin (Optional, to allow isPackageNameVisible = true)
        MyLogKt.packageName = packageName
        MyLogKt.isLogsShown = BuildConfig.DEBUG

        // Other Kotlin version configurations
        MyLogKt.packageName = ""
        MyLogKt.isLogsShown = true
        MyLogKt.isThreadNameVisible = false
        MyLogKt.isTimeVisible = true
        MyLogKt.isPackageNameVisible = false
        MyLogKt.isClassNameVisible = true
        MyLogKt.isMethodNameVisible = true
        MyLogKt.isLengthShouldWrap = true

        MyLogKt.classNameLength = 15
        MyLogKt.packageAndClassNameLength = 35
        MyLogKt.methodNameLength = 15
        MyLogKt.threadNameLength = 6
        MyLogKt.timeFormat = "HH:mm:ss.SSS"
    }
}
```

* Also don't forget to add: android:name=".MyApplication" at your application tag in AndroidManifest.xml
```sh
 <application
        android:name=".MyApplication"
        ...>
```

# How I use it

```sh
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    // Java lib
    MyLog.setTag("STATIC CUSTOM TAG");
    MyLog.d("Empty 1");
    MyLog.i("Empty 2");
    MyLog.w("Empty 3");
    MyLog.e("Empty 4");

    MyLog.d("CustomTag", "Custom tag example message");

    MyLog.e("Show Exception", new NullPointerException());
    
    // Kotlin lib
    MyLogKt.tag = "STATIC CUSTOM TAG"
    logD("Empty 1")
    logI("Empty 2")
    logW("Empty 3")
    logE("Empty 4")

    logD("Custom tag example 1", "CustomTag1")

    logE("Show Exception", t = NullPointerException())
}
```    

# Release notes
1.12(kotlin version) - Allow more configurations

1.11(kotlin version) - Refactoring, classes/methods wrapping

1.9 (kotlin version) - Allow logging functions to be empty

1.8 (kotlin version) - Remove init, thread name instead of tread id

1.7 (kotlin version) - Koltin library bug fixes

1.5 - Bug fix + Kotlin version

1.4 - Custom tag support<br>

1.3 - AndroidX migration, Target 29<br>

1.2 - Add MyLog.e(String msg, Throwable t) - (by @davidHarush), 2 new constructors


## Stage 1 - Remove logcat headers
Usualy logs look like below:  <br> 
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/1.PNG">

First go to Configure Logcat Header:  <br> 
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/2.PNG">

Uncheck all options and press OK:  <br> 
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/3.PNG">

Now your logs looks like below:   <br> 
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/4.PNG">

## Stage 2 - Customize logs color scheme
File -> Settings -> Editor -> Colors & Fonts -> Android Logcat
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/5.PNG">

## Stage 3 - Library customization
Default: <br> 
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/6.PNG">

```sh
MyLog.setPackageNameVisibility(true); 
MyLogKt.isPackageNameVisible = true
```
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/7.PNG">

```sh
MyLog.setIsTimeVisible(false);
MyLogKt.isTimeVisible = false
```
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/8.PNG">

```sh
MyLog.setThreadIdVisibility(true); 
MyLogKt.isThreadIdVisible = true
```
<img src="https://raw.githubusercontent.com/Pulimet/MyLogLibrary/master/art/9.PNG">

 <br>  <br>  <br> 
# License
```
Copyright 2017 Alexey Korolev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
