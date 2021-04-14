package com.webtoeic.unary;

import com.webtoeic.unary.UnaryGrpc.UnaryBlockingStub;
import com.webtoeic.unary.UnaryOuterClass.Message;
import com.webtoeic.unary.UnaryOuterClass.MessageResponse;
import com.webtoeic.unary.UnaryOuterClass.Message.Builder;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UnaryClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50051).usePlaintext().build();
		
		// stubs - generate from proto
		
		UnaryBlockingStub unaryStub = UnaryGrpc.newBlockingStub(channel);
		Message msg = Message.newBuilder().setMessage("hello").build();
		MessageResponse msgresponse = unaryStub.getServerResponse(msg);
		System.out.println(msgresponse.getMessage());
		System.out.println(msgresponse.getReceived());
	}

}