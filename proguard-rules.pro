# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Volumes/Data/Android/SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Retrofit 2.x
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp3
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Jackson 2.x
-keep class com.fasterxml.jackson.databind.ObjectMapper {
    public <methods>;
    protected <methods>;
}
-keep class com.fasterxml.jackson.databind.ObjectWriter {
    public ** writeValueAsString(**);
}
-keep class com.fasterxml.** { *; }
-keep class com.fasterxml.jackson.databind.introspect.**

-keepclassmembers public final enum com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility {
    public static final com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility *;
}

-keepnames class com.fasterxml.jackson.** { *; }
-keepattributes Annotation,EnclosingMethod,Signature
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry
-dontwarn com.fasterxml.jackson.databind.introspect.**
-dontwarn com.fasterxml.jackson.databind.*
-dontwarn java.nio.file.**
-dontwarn java.beans.**

# SDK #
-keep public class * {
      public *;
}

# Native
-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

# Models
-keep,allowobfuscation class com.venomvendor.sdk.wordpress.network.**

# RestClient
-keepclasseswithmembers,allowobfuscation class com.venomvendor.sdk.wordpress.** {
    @retrofit2.http.* <methods>;
}
