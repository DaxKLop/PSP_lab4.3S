package org.example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    static int countclients = 0;

    Main() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            sock = new ServerSocket(1024);
            System.out.println("Сервер создан и ждет подключения клиентов...");

            while(true) {
                Socket client = sock.accept();
                ++countclients;
                System.out.println("Клиент подключился");
                is = client.getInputStream();
                os = client.getOutputStream();
                byte[] bytes = new byte[1024];
                is.read(bytes);
                String str = new String(bytes, "UTF-8");
                String[] parts = str.split(" ");
                double operand1 = Double.parseDouble(parts[0]);
                double operand2 = Double.parseDouble(parts[1]);
                int result =  0;
                if(operand1 > 0 && operand2 > 0) result = 1;
                if(operand1 < 0 && operand2 > 0) result = 2;
                if(operand1 < 0 && operand2 < 0) result = 3;
                if(operand1 > 0 && operand2 < 0) result = 4;

                String resString = String.valueOf(result + " Четверть");
                bytes = resString.getBytes();
                os.write(bytes);
            }
        } catch (Exception var20) {
            System.out.println("Error " + var20.toString());
        } finally {
            is.close();
            os.close();
            sock.close();
            System.out.println("Client " + countclients + " disconnected");
        }

    }
}
