package com.eddie.course.service;

import com.eddie.micro.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvide {

    @Value(value = "${thrift.service.ip}")
    private String userServiceIP;
    @Value(value = "${thrift.service.port}")
    private int userServicePort;

    public UserService.Client getUserService(){
        return getService(userServiceIP, userServicePort);
    }

    private <T> T getService(String ip, int port){
        TSocket socket = new TSocket(ip , port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = new UserService.Client(protocol);
        return (T)result;
    }
}
