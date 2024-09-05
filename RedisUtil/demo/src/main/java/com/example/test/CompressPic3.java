package com.goldgrid.compresspic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressPic3 {

    public static String CompressPic(String srcPath,String targetPath) throws Exception {
        double cutPercent=0.1;
        File file=new File(srcPath);
        BufferedImage bufferedImage=ImageIO.read(new FileInputStream(file));
        int width=bufferedImage.getWidth(null);
        int height=bufferedImage.getHeight(null);
        long fileLength=file.length();
        while((fileLength/1024)>=400) {
            width=(int)(width*(1-cutPercent));
            height=(int)(height*(1-cutPercent));
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height, null); // 绘制缩小后的图
            FileOutputStream deskImage = new FileOutputStream(targetPath); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
            encoder.encode(tag); // 近JPEG编码
            deskImage.close();

            File file1=new File(targetPath);
            BufferedImage bufferedImage1=ImageIO.read(new FileInputStream(file1));
            width=bufferedImage1.getWidth(null);
            height=bufferedImage1.getHeight(null);
            fileLength=file1.length();
        }
        return targetPath;
    }

    public static void main(String[] args) throws Exception {
        File file3=new File("D:\\app\\financefinance\\finance\\a.jpg");
        BufferedImage bufferedImage3=ImageIO.read(new FileInputStream(file3));
        System.out.println(file3.length());
        int width3=bufferedImage3.getWidth(null);
        int height3=bufferedImage3.getHeight(null);
        System.out.println("压缩前图片的宽为："+width3);
        System.out.println("压缩前图片的高为："+height3);
        CompressPic3.CompressPic("D:\\app\\financefinance\\finance\\a.jpg", "D:\\app\\financefinance\\finance\\a.jpg");
       /* String imgdist=reduceImg("d:/ZTestForWork/d.jpg", "d:/ZTestForWork/zipd.jpg", 1600, 1920, null);*/
        File file4=new File("D:\\app\\financefinance\\finance\\a.jpg");
        BufferedImage bufferedImage4=ImageIO.read(new FileInputStream(file4));
        System.out.println(file4.length());
        int width4=bufferedImage4.getWidth(null);
        int height4=bufferedImage4.getHeight(null);
        System.out.println("压缩后图片的宽为："+width4);
        System.out.println("压缩后图片的高为："+height4);
    }
}
