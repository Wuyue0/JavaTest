package com.zixue.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
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
    AliOSSProperties aliOSSProperties;

    // 连接的oss对象
    private OSS ossClient;

    public String upload(MultipartFile file) throws Exception {
        // 获取属性注入
        String endpoint = aliOSSProperties.getEndpoint();
        String bucketName = aliOSSProperties.getBucketName();
        String region = aliOSSProperties.getRegion();

        // 使用 ByteArrayOutputStream 缓存文件流
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
            // 计算文件的MD5值
            String fileHash = calculateMD5(new ByteArrayInputStream(fileBytes));
            // 生成文件名
            String fileName = fileHash + getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
            // 创建OSSClient实例
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
            clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();

            // 4.获取文件的访问地址
            String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
            // 判断OSS当前图片是否存在
            boolean exists = ossClient.doesObjectExist(bucketName, fileName);
            // 判断OSS当前图片是否存在
            if (exists) {
                return url;
            } else {
                // 上传文件到OSS
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(fileBytes));
                PutObjectResult result = ossClient.putObject(putObjectRequest);
                return url;
            }
        } catch (IOException e) {
            throw new IOException("上传文件时发生错误", e);
        } finally {
            // 关闭流
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
    }

    // 返回文件的后缀名
    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0) {
            return fileName.substring(lastIndexOfDot); // 包含点的后缀名
        }
        return ""; // 如果没有后缀名，返回空字符串
    }

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