package com.smartFarm.was.domain.dto.response;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;


@Slf4j
public class SubTest {

    public void noTWR() throws IOException {

        FileInputStream file = null;
        BufferedInputStream buffer = null;
        try {
            file = new FileInputStream("file.txt");
            buffer = new BufferedInputStream(file);
            int data = -1;
            while ((data = buffer.read()) != -1) {
                log.info("data = {}", (char) data);
            }
        } finally {
            // 객체 닫기
            if (file != null) {
                file.close();
            }
            if (buffer != null) {
                buffer.close();
            }
        }
    }

    public String useTWR(String url) throws IOException {

        URL targetUrl = new URL(url);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(targetUrl.openStream()))) {
            StringBuffer buffer = new StringBuffer();
            String tmp = null;

            while ((tmp = reader.readLine()) != null) {
                buffer.append(tmp);
            }
            return buffer.toString();
        }
    }
}
