cmake_minimum_required(VERSION 3.4.1)
add_library(wordpress SHARED src/main/jni/wordpress.c)
target_link_libraries(wordpress)
