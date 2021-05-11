package com.webtoeic.grpc;

import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;
import com.webtoeic.grpc.transferimg.GreeterGrpc;
import com.webtoeic.grpc.transferimg.GreeterGrpc.GreeterBlockingStub;
import com.webtoeic.grpc.transferimg.ImgAuthRequest;
import com.webtoeic.grpc.transferimg.ImgReply;
import com.webtoeic.grpc.transferimg.ImgUploadRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class TransferStudentInfoClient {
	ManagedChannel managedChannel = ManagedChannelBuilder
	        .forAddress("localhost", 9999).usePlaintext().build();

	    GreeterBlockingStub greetStub = GreeterGrpc.newBlockingStub(managedChannel);
	    public String ImgRegister(long id, byte[] photo) {
			  ByteString bString = ByteString.copyFrom(photo);
			  ImgUploadRequest request = ImgUploadRequest.newBuilder().setId(id).setPhoto(bString).build();
			  ImgReply imgReply = null;
			  imgReply = greetStub.imgRegister(request);
//			  System.out.println("res: "+ imgReply.getMessage());
			  return imgReply.getMessage();
			  
		  }
	    public String imgAuth(String email, byte[] photo) {
			  ByteString bString = ByteString.copyFrom(photo);
			  ImgAuthRequest request = ImgAuthRequest.newBuilder().setEmail(email).setPhoto(bString).build();  			  
			  ImgReply imgReply = null;
			  imgReply = greetStub.imgAuth(request);
			  return imgReply.getMessage();
			  
		  }



}
