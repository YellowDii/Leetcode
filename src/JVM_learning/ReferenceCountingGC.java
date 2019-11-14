package JVM_learning;
/*
JVM学习之路：
   《深入理解Java虚拟机》 周志明 第二卷
    参照代码清单3-1写的引用技术算法
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 占内存，以便在gc日志中查看是否被回收
     */
    private byte[] maxSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingGC objectA = new ReferenceCountingGC();
        ReferenceCountingGC objectB = new ReferenceCountingGC();
        objectA.instance = objectB;
        objectB.instance = objectA;

        objectA = null;
        objectB = null;

        System.out.println("GC前。。。");
        System.gc();
        System.out.println("GC完。。。");
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
/*
注意需要配置 VM options :-XX:+PrintGCDetails
才会打印GC日志
打印信息如下：

GC前。。。
[GC (System.gc()) [PSYoungGen: 7438K->712K(38400K)] 7438K->720K(125952K), 0.1169539 secs] [Times: user=0.00 sys=0.00, real=0.16 secs]
[Full GC (System.gc()) [PSYoungGen: 712K->0K(38400K)] [ParOldGen: 8K->629K(87552K)] 720K->629K(125952K), [Metaspace: 3217K->3217K(1056768K)], 0.0068048 secs] [Times: user=0.00 sys=0.02, real=0.02 secs]
GC完。。。
Heap
 PSYoungGen      total 38400K, used 998K [0x00000000d5d00000, 0x00000000d8780000, 0x0000000100000000)
  eden space 33280K, 3% used [0x00000000d5d00000,0x00000000d5df9b20,0x00000000d7d80000)
  from space 5120K, 0% used [0x00000000d7d80000,0x00000000d7d80000,0x00000000d8280000)
  to   space 5120K, 0% used [0x00000000d8280000,0x00000000d8280000,0x00000000d8780000)
 ParOldGen       total 87552K, used 629K [0x0000000081600000, 0x0000000086b80000, 0x00000000d5d00000)
  object space 87552K, 0% used [0x0000000081600000,0x000000008169d408,0x0000000086b80000)
 Metaspace       used 3223K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 350K, capacity 388K, committed 512K, reserved 1048576K

 */