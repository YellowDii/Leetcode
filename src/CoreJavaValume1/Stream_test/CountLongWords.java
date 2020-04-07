package CoreJavaValume1.Stream_test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents=new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        // "\\PL+" 表示非字母分割
        // "\\PN+" 非数字;
        //"\\PZ+" 非分隔符;
        //"\\PS+" 非符号;
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        //迭代
        long count=0;
        for (String s:words){
            if (s.length()>12){
                count++;
            }
        }
        System.out.println(count);
        //流写法
        count=0;
        count=words.stream().filter(w->w.length()>12).count();
        System.out.println(count);
        //并行方式执行过滤和计数
        count=0;
        count=words.parallelStream().filter(w->w.length()>12).count();
        System.out.println(count);
    }
}
