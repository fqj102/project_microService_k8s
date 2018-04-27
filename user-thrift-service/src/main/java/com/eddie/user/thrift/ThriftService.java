package com.eddie.user.thrift;

import com.eddie.micro.user.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ThriftService {

    @Value("${service.port}")
    private int port;

    @Autowired
    private UserService.Iface userService;


    @PostConstruct
    public void startThriftService(){
        TProcessor processor = new UserService.Processor<>(userService);
        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(port);
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        args.processor(processor);
        //帧传输
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());

        TNonblockingServer server = new  TNonblockingServer(args);
        server.serve();
    }

}
