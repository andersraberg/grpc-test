package com.example.hello;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        GreeterGrpc.GreeterBlockingStub stub =
                GreeterGrpc.newBlockingStub(channel);

        HelloReply response = stub.sayHello(
                HelloRequest.newBuilder()
                        .setName("Alice")
                        .build()
        );

        System.out.println(response.getMessage());

        Reply2 reply2 = stub.sayHello2(Hello2.newBuilder().setMsg("tjipp").setValue(42).build());

        System.out.println(reply2.getOk());
        channel.shutdown();
    }
}
