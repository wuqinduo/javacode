package javacode.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1、替代匿名函数
 * 
 */
public class lamda {

	public static void main(String[] args) {
		//1、
		new Thread(new Runnable() {			
			@Override
			public void run() {
				System.out.println("原來匿名函数");
			}
		});
		Runnable stackadd = ()->{
			System.out.println("lamda匿名函数");
		};
		//2、迭代
		List<String> languages = Arrays.asList("java","scala","python");
		for(String each:languages) {
            System.out.println(each);
        }
        //after java8
        languages.forEach(x -> System.out.println(x));
        languages.forEach(System.out::println);
        //3、map
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        cost.stream().map(x -> x + x*0.05).forEach(x -> System.out.println(x));
        //4、map:將对象变成另一个; reduce将所有集合合成一个
        List<Double> cost2 = Arrays.asList(10.0, 20.0,30.0);
        double allCost = cost2.stream().map(x -> x+x*0.05).reduce((sum,x) -> sum + x).get();
        System.out.println(allCost);
        //5、过滤filter
        List<Double> filteredCost = cost.stream().filter(x -> x > 25.0).collect(Collectors.toList());
	}
}
