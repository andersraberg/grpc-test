package com.example.hello;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(50051)
                .addService(new GreeterService())
                .build();

        server.start();
        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }

    static class GreeterService extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(
                HelloRequest request,
                StreamObserver<HelloReply> responseObserver) {

            HelloReply reply = HelloReply.newBuilder()
                    .setMessage("Hello, " + request.getName() + "!")
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void sayHello2(
                Hello2 request,
                StreamObserver<Reply2> responseObserver) {

            Reply2 reply = Reply2.newBuilder()
                    .setOk(true)
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
