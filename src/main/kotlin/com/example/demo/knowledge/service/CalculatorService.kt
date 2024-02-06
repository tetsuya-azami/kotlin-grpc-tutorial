package com.example.demo.knowledge.service

import com.example.demo.knowledge.proto.CalculatorGrpc
import com.example.demo.knowledge.proto.CalculatorOuterClass
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class CalculatorService: CalculatorGrpc.CalculatorImplBase() {
    override fun add(
        request: CalculatorOuterClass.AdditionRequest?,
        responseObserver: StreamObserver<CalculatorOuterClass.AdditionReply>?
    ) {
        val replyBuilder = CalculatorOuterClass.AdditionReply.newBuilder().setResult(request?.added?.plus(request.add) ?: 0)
        responseObserver?.onNext(replyBuilder.build())
        responseObserver?.onCompleted()
    }
}