public class Hello {

public static void main(String[] args) {
        System.out.println("Hello, World!");

        // array
        int arr[] = {1, 2, 3, 4, 5};

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        // String

        String str1 = "Hello";
        String str2 = str1;
        str1 = str1 + " World";

        System.out.println(str2);



    }
}