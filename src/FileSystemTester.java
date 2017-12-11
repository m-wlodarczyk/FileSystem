public class FileSystemTester {

    public static void main(String[] args) {
        DiscDrive hdd = new DiscDrive();
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

        /*System.out.println(pom);
        System.out.println(pom.length());
        System.out.println(pom1);
        System.out.println(pom1.length());
        System.out.println(pom2);
        System.out.println(pom2.length());*/

        //System.out.println(blocks);
        //System.out.print(pom.charAt(0));
    }

}