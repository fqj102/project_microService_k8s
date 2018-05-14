package com.eddie.user.thrift;

import com.eddie.micro.message.MessageService;
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

    @Value("${thrift.user.service.ip}")
    private String userServiceIP;
    @Value("${thrift.user.service.port}")
    private int userServicePort;

    @Value("${thrift.message.service.ip}")
    private String MessageServiceIp;
    @Value("${thrift.message.service.port}")
    private int MessageServicePort;

    private enum serviceType{
        USER,
        MESSAGE
    }

    public UserService.Client getUserService(){
        return getService(userServiceIP, userServicePort ,serviceType.USER);
    }

    public MessageService.Client getMessageService(){
        return getService(MessageServiceIp, MessageServicePort ,serviceType.MESSAGE);
    }

    private <T> T getService(String ip, int port, serviceType serviceType){
        TSocket socket = new TSocket(ip , port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;
        switch (serviceType){
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }
        return (T)result;
    }
}
