import grpc
from concurrent import futures
import time
import HelloWorld_pb2_grpc as pb2_grpc
import HelloWorld_pb2 as pb2

class HelloWorldService(pb2_grpc.HelloWorldServiceServicer):

    def __init__(self, *args, **kwargs):
        pass

    def sayHello(self, request, context):
        first_name = request.first_name
        last_name = request.last_name
        result = f'Hello {first_name} {last_name}!'
        result = {'message': result}
        print('result:')
        return pb2.Greeting(**result)


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    pb2_grpc.add_HelloWorldServiceServicer_to_server(HelloWorldService(), server)
    server.add_insecure_port('[::]:9090')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    serve()