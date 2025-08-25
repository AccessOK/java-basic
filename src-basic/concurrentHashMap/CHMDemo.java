
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CHMDemo{
    public static ConcurrentHashMap<String,Long> map=new ConcurrentHashMap<>();
    public static void process(Path file){
        //扫描文件
        try(Scanner in=new Scanner(file)){
            //逐行读取文件
            while(in.hasNext()){
                String word=in.next();
                //统计文件单词个数
                map.merge(word, 1L, Long::sum);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static Set<Path> descendants(Path rootDir) throws IOException{
        //遍历目录树
        try(Stream<Path> entries=Files.walk(rootDir)){
            return entries.collect(Collectors.toSet());
        }
    }
    public static void main(String[] args) throws IOException ,InterruptedException{
        System.err.println("Start....");
        //java虚拟机可用的处理器个数
        int processors=Runtime.getRuntime().availableProcessors();
        //创建线程池
        ExecutorService executor=Executors.newFixedThreadPool(processors);
        //获取当前工作目录的路径
        Path pathToRoot=Path.of(".");
        //找寻目录树中的java文件
        for(Path p:descendants(pathToRoot)){
            if(p.getFileName().toString().endsWith(".java")){
                //对每一个java文件开启线程
                executor.execute(()->process(p));
            }
        }
        //要求线程池停止接受新任务，并在现有任务执行完毕后终止线程池中的所有线程
        executor.shutdown();
        //要求关闭线程池后等待线程池中的所有任务完成
        executor.awaitTermination(1, TimeUnit.MINUTES);
        //遍历输出并发映射集
        map.forEach((k,v)->{
            System.out.println(k+" occurs "+v+" times");
        });
    }
}