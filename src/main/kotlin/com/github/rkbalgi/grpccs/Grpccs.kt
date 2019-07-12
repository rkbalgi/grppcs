//@file:JvmName("GrpcsKt")
package com.github.rkbalgi.grpccs

import com.github.rkbalgi.grpccs.proto.S1Grpc
import com.github.rkbalgi.grpccs.utils.TestJava

fun main(args: Array<String>):Unit{

    TestJava().funcA()

    println(S1Grpc.getDoMethod().fullMethodName)

    println("Hello World\n")
}