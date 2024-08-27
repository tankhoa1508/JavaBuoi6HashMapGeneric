package BaiTapCollections;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HashMap1 {
    public static void test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("USA", "Washington, D.C.");
        map.put("France", "Paris");
        map.put("Japan", "Tokyo");

        // Kiểm tra sự tồn tại của một khóa
        if (map.containsKey("France")) {
            System.out.println("Cappital of France: "+ map.get("France"));
        }

        // Kiểm tra sự tồn tại của một giá trị
        if (map.containsValue("Tokyo")) {
            System.out.println("Tokyo is in the map");
        }

        // Xóa một phần tử
        map.remove("Japan");
        System.out.println("########################");

        // In ra các phần tử còn lại trong hashmap
        for (String key : map.keySet()){
            System.out.println(key + ":" +map.get(key));
        }
        System.out.println("#####################");

        // Lặp qua các cặp khóa bằng cách sử dụng entrySet()
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Country: "+key+", Capital: "+value);
        }
        System.out.println("########################");

        // Lấy giá trị với khóa có sẵn
        System.out.println("Value for key 'USA': "+ map.getOrDefault("USA","Not Found"));

        // Lấy giá trị với khóa không có sẵn
        System.out.println("Value for key 'C': "+ map.getOrDefault("C","Not Found"));

        // Thêm hoặc cập nhật giá trị nếu khóa không có mặt (thay đổi kiểu giá trị thành String)
        map.putIfAbsent("VietNam", "Ha Noi");
        System.out.println("Value of VietNam: "+map.get("VietNam"));

        // Cập nhật giá trị nếu khóa có mặt (thay đổi  giá trị từ Paris -> Paris (Updated))
        map.computeIfPresent("France", (k,v) -> v +" Updated");
        System.out.println("Updated France Capital: "+map.get("France"));
    }

    public static void test2_countWords(){
        String s="xin chào xin xin chào phố phường bao phường phố";
        HashMap<String, Integer> map = new HashMap<>();
        String []words = s.split(" ");
        for(String x:words){
            int count = map.getOrDefault(x, 0);
            map.put(x, count+1);
        }

        for (String key : map.keySet()){
            System.out.println(key + ": " + map.get(key));
        }
    }

    public static void test3_countWords(){
        String s="xin chào xin xin chào phố phường bao phường phố";
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()){
            String word = tokenizer.nextToken();
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        for(String key : map.keySet()){
            System.out.println(key +": "+map.get(key));
        }
    }

    public static void test4_dictionary(){
        //Tạo một HashMap để lưu trữ từ điển
        HashMap<String, String> dictionary = new HashMap<>();

        //Thêm một số từ và định nghĩa vào từ điển
        dictionary.put("apple", "A fruit that is typically round and red, green, or yellow.");
        dictionary.put("banana", "A long, curved fruit with a yellow skin and soft, sweet, white flesh. ");
        dictionary.put("cherry", "A small, round fruit that is typically red or black.");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời ban nhập từ cần tra cứu: ");
        String name = scanner.nextLine();
        String res = dictionary.get(name);
        if (res!=null) {
            System.out.println(name+"  : "+res);
        } else {
            System.out.println("Not found");
        }
    }

    public static void test5_stream(){
        HashMap<String, Integer> map = new HashMap<>();

        // Quốc gia -- Số lượng cty có vốn trên 10tr đô
        map.put("USA", 3);
        map.put("France", 7);
        map.put("Japan", 2);
        map.put("VietNam", 25);

        System.out.println("###################");
        //In ra các phần tử còn lại trong HashMap
        for (String key : map.keySet()) {
            System.out.println(key+":  "+map.get(key));
        }
        System.out.println("###################");
        //In ra các phần tử còn lại trong HashMap
        map.entrySet().stream()
                .forEach(item -> System.out.println(item.getKey()+ ":"+item.getValue()));

        System.out.println("########################");
        //Tính tổng số lượng các công ty trên
        int kq=map.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum = "+kq);
        System.out.println("#####################");
        //Lọc các quốc gia có số lượng cty >5
        Map<String, Integer> newMap = map.entrySet().stream()
                .filter(item->item.getValue()>5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(newMap);
        System.out.println("####################");

        //Lọc các quốc gia có số lượng cty >5
        //Lọc các phần tử có giá trị lớn hơn 5 và thu thập vào một HashMap mới
        Map<String, Integer> newMap1 = map.entrySet().stream()
                .filter(entry -> entry.getValue() > 5)
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                ));
    }

    public static void test_linkedhashmap() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        //Thêm phần tử
        linkedHashMap.put("one", 1);
        linkedHashMap.put("two", 2);
        linkedHashMap.put("three", 3);

        // Cập nhật giá trị của khóa "two"
        linkedHashMap.put("two", 22);

//        //Truy cập các phần tử
//        linkedHashMap.get("two");
//        linkedHashMap.get("one");
//
//        // Xóa phần tử với khóa "two"
//        linkedHashMap.remove("two");

        // In ra LinkedHashMap
        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void test_treemap(){
        Map<String, Integer> treeMap = new TreeMap<>();

        //Thêm các phần tử vào TreeMap
        treeMap.put("banana", 2);
        treeMap.put("apple", 3);
        treeMap.put("cherry", 1);

        //In ra TreeMap
        for (Map.Entry<String, Integer> entry: treeMap.entrySet()) {
            System.out.println(entry.getKey()+ ": " + entry.getValue());
        }

        //Tạo TreeMap với khóa là tên và giá trị là tuổi
        TreeMap<String, Integer> ageMap = new TreeMap<>();

        //Thêm các phần tử vào TreeMap
        ageMap.put("John", 30);
        ageMap.put("Jane", 25);
        ageMap.put("Paul", 35);
        ageMap.put("Anna", 28);

        System.out.println("##################");
        for (Map.Entry<String, Integer> entry : ageMap.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }

        System.out.println("##################");
        //Tìm kiếm các phần tử
        System.out.println("First key: "+ ageMap.firstKey()); //Tên đầu tiên theo thứ tự
        System.out.println("Last key: "+ ageMap.lastKey()); //Tên cuối cùng theo thứ tự
        System.out.println("SubMap from 'Anna' to 'Paul': "+ ageMap.subMap("Anna", "Paul"));
    }

    public static void test_hashCodepoint(){
        Map<Point, String> pointMap = new HashMap<>();
        Point p1 = new Point(1,2);
        Point p2 = new Point(1,2); // Đối tượng giống p1
        Point p3 = new Point(3,4);

        pointMap.put(p1, "Point 1");
        pointMap.put(p3, "Point 2");

        //In ra kết quả kiểm tra
        System.out.println("Map contain p2: " +pointMap.containsKey(p2)); // True, vì p1 và p2 bằng nhau
        System.out.println("Map content: "+ pointMap);
    }

    public static void test_hashCode_person(){
        Person p1 =new Person("Alice", 30);
        Person p2 =new Person("Alice", 30);
        Person p3 =new Person("BoB", 25);

        // sử dụng HashSet để kiểm tra equals và hashCode
        Map<Person, Integer> map = new HashMap<>();
        map.put(p1,1);
        // map.put(2,p2);
        map.put(p3,3);

        // In ra kết quả kiểm tra
        System.out.println("Map contain p2: "+ map.containsKey(p2));
        System.out.println("Map content: "+map);
    }

    public static void test_optional(){
        //Tạo các đối tượng Optional
        Optional<String> optionalValue = Optional.of("Hello, World!");
        Optional<String> emptyOptional = Optional.empty();

        //Sử dụng các phương thức của Optional
        System.out.println("optionalValue is present: " + emptyOptional.isPresent());
        optionalValue.ifPresent(value -> System.out.println("Value: "+value));

        //Lấy giá trị hoặc giá trị thay thế
        String value = emptyOptional.orElse("Default Value");
        System.out.println("Value or Default: " +value);

        //Giá trị thay thế từ Supplier
        String generatedValue = emptyOptional.orElseGet(() -> "Generated Default Value");
        System.out.println("Generated Value: "+ generatedValue);

        //Giá trị từ Optional nếu có, hoặc ném ngoại lệ nếu không có
        try {
            String result = emptyOptional.orElseThrow(() -> new IllegalArgumentException("Value not present"));
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element + "  ");
        }
        System.out.println("");
    }

    public static void generic1(){
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello Generics");
        System.out.println("String content: " + stringBox.getContent());

        //Tạo Box cho Integer
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println("Integer content: " + integerBox.getContent());
        Integer[] intArray = {1,2,3,4};
        String[] strArray = {"A", "B", "C"};

        //Gọi phương thức Generic
        printArray(intArray);
        printArray(strArray);

        //Tạo danh sách cho số nguyên
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        //In danh sach
        for (Integer item : integerList) {
            System.out.println(item);
        }

        Pair<String, Integer> pair = new OrderedPair<>("Age", 25);
        System.out.println("Key: "+ pair.getKey() + ", Value: " + pair.getValue());

    }
    public static void main(String[] args) {
//        test1();
//        test2_countWords();
//        test3_countWords();
//        test4_dictionary();
//        test5_stream();
//        test_linkedhashmap();
//        test_treemap();
//        test_hashCodepoint();
//        test_hashCode_person();
//        test_optional();
        generic1();
    }
}
