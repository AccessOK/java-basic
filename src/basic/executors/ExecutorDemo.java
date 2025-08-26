
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorDemo{
    //记录文件中word单词的个数
    public static long occurrences(String word,Path path){
        try(var in = new Scanner(path)){
            int count=0;
            while(in.hasNext()){
                if(in.next().equals(word)){
                    count++;
                }
            }
            return count;
        }catch(IOException ex){
            return 0;
        }
    }
    //获取目录树
    public static Set<Path> descendants(Path rootDir) throws IOException{
        try(Stream<Path> entries=Files.walk(rootDir)){
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }
    //创建任务
    public static Callable<Path> searchForTask(String word,Path path){
        return ()->{
            try(var in=new Scanner(path)){
                while(in.hasNext()){
                    if(in.next().equals(word))return path;
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Search in "+path+" cancaled.");
                        return null;    
                    }
                }
            }
            throw new NoSuchElementException();
        };
    }
    public static void main(String[] args) throws IOException, InterruptedException,ExecutionException{
        try(var in=new Scanner(System.in)){
            System.out.println("Enter base directory (e.g. /opt/java-9-src): ");
            String start =in.nextLine();
            System.out.println("Enter keyword (e.g. volatile): ");
            String word=in.nextLine();
            Set<Path> files=descendants(Path.of(start));
            var tasks=new ArrayList<Callable<Long>>();
            //封装任务集
            for(Path file:files){
                Callable<Long> task=()-> occurrences(word, file);
                tasks.add(task);
            }
            //构造线程池
            ExecutorService executor=Executors.newCachedThreadPool();
            //开始计时
            Instant startTime = Instant.now();
            //并发执行任务
            List<Future<Long>> results=executor.invokeAll(tasks);
            long total=0;
            //统计并发之后的总和
            for(Future<Long> result:results){
                total+=result.get();
            }
            //结束计时
            Instant endTime=Instant.now();
            //输出word的总个数
            System.out.println("Occurrences of "+ word+" ： "+total);
            //输出并发执行时间
            System.out.println("Time elapsed: "+Duration.between(startTime, endTime).toMillis()+" ms");
            //封装任务集
            var searchTasks=new ArrayList<Callable<Path>>();
            for(Path file:files){
                searchTasks.add(searchForTask(word, file));
            }
            //并发执行任务，只需要一个结果
            Path found=executor.invokeAny(searchTasks);
            //输出最短时间
            System.out.println(word+" occurs in "+ found);
            if(executor instanceof ThreadPoolExecutor tpExecutor){
                System.out.println("Largest pool size: "+tpExecutor.getLargestPoolSize());
            }
            //申请关闭线程池
            executor.shutdown();
        }
    }
}