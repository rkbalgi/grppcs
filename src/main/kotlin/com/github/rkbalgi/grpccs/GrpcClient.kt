package com.github.rkbalgi.grpccs

import com.github.rkbalgi.grpccs.proto.S1Grpc
import com.github.rkbalgi.grpccs.proto.Test
import io.grpc.ManagedChannelBuilder

fun main(args: Array<String>): Unit {
    val req = Test.Request.newBuilder().setId("ABCD").setType("normal").build();

    val channel = ManagedChannelBuilder.forAddress("localhost", 9898).usePlaintext().build();
    val stub = S1Grpc.newBlockingStub(channel)
    val resp = stub.do_(req)

    println("Received - ${resp.code} and ${String(resp.data.toByteArray())}")

}