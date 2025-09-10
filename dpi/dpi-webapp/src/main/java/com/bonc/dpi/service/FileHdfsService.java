package com.bonc.dpi.service;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.service.i.FileHdfsServiceI;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileHdfsService implements FileHdfsServiceI {
	
	static Configuration conf = new Configuration();
	
//	static {
//        conf.set("fs.defaultFS", "hdfs://nameservice1");
//        conf.set("dfs.nameservices", "nameservice1");
//        conf.set("dfs.ha.namenodes.nameservice1", "nn1,nn2");
//        conf.set("dfs.namenode.rpc-address.nameservice1.nn1", "xxx:8020");
//        conf.set("dfs.namenode.rpc-address.nameservice1.nn2", "xxx:8020");
//        conf.set("dfs.client.failover.proxy.provider.nameservice1"
//                ,"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
//    }
	
	FileSystem fs = null;
	
	@Before
    public void init() throws Exception {
        fs = FileSystem.get(URI.create("hdfs://192.168.0.14:9000"), conf, "hadoop");
    }
	
	//创建新文件
    public void createFile(String dst , byte[] contents) throws IOException{
        Path dstPath = new Path(dst);
        FSDataOutputStream outputStream = fs.create(dstPath);
        outputStream.write(contents);
        outputStream.close();
        fs.close();
        System.out.println("文件创建成功！");
    }
    
    //删除文件
    public void deleteFile(String filePath) throws IOException{
        Path path = new Path(filePath);
        boolean isok = fs.deleteOnExit(path);
        if(isok){
            System.out.println("delete ok!");
        }else{
            System.out.println("delete failure");
        }
        fs.close();
    }
    
    //创建新文件夹
    public void createFolder(String dst) throws IOException{
        Path dstPath = new Path(dst);
        fs.mkdirs(dstPath);
        fs.close();
        System.out.println("文件夹创建成功！");
    }
    
    //重命名文件夹
    public void renameFolder(String dst,String dstNew) throws IOException{
        Path dstPath = new Path(dst);
        Path dstNewPath = new Path(dstNew);
        fs.rename(dstPath,dstNewPath);
        fs.close();
        System.out.println("文件夹重命名成功！");
    }
    
    //删除文件夹
    @SuppressWarnings("deprecation")
	public void deleteFolder(String dst) throws IOException{
        Path dstPath = new Path(dst);
        fs.delete(dstPath);
        fs.close();
        System.out.println("文件夹删除成功！");
    }
    
    //上传本地文件
    public void uploadFile(String src,String dst) throws IOException{
        Path srcPath = new Path(src);
        Path dstPath = new Path(dst);
        //调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
        fs.copyFromLocalFile(false, srcPath, dstPath);

        //打印文件路径
        System.out.println("Upload to "+conf.get("fs.default.name"));
        System.out.println("------------list files------------"+"\n");
        FileStatus [] fileStatus = fs.listStatus(dstPath);
        for (FileStatus file : fileStatus)
        {
            System.out.println(file.getPath());
        }
        fs.close();
    }
    
    /**
     * 遍历指定目录(direPath)下的所有文件
     */
    public void  getDirectoryFromHdfs(String direPath){
        try {
            FileStatus[] filelist = fs.listStatus(new Path(direPath));
            for (int i = 0; i < filelist.length; i++) {
                System.out.println("_________" + direPath + "目录下所有文件______________");
                FileStatus fileStatus = filelist[i];
                System.out.println("Name:"+fileStatus.getPath().getName());
                System.out.println("Size:"+fileStatus.getLen());
                System.out.println("Path:"+fileStatus.getPath());
            }
            fs.close();
        } catch (Exception e){

        }
    }

}
