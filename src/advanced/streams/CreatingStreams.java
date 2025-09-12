import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CreatingStreams {
    public static <T> void show(String title,Stream<T> stream){
        final int SIZE=10;
        List<T> firstElements= stream.limit(SIZE+1).toList();
        System.out.println(title);
    }
    public static void main(String args[]) throws IOException{
        Path path=Path.of("./src/advanced/streams/words.txt");
        var contents = Files.readString(path);
        Stream<String> words=Stream.of(contents.split("\\PL+"));
        show("words", words);

    }
}
