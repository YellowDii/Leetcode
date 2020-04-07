package DBtest;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Query1 {

    public static void main(String[] args) {
        Query1 query1=new Query1();
        query1.query1();
    }
    public void query1(){
        /* 建立连接*/
        ServerAddress s=new ServerAddress("localhost",27017);
        /* 创建客户端*/
        MongoClient mc=new MongoClient(s);
        /* 连接到student数据库，如果没有这个数据库，会自动创建*/
        MongoDatabase md=mc.getDatabase("student");
        /*连接到student_info集合 */
        MongoCollection<Document> studentCollection = md.getCollection("student_info");
        /*查询——利用 hint 函数强制使用某个索引或强制不使用索引（给出的例子是查询学号在 1~800000 之间的学生，这里强制不使用索引）*/
        long startMills=System.currentTimeMillis();
        Bson b1= Filters.lt("student_id",800001);
        Bson b2= Filters.gt("student_id",0);
        Bson b=Filters.and(b1,b2);
//        Bson natural=new BasicDBObject("$natural",1);
        FindIterable<Document> findIterable=studentCollection.find(b);
        MongoCursor<Document> mongoCursor=findIterable.iterator();
        long endMills=System.currentTimeMillis();
        System.out.println("查询耗时： "+(endMills-startMills)+" ms");
        int i=1;
        while (mongoCursor.hasNext()){
            Document d=mongoCursor.next();
            System.out.println(d.toJson());
            if (++i>20){
                System.out.println("这里限制打印20条......");
                break;
            }
        }
    }
}
