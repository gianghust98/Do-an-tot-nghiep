package com.webtoeic;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: com.webtoeic.proto")
public final class UnaryGrpc {

  private UnaryGrpc() {}

  public static final String SERVICE_NAME = "com.webtoeic.Unary";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.webtoeic.UnaryOuterClass.Message,
      com.webtoeic.UnaryOuterClass.MessageResponse> getGetServerResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetServerResponse",
      requestType = com.webtoeic.UnaryOuterClass.Message.class,
      responseType = com.webtoeic.UnaryOuterClass.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.webtoeic.UnaryOuterClass.Message,
      com.webtoeic.UnaryOuterClass.MessageResponse> getGetServerResponseMethod() {
    io.grpc.MethodDescriptor<com.webtoeic.UnaryOuterClass.Message, com.webtoeic.UnaryOuterClass.MessageResponse> getGetServerResponseMethod;
    if ((getGetServerResponseMethod = UnaryGrpc.getGetServerResponseMethod) == null) {
      synchronized (UnaryGrpc.class) {
        if ((getGetServerResponseMethod = UnaryGrpc.getGetServerResponseMethod) == null) {
          UnaryGrpc.getGetServerResponseMethod = getGetServerResponseMethod = 
              io.grpc.MethodDescriptor.<com.webtoeic.UnaryOuterClass.Message, com.webtoeic.UnaryOuterClass.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.webtoeic.Unary", "GetServerResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.webtoeic.UnaryOuterClass.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.webtoeic.UnaryOuterClass.MessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UnaryMethodDescriptorSupplier("GetServerResponse"))
                  .build();
          }
        }
     }
     return getGetServerResponseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UnaryStub newStub(io.grpc.Channel channel) {
    return new UnaryStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UnaryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UnaryBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UnaryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UnaryFutureStub(channel);
  }

  /**
   */
  public static abstract class UnaryImplBase implements io.grpc.BindableService {

    /**
     */
    public void getServerResponse(com.webtoeic.UnaryOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.webtoeic.UnaryOuterClass.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetServerResponseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetServerResponseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.webtoeic.UnaryOuterClass.Message,
                com.webtoeic.UnaryOuterClass.MessageResponse>(
                  this, METHODID_GET_SERVER_RESPONSE)))
          .build();
    }
  }

  /**
   */
  public static final class UnaryStub extends io.grpc.stub.AbstractStub<UnaryStub> {
    private UnaryStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UnaryStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnaryStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UnaryStub(channel, callOptions);
    }

    /**
     */
    public void getServerResponse(com.webtoeic.UnaryOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.webtoeic.UnaryOuterClass.MessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetServerResponseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UnaryBlockingStub extends io.grpc.stub.AbstractStub<UnaryBlockingStub> {
    private UnaryBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UnaryBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnaryBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UnaryBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.webtoeic.UnaryOuterClass.MessageResponse getServerResponse(com.webtoeic.UnaryOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), getGetServerResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UnaryFutureStub extends io.grpc.stub.AbstractStub<UnaryFutureStub> {
    private UnaryFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UnaryFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnaryFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UnaryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.webtoeic.UnaryOuterClass.MessageResponse> getServerResponse(
        com.webtoeic.UnaryOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getGetServerResponseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SERVER_RESPONSE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UnaryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UnaryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SERVER_RESPONSE:
          serviceImpl.getServerResponse((com.webtoeic.UnaryOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.webtoeic.UnaryOuterClass.MessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UnaryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UnaryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.webtoeic.UnaryOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Unary");
    }
  }

  private static final class UnaryFileDescriptorSupplier
      extends UnaryBaseDescriptorSupplier {
    UnaryFileDescriptorSupplier() {}
  }

  private static final class UnaryMethodDescriptorSupplier
      extends UnaryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UnaryMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UnaryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UnaryFileDescriptorSupplier())
              .addMethod(getGetServerResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
