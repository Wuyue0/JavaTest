package com.zixue.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Component
public class AliOSSUtils {
    @Autowired
    AliOSSConfig aliOSSConfig;

    // oss对象
    @Autowired
    private OSS ossClient;

    // 上传文件
    public String upload(MultipartFile file) throws Exception {
        try (
             InputStream inputStream = file.getInputStream();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            // 获取文件的字节数组
            byte[] fileBytes = byteArrayOutputStream.toByteArray();
            // 生成文件名
            String fileName = generateFileName(file, fileBytes);
            // 4.获取文件的访问地址
            String url = generateFilePath(fileName);
            // 判断OSS当前图片是否存在
            boolean exists = isExist(fileName);
            // 判断OSS当前图片是否存在
            if (exists) {
                return url;
            }
            String bucketName = aliOSSConfig.getBucketName();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(fileBytes));
            ossClient.putObject(putObjectRequest);
            return url;
        } catch (IOException e) {
            throw new IOException("上传文件时发生错误", e);
        }
    }

    //删除文件
    public String delete(String fileName) {
        if(fileName == null || fileName.isEmpty()) return "文件名为空，请检查";
        if(!isExist(fileName)) return "删除的文件不存在";
        ossClient.deleteObject(aliOSSConfig.getBucketName(), fileName); // oss 删除文件
        return null;
    }

    // 判断oss文件是否存在
    public boolean isExist(String fileName) {
      String bucketName = aliOSSConfig.getBucketName();
      return ossClient.doesObjectExist(bucketName, fileName);
    }

    // 返回文件的后缀名
    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0) {
            return fileName.substring(lastIndexOfDot); // 包含点的后缀名
        }
        return ""; // 如果没有后缀名，返回空字符串
    }

    // 生成文件名 带后缀
    private String generateFileName(MultipartFile file, byte[] fileBytes) throws NoSuchAlgorithmException, IOException {
        String fileHash = calculateMD5(new ByteArrayInputStream(fileBytes));
        return fileHash + getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
    }

    // 生成完整的文件路径
    private String generateFilePath(String fileName) throws IOException {
        String endpoint = aliOSSConfig.getEndpoint();
        String bucketName = aliOSSConfig.getBucketName();
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
    }

    // 计算文件的MD5值
    public String calculateMD5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            md.update(buffer, 0, read);
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}