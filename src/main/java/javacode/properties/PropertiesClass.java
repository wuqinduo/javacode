package javacode.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
/**
src/main/java和src/test/java

这两个目录中的所有*.java文件会分别在comile和test-comiple阶段被编译，
编译结果分别放到了target/classes和targe/test-classes目录中，但是这两个目录中的其他文件都会被忽略掉。

src/main/resouces和src/test/resources

这两个目录中的文件也会分别被复制到target/classes和target/test-classes目录中。
也就是：application.properties文件直接 复制到/tartget/classes目录下和 com同目录。
 */
public class PropertiesClass {
	public static void main(String[] args) throws IOException {
		//writeToProperties();
		getFromProperties();
	}
	public static void getFromProperties() throws  IOException{
		Properties prop = new Properties();
		// PropertiesClass.class 是Class类型的实例。是一体.class文件。才是我们真正想要的。
		/*String path = PropertiesClass.class.getClassLoader().getResource("")
				.getPath();
		String input = path + "my.properties";
*/		String input =  "my.properties";//在整个项目的目录下。和src等同目录。
		InputStream is = new BufferedInputStream(new FileInputStream(input));
		prop.load(new InputStreamReader(is, "UTF-8"));
		Set<Object> keySet = prop.keySet();
		for (Object key : keySet) {
			System.out.println(prop.getProperty((String) key));
		}
		
		/**
		 * 输出wuqinduo
		 * 最重要的是搞清 根路径
		 */
	}
	public static void writeToProperties(){
		Properties prop = new Properties();
		prop.setProperty("name", "zhangsan");
		prop.setProperty("age", "123");
		
		//keyset遍历
		System.out.println("keyset遍历");
		Set<Object> keys = prop.keySet();
		for (Object key : keys.toArray(new String[0])) {
			System.out.println(key + "\t" + prop.get(key));
		}
		//通过entrySet遍历
		System.out.println("entrySet遍历");
        Set<Map.Entry<Object,Object>> entrys = prop.entrySet();
        for(Map.Entry<Object,Object> entry: entrys) {
            System.out.println(entry.getKey().toString() + "\t" + entry.getValue().toString());
        }

        //通过propertyNames遍历
        System.out.println("propertyNames遍历");
        Enumeration<?> e = prop.propertyNames();
        while(e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            System.out.println(key + "\t" + value);
        }
        
        try {
        	OutputStream os = new FileOutputStream("my.properties");
			prop.store(os, "test properties " + new Date().toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //写入文件

		
	}
}
