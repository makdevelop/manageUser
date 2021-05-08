package com.test.crud.testJava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.*;

public class StreamsJava8 {

//	java.util.stream – supports functional-style operations on streams of elements, 
//	such as map-reduce transformations on collections.

	static Employee[] arrayOfEmps = { new Employee(1, "Jeff Bezos", 100000), new Employee(2, "Bill Gates", 200000),
			new Employee(3, "Mark Zuckerberg", 300000) };

	public static void main(String[] args) {

		// 1 -- Let’s first obtain a stream from an existing array
		Stream.of(arrayOfEmps);

		// 2-- We can also obtain a stream from an existing list:
		Arrays.asList(arrayOfEmps).stream();

		// Let’s now see some common usages and operations we can perform on
		// and with the help of the stream support in the language.
		List<Employee> listEmp = Arrays.asList(arrayOfEmps);
		System.out.println(listEmp);
		listEmp.stream().forEach(e -> e.salaryIncrement(10));
		for (Employee ee : listEmp) {
			System.out.println(ee.getAge());
		}

		List<String> str1 = new ArrayList<String>();
		List<String> str2 = new ArrayList<String>();
		str1.add("a");
		str1.add("b");
		str1.add("c");
		str1.add("d");
		str1.forEach(str2::add);
		System.out.println("str2 size ==== " + str2.size());

		// use of collect Collectors: its one of the common ways to get stuff out of the
		// stream once we are done with all the processing:
		List<String> str3 = new ArrayList<String>();
		str3 = str2.stream().collect(Collectors.toList());
		System.out.println("str3 size ==== " + str3.size());

		List<String> str4 = str3.stream().filter(s -> s.equals("a")).collect(Collectors.toList());
		System.out.println("str4 === " + str4);
		System.out.println("str4 === " + str4.size());

		// the first String with the value=n is returned. If no such String value
		// exists, then null is returned.
		String str5 = str3.stream().filter(s -> s.equals("n")).findFirst().orElse(null);
		System.out.println(str5);

		// If we need to get an array out of the stream, we can simply use toArray():
		String[] str6 = str3.stream().toArray(String[]::new); // The syntax String[]::new creates an empty array of
																// Employee
		System.out.println("str6 array length == " + str6.length);

		//// count
		long countValues = str3.stream().filter(s -> s.equals("a")).count();
		System.out.println(countValues);

		List<Integer> int1 = new ArrayList<Integer>();
		int1.add(10);
		int1.add(10);
		int1.add(30);
		int1.add(40);
		int1.add(50);

		// min() and max() return the minimum and maximum element in the stream
		Integer ee = int1.stream().max((e1, e2) -> e1 - e2).orElseThrow(NoSuchElementException::new);
		System.out.println("min value in the liste  == " + ee);

		// distinct
		List<Integer> listDistinct = int1.stream().distinct().collect(Collectors.toList());
		System.out.println("listDistinct values   == " + listDistinct);

		double a = int1.stream().mapToDouble(e -> e.doubleValue()).max().getAsDouble(); // orElseThrow(NoSuchElementException::new);
		System.out.println("max a value   == " + a);

		double b = int1.stream().mapToDouble(e -> e.doubleValue()).average().getAsDouble();
		System.out.println("average b value   == " + b);

		// renvoie duplicate elements from array :
		// Employee[] rr = (Employee[]) Arrays.stream(arrayOfEmps).distinct().toArray();

		List<Hosting> listHost = new ArrayList<>();
		listHost.add(new Hosting(5, "liquidweb.com", 80000));
		listHost.add(new Hosting(2, "linode.com", 90000));
		listHost.add(new Hosting(3, "digitalocean.com", 120000));
		listHost.add(new Hosting(4, "aws.amazon.com", 200000));
		listHost.add(new Hosting(1, "mkyong.com", 1));

		// convert list to map key:id, value :name
		Map<Integer, String> mapHost = listHost.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName));
		System.out.println("mapHost value === " + mapHost);

		// convert list to map key:name, value :website
		Map<String, Long> mapHost2 = listHost.stream()
				.collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites));
		System.out.println("mapHost2 value === " + mapHost2);

		// same with differnet method
		Map<Integer, String> mapHost3 = listHost.stream().collect(Collectors.toMap(x -> x.getId(), x -> x.getName()));
		System.out.println("mapHost3 value === " + mapHost3);

		System.out.println("sort by id");
		listHost.sort((Hosting e1, Hosting e2) -> e1.getId() - e2.getId());
		listHost.forEach(h -> System.out.println(h.getWebsites()));

		// compare by name
		System.out.println("sort by name");
		listHost.sort((Hosting e1, Hosting e2) -> e1.getName().compareTo(e2.getName()));
		listHost.forEach(h -> System.out.println(h.getName()));
		
		// reversed sort (desc -> asc)
		Comparator<Hosting> wbComparator=(o1,o2)->new Long(o1.getWebsites()).compareTo(new Long(o2.getWebsites()));
		listHost.sort(wbComparator);
		listHost.forEach(h -> System.out.println(h.getWebsites()));
		
		
		/**
		 * Java 8 method references, double colon (::) operator :
		 * There are four kinds of method references:
    			- Reference to a static method ClassName::staticMethodName
    			- Reference to an instance method of a particular object Object::instanceMethodName
    			- Reference to an instance method of an arbitrary object of a particular type ContainingType::methodName–
    			- Reference to a constructor ClassName::new
    		
    	List<String> list = Arrays.asList("node", "java", "python", "ruby");
		list.forEach(str -> System.out.println(str)); // lambda expression		
		list.forEach(System.out::println);          // method references
		
		Lambda expression : (args) -> ClassName.staticMethodName(args)
		Method Reference. : ClassName::staticMethodName
		
		 */
		
		List<String> list = Arrays.asList("1", "2", "3");
		list.stream().map(e->Integer.parseInt(e)).collect(Collectors.toList()); // lambda expression
		list.stream().map(Integer::parseInt).collect(Collectors.toList());      // method reference
		
		
		/*
		 ComparatorProvider provider = new ComparatorProvider();
		   // lambda
        // list.sort((o1, o2) -> provider.compareBySalary(o1, o2));
         * *******************
         // method reference
        list.sort(provider::compareBySalary);
        list.forEach(x -> System.out.println(x));

         class ComparatorProvider {

  		public int compareByAge(Employee o1, Employee o2) {
        return o1.getAge().compareTo(o2.getAge());
    	}

    	public int compareByName(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    	}

    	public int compareBySalary(Employee o1, Employee o2) {
        return o1.getAge().compareTo(o2.getAge());
    	}
         
		 */
		
		/**
		 * By using Optional you don’t have to use too many null checks to avoid nullPointerException.
		 * 
		 */
		
		String[] str = new String[10]; 
		Optional<String> checkNull=Optional.ofNullable(str[5]); // heck if the string is null
		if(checkNull.isPresent()) {
			String word = str[5].toLowerCase();   
            System.out.print(str);
		}else {
	           System.out.println("string is null");   
	    }   


		// sort values
		// List<String> str7 =(List<String>) str3.stream().sorted((e1, e2) ->
		// e1.compareTo(e2.toString()));

		// List<String> articles = new ArrayList<String>();
		// articleService.getAllArticles().forEach(articles::add); // each article on
		// l'ajoute au liste articles

		// map() produces a new stream after applying a function to each element of the
		// original stream. The new stream could be of different type.

//		List<Employee> employees = Stream.of(empIds)
//			      .map(employeeRepository::findById)
//			      .collect(Collectors.toList());

		// Here, we obtain an Integer stream of employee ids from an array. Each Integer
		// is passed to the function employeeRepository::findById() –
		// which returns the corresponding Employee object; this effectively forms an
		// Employee stream.
	}

}
