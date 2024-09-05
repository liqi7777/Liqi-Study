package com.example.zipDemo;

import ch.qos.logback.core.util.FileSize;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Sky
 * create  2020-11-11 16:03
 * email sky.li@ixiaoshuidi.com
 */
public class ZipCompressDemo {
    private static String zipTargetFile = "/Users/liqi/Documents/zipbuffertest.zip";
    private static String zipSourceFile = "/Users/liqi/Documents/zipbuffertest.png";
    private static int FILE_SIZE = (int) new File(zipSourceFile).length();

    public static void main(String[] args) {
        //无buffer压缩
//        zipFileNoBuffer(zipSourceFile, zipTargetFile);

        //有buffer压缩
//        zipFileBuffer(zipSourceFile, zipTargetFile);

        // java nio channel
//        zipFileChannel(zipSourceFile, zipTargetFile);

        //zipFileMap
//        zipFileMap(zipSourceFile, zipTargetFile);


        //pipe
        zipFilePip(zipSourceFile,zipTargetFile);

    }

    public static void zipFilePip(String zipSourceFile, String zipTargetFile) {
        long beginTime = System.currentTimeMillis();
        try (WritableByteChannel out = Channels.newChannel(new FileOutputStream(zipTargetFile))) {
            Pipe pipe = Pipe.open();
            CompletableFuture.runAsync(() -> {
                runTask(pipe);
            });
            //获取读通道
            ReadableByteChannel readableByteChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(FILE_SIZE * 10);
            while (readableByteChannel.read(buffer) >= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("consume_time:" + (System.currentTimeMillis() - beginTime));

    }

    public static void runTask(Pipe pipe) {
        try (ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));
             WritableByteChannel out = Channels.newChannel(zos);
        ) {
            System.out.println("begin");
            for (int i = 0; i < 10; i++) {
                File file = new File(zipSourceFile);
                zos.putNextEntry(new ZipEntry("zipbuffertest" + i + ".png"));
                FileChannel jpgChannel = new FileInputStream(file).getChannel();
                jpgChannel.transferTo(0, file.length(), out);
                jpgChannel.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void zipFileMap(String zipSourceFile, String zipTargetFile) {
        File zipFile = new File(zipTargetFile);
        try (ZipOutputStream zip0ut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zip0ut);) {
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                File file = new File(zipSourceFile);
                zip0ut.putNextEntry(new ZipEntry("zipbuffertest" + i + ".png"));
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(zipSourceFile, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                writableByteChannel.write(mappedByteBuffer);
            }
            System.out.println("consume_time:" + (System.currentTimeMillis() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void zipFileChannel(String zipSourceFile, String zipTargetFile) {
        File zipFile = new File(zipTargetFile);
        try (ZipOutputStream zip0ut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zip0ut);) {
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                File file = new File(zipSourceFile);
                try (FileChannel fileChannel = new FileInputStream(file).getChannel()) {
                    zip0ut.putNextEntry(new ZipEntry("zipbuffertest" + i + ".png"));
                    fileChannel.transferTo(0, file.length(), writableByteChannel);
                }
            }
            System.out.println("consume_time:" + (System.currentTimeMillis() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void zipFileBuffer(String zipSourceFile, String zipTargetFile) {
        File zipFile = new File(zipTargetFile);
        try (ZipOutputStream zip0ut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zip0ut);) {
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(zipSourceFile))) {
                    zip0ut.putNextEntry(new ZipEntry("zipbuffertest" + i + ".png"));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }
            System.out.println("consume_time:" + (System.currentTimeMillis() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFileNoBuffer(String zipSourceFile, String zipTargetFile) {
        File zipFile = new File(zipTargetFile);
        try (ZipOutputStream zip0ut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                try (InputStream input = new FileInputStream(zipSourceFile)) {
                    zip0ut.putNextEntry(new ZipEntry("zipbuffertest" + i + ".png"));
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        zip0ut.write(temp);
                    }
                }
            }
            System.out.println("consume_time:" + (System.currentTimeMillis() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
