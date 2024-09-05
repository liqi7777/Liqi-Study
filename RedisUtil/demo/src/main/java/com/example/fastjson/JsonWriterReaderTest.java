package com.example.fastjson;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sky
 * create  2021-03-25 10:20
 * email sky.li@ixiaoshuidi.com
 */
public class JsonWriterReaderTest {

    public static void main(String[] args) throws IOException {
//        writeTest();

        readTest();
    }

    private static void readTest() throws IOException {
        JSONReader reader = new JSONReader(new FileReader("/tmp/huge.json"));
        reader.startArray();
        int i =0;
        while(reader.hasNext()) {
            StateInRentFeeBO_v2 vo = reader.readObject(StateInRentFeeBO_v2.class);
            // handle vo ...
            System.out.println(i++);
        }
        reader.endArray();
        reader.close();
        System.out.println(i);
    }


    private static void writeTest() throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
        writer.startArray();
        for (int i = 0; i < 1000 * 10000; ++i) {
            writer.writeValue(new StateInRentFeeBO_v2());
        }
        writer.endArray();
        writer.close();
    }
}
