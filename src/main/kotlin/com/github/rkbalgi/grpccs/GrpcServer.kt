package com.github.rkbalgi.grpccs

import com.github.rkbalgi.grpccs.proto.S1Grpc
import com.github.rkbalgi.grpccs.proto.Test
import com.google.protobuf.ByteString
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

var server: Server? = null

class S1Service : S1Grpc.S1ImplBase() {


    override fun do_(request: Test.Request?, responseObserver: StreamObserver<Test.Response>?) {
        println("Received request - ${request.toString()}")

        val response = Test.Response.newBuilder().setCode(99).setData(ByteString.copyFrom("hello world".toByteArray()))
        responseObserver!!.onNext(response.build())
        responseObserver.onCompleted()


    }

}

fun startServer() {
    server = ServerBuilder.forPort(9898).addService(S1Service()).build();
    server!!.start()

}