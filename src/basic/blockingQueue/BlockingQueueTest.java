package blockingQueue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public class BlockingQueueTest {
    //队列大小
    public static final int FILE_QUEUE_SIZE=10;
    //搜索线程数
    public static final int SEARCH_THREADS=100;
    //占位符
    private static final Path DUMMY=Path.of("");
    //阻塞队列
    private static BlockingQueue<Path> queue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)){
            System.out.print("Enter a base directory(e.g. /opt/jdk-11-src)：");
            String directory=in.nextLine();
            System.out.print("Enter a keyword (e.g. volatile)：");
            String keyword=in.nextLine();
            Runnable runnable=()->{
                //将目录下的文件及子目录中的文件都放入队列中
                try{
                    enumerate(Path.of(directory));
                    queue.put(DUMMY);
                }catch (IOException e){
                    e.printStackTrace();
                }catch (InterruptedException e){

                }
            };
            new Thread(runnable).start();
            for (int i = 1; i < SEARCH_THREADS; i++) {
                Runnable searcher=()->{
                    try {
                        boolean done=false;
                        while (!done) {
                            Path file=queue.take();
                            if(file==DUMMY){
                                queue.put(file);
                                done=true;
                            }else {
                                search(file,keyword);
                            }
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }catch (InterruptedException e){

                    }
                };
                new Thread(searcher).start();
            }
        }
    }
    public static void enumerate(Path directory) throws IOException,InterruptedException{
        //获取目录中的所有文件路径
        try(Stream<Path> children= Files.list(directory)){
            for(Path child:children.toList()){
                //判断是否是目录
                if(Files.isDirectory(child)){
                    //递归
                    enumerate(child);
                }else{
                    //将文件放入阻塞队列
                    queue.put(child);
                }
            }
        }
    }
    public static void search(Path file,String keyword) throws  IOException{
        try( Scanner in = new Scanner( file,StandardCharsets.UTF_8)){
            int lineNumber=0;
            while(in.hasNextLine()){
                lineNumber++;
                String line=in.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n",file,lineNumber,line);
                }
            }
        }
    }
}















