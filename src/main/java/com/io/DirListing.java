package com.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//从命令行接受一个参数，代表一个路径，然后列举这个目录下的所有文件
//使用java.nio.file包
public class DirListing {
    public static void main(String[] args) {
        //检查参数个数
        if (args.length < 1) {
            System.out.println("need a dir");
            System.exit(0);
        }
        //用args[0]的目录，比如c:/，然后用这个目录构造一个path对象
        Path dirPath = Paths.get(args[0]);//get是Paths类的静态方法，调用它构造Path对象
        //声明一个directory流
        DirectoryStream<Path> directory = null;
        try {
            //用上面那个path对象来初始化这个流,后面可以加个过滤器（限制只有txt）
            directory = Files.newDirectoryStream(dirPath, "*.{txt}");
            //或者定义自己的过滤器
            DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) throws IOException {
                    return (Files.size(entry)>20000L);
                }
            };
            //然后使用这个过滤器
            //directory = Files.newDirectoryStream(dirPath,filter);
            //迭代访问这个流，打印出文件名称
            for (Path p : directory) {
                System.out.println(p.getFileName());
            }
        } catch (Exception ie) {
            System.out.println("invalid path specified：" + args[0]);
        } finally {
            try {
                if (directory != null) {
                    directory.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }
}
