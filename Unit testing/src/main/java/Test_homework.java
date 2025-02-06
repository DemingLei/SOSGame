public class Test_homework {
    //Print the length of the string
    public static void printNumber(String name){
        System.out.println("The name of the string is " + name.length());
    }

    //Print the maximum index of a string
    public static int getMaxIndex(String data){
        if (data == null){
            return -1;
        }

        if (data == ""){
            return 0;
        }

        //Write the code wrong deliberately here for testing
        return data.length();
    }
}
