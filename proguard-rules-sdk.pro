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
