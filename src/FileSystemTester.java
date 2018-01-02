public class FileSystemTester {

    public static void main(String[] args) {
        DiscDrive hdd = new DiscDrive();
        FileSystem drive = new FileSystem();
        //int /*i = 5,*/ current_block=0;
        /*int pos=((i - 1) + ((current_block * 32)));*/
        /*String content = "abcdefghijklmnoprstuwxyz1234567890!";
        int i=0;

        while (content.length()>=1) {

            //if (i>31) { current_block=firstFreeBlock(); }
            hdd.putByte(getChar(content), (i + ((current_block * 32))));
            content=removeChar(content);
            i++;
        }
        hdd.print();*/

        drive.createFile("plik1");
        drive.createFile("plik2");
        drive.appendFile("plik1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        drive.appendFile("plik1", " Pellentesque lobortis est sed elit vestibulum ultricies.");
        drive.appendFile("plik2", "Sed egestas sem id aliquet vulputate. Integer consectetur maximus risus et placerat. Vivamus ac pulvinar mauris, varius pretium augue.");

        drive.printDrive();
        System.out.println();
        drive.printBitVec();
        System.out.println();
        System.out.println(drive.list());

        drive.openFile("plik2");
        drive.appendFile("plik2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        drive.deleteFile("plik1");
        drive.appendFile("plik2", " Pellentesque lobortis est sed elit vestibulum ultricies.");
        drive.closeFile("plik2");

        drive.printDrive();
        System.out.println();
        drive.printBitVec();
        System.out.println();
        System.out.println(drive.list());

        /*drive.deleteFile("plik1");
        drive.printDrive();
        System.out.println();
        drive.printBitVec();
        System.out.println();
        System.out.println(drive.list());

        drive.deleteContent("plik2");
        drive.printDrive();
        System.out.println();
        drive.printBitVec();
        System.out.println();
        System.out.println(drive.list());*/

        /*String str = "this is Java";
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