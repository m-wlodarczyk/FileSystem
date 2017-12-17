public class FileSystemTester {

    public static void main(String[] args) {
        DiscDrive hdd = new DiscDrive();
        int i = 5, current_block=3;
        int pos=((i - 1) + ((current_block * 32)));

        hdd.putByte('a', pos);
        hdd.print();

        /*
        //hdd.fill2();
        //hdd.print();
        String pom = "No siema!";

        String pom1 = "";
        String pom2 = " ";

        int size = 64;

        int blocks = (int) (Math.ceil (((double) size)/31));

        char a = 0;

        int b = a;

        System.out.print(a);
        System.out.print(b);
        */
        /*System.out.println(pom);
        System.out.println(pom.length());
        System.out.println(pom1);
        System.out.println(pom1.length());
        System.out.println(pom2);
        System.out.println(pom2.length());*/

        //System.out.println(blocks);
        //System.out.print(pom.charAt(0));
        /*
        String str = "this is Java";
        System.out.println(str);
        System.out.println(str.length());

        str = removeCharAt(str, 3);
        System.out.println(str);
        System.out.println(str.length());


        while (str.length()>=1) {
            char a = getChar(str);
            str=removeChar(str);
            System.out.println(a + " " + str);
            System.out.println(str.length());
        }
        */
    }

    private static char getChar(String str){
        return str.charAt(0);
    }

    private static String removeChar(String s) {
        StringBuilder build = new StringBuilder(s);
        build.deleteCharAt(0);
        return build.toString();
    }

}