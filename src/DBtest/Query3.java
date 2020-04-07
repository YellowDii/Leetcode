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
import org.bson.types.ObjectId;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

public class Query3 {
    public static void main(String[] args) {
        Query3 query3=new Query3();
        query3.query_book2();
    }
    public void query_book1(){
        /* 建立连接*/
        ServerAddress s=new ServerAddress("localhost",27017);
        /* 创建客户端*/
        MongoClient mc=new MongoClient(s);
        /* 连接到library数据库，如果没有这个数据库，会自动创建*/
        MongoDatabase md=mc.getDatabase("library");
        /*连接到book1集合 */
        MongoCollection<Document> studentCollection = md.getCollection("book1");
        long startMills=System.currentTimeMillis();
        FindIterable<Document> findIterable=studentCollection.find();
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
    public void query_book2(){
        /* 建立连接*/
        ServerAddress s=new ServerAddress("localhost",27017);
        /* 创建客户端*/
        MongoClient mc=new MongoClient(s);
        /* 连接到library数据库，如果没有这个数据库，会自动创建*/
        MongoDatabase md=mc.getDatabase("library");
        /*连接到book2集合 */
        MongoCollection<Document> studentCollection = md.getCollection("book2");
        long startMills=System.currentTimeMillis();
        FindIterable<Document> findIterable=studentCollection.find();
        MongoCursor<Document> mongoCursor=findIterable.iterator();
        long endMills=System.currentTimeMillis();
        System.out.println("查询耗时： "+(endMills-startMills)+" ms");
        int i=1;
        while (mongoCursor.hasNext()){
            Document d=mongoCursor.next();
            ArrayList<ObjectId> authors=(ArrayList<ObjectId>)d.get("authors");
            Bson authorFilter=Filters.in("_id",authors);
            MongoCollection<Document> authorCollection=md.getCollection("author");
            MongoCursor<Document> authorCursor=authorCollection.find(authorFilter).iterator();
            String name1 = "",name2="";
            String country1="",country2="";
            int k=0;
            while (authorCursor.hasNext()){
                Document author=authorCursor.next();
                if (k%2==0){
                    name1=author.get("name")+"";
                    country1=author.get("country")+"";
                    k++;
                }else {
                    name2=author.get("name")+"";
                    country2=author.get("country")+"";
                    k++;
                }
            }
            System.out.print("{ \"_id\":"+d.get("_id")+" ,\"name\":"+d.get("name")+" ,\"publication year\":"+d.get("publication year"));
            System.out.println("\"authors\": [{\"name\": "+name1+",\"country\":"+country1+"}, {\"name\": "+name2+",\"country\":"+country2+"}]}");
            if (++i>20){
                System.out.println("这里限制打印20条......");
                break;
            }
        }
    }

}
