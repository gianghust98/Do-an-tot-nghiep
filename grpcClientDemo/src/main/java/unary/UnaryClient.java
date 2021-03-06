package unary;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import unary.UnaryGrpc.UnaryBlockingStub;
import unary.UnaryOuterClass.Message;
import unary.UnaryOuterClass.Message.Builder;
import unary.UnaryOuterClass.MessageResponse;

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