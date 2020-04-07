package CoreJavaValume1.Stream_test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreams {
    public static <T> void show(String title, Stream<T> stream){
        final int SIZE=10;
        List<T> firstElements=stream.limit(SIZE+1).collect(Collectors.toList());
        System.out.println(title+":");
        for (int i=0;i<firstElements.size();i++){
            if (i>0)
                System.out.print(",");
            if (i<SIZE)
                System.out.print(firstElements.get(i));
            else
                System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path= Paths.get("../xxx/xxxx/xxxxrr.txt");
        String contens=new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        //创建流的方法：
        Stream<String> words=Stream.of(contens.split("\\PL+"));
        show("words",words);
        Stream<String> songs=Stream.of("gently","down","the","stream");
        show("songs",songs);
        Stream<String> silence=Stream.empty();
        show("silence",silence);

        Stream<String> echos=Stream.generate(()->"Echos");
        show("echos",echos);

        Stream<Double> randoms=Stream.generate(Math::random);
        show("randoms",randoms);
        //无限序列
        Stream<BigInteger> integers=Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));
        show("integers",integers);

        Stream<String> wordsAnotherWay= Pattern.compile("\\PL+").splitAsStream(contens);
        show("wordsAnotherWay",wordsAnotherWay);

        try(Stream<String> lines=Files.lines(path,StandardCharsets.UTF_8)){
            show("lines",lines);
        }
    }
}
