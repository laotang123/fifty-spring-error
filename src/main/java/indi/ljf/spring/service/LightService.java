package indi.ljf.spring.service;

import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;

@Service
public class LightService  implements Closeable {

    public void shutdown1() {
        System.out.println("shutting down all lights");
    }

    @Override
    public void close() throws IOException {
        System.out.println("close all lights");
    }
}
