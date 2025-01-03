package j2ray.support.tools;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTool {


    public static void deleteDir(String pp) throws Exception {

        Path directory = Paths.get(pp);




        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file); // 有效，因为它始终是一个文件
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir); //这将起作用，因为目录中的文件已被删除
                return FileVisitResult.CONTINUE;
            }
        });


    }

    public static void copy(String src,String dest) throws Exception {
        copy(new File(src),new File(dest));
    }

    public static void copy(File src,File dest) throws Exception {

        if(dest.exists()){
            if(!dest.delete()){
                throw new Exception("dest file exist,and delete fail");
            }
        }

        Files.copy(src.toPath(),dest.toPath());
    }

    public static void writeFileContent(String path,String content,String characterSet) throws Exception {
        File file=new File(path);
        writeFileContent(file,content,characterSet);
    }
    public static void writeFileContent(String path,byte[] content) throws Exception {
        File file=new File(path);
        writeFileContent(file,content);
    }

    public static void writeFileContent(File file,byte[] content) throws Exception {
        if(file==null){
            return;
        }


        if(file.exists()){
            if(!file.delete()){
                throw new Exception("delfile err");
            }
        }else{
            File dir=new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));
            if(!dir.exists()){
                if(!dir.mkdirs()){
                    throw new Exception("mkdirs err");
                }
            }
        }

        if(!file.createNewFile()){
            throw new Exception("createFile err");
        }


        FileOutputStream br=null;
        try{
            br=new FileOutputStream(file);
            br.write(content);
            br.flush();
        }finally {
            if(br!=null){
                br.close();
            }
        }
    }

    public static void writeFileContent(File file,String content,String characterSet) throws Exception {
        if(file==null){
            return;
        }


        if(file.exists()){
            if(!file.delete()){
                throw new Exception("delfile err");
            }
        }else{
            File dir=new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));
            if(!dir.exists()){
                if(!dir.mkdirs()){
                    throw new Exception("mkdirs err");
                }
            }
        }

        if(!file.createNewFile()){
            throw new Exception("createFile err");
        }


        BufferedWriter br=null;
        try{
            br=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),characterSet));
            br.write(content);
            br.flush();
        }finally {
            if(br!=null){
                br.close();
            }
        }
    }


    public static String readFileContent(String path,String characterSet) throws Exception {
        File file=new File(path);
        return readFileContent(file,characterSet);
    }
    public static String readFileContent(File file,String characterSet) throws Exception {
        if(file==null||!file.exists()){
            return null;
        }

        StringBuilder stb=new StringBuilder();
        BufferedReader br=null;
        try{
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file),characterSet));
            String str;
            while((str=br.readLine())!=null){
                stb.append(str).append("\r\n");
            }
            return stb.toString().trim();
        }finally {
            if(br!=null){
                br.close();
            }
        }

    }

    public static String md5hex(File file) throws Exception {
        BinaryData b=readFileBinary(file);
        return MD5Tool.md5Hex(b.toBytes());
    }

    public static BinaryData readFileBinary(String path) throws Exception {
        return readFileBinary(new File(path));
    }

    public static BinaryData readFileBinary(File file) throws Exception {
        if(file==null||!file.exists()){
            return null;
        }

        BinaryData binaryData=new BinaryData(file.length());



        FileInputStream br=null;
        try{
            br=new FileInputStream(file);
            binaryData.read(br);
        }finally {
            if(br!=null){
                br.close();
            }
        }
        return binaryData;
    }


    public static void writeFileBinary(String filePath,BinaryData binaryData) throws Exception {
        writeFileBinary(new File(filePath),binaryData);
    }

    public static void writeFileBinary(File file,BinaryData binaryData) throws Exception {
        if(file==null){
            return;
        }

        if(file.exists()){
            if(!file.delete()){
                throw new Exception("delfile err");
            }
        }else{
            File dir=new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));
            if(!dir.exists()){
                if(!dir.mkdirs()){
                    throw new Exception("mkdirs err");
                }
            }
        }

        if(!file.createNewFile()){
            throw new Exception("createFile err");
        }


        OutputStream br=null;
        try{
            br=new FileOutputStream(file);
            binaryData.write(br);
            br.flush();
        }finally {
            if(br!=null){
                br.close();
            }
        }
    }

}
