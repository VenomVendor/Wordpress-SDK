cmake_minimum_required(VERSION 3.4.1)
add_library(wordpress-android SHARED src/main/jni/wordpress-android.c)
target_link_libraries(wordpress-android)
