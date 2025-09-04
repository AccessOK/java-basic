import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CountLogWords{
    public static void main(String[] args) throws IOException{
        var contents=Files.readString(Path.of("./src/advanced/streams/words.txt"));
        List<String> words=List.of(contents.split("\\PL+"));
        long count=0;
        for(String word:words){
            if(word.length()>4){
                count++;
            }
            System.out.println(count);
            count=words.stream().filter(w-> w.length()>4).count();
            System.out.println(count);
            count=words.parallelStream().filter(w -> w.length()>4).count();
            System.out.println(count);
        }
        //数组转换成流
        var a=Stream.of("asd","weqw","asdweqwe").toList();
        System.out.println(a);
        //创建无限流，通过limit()限制流大小
        var b=Stream.generate(()->{return "asd";}).limit(2).toList();
        System.out.println(b);
        //创建无限流，若不限制大小，会超内存
        var c=Stream.generate(Math::random).limit(20).toList();
        System.out.println(c);
        // 无限流
        var d=Stream.iterate(BigInteger.ZERO, n->n.add(BigInteger.ONE)).limit(10).toList();
        System.out.println(d);
        //限制流
        var e=Stream.iterate(BigInteger.ZERO, n->n.compareTo(new BigInteger("102"))<0,n->n.add(BigInteger.ONE)).toList();
        System.out.println(e);
    }
}