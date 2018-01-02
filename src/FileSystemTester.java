public class FileSystemTester {

    public static void main(String[] args) {
        //DiscDrive hdd = new DiscDrive();
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

        try {
            drive.createFile("plik1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.openFile("plik1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.appendFile("plik1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String toPrint = new String();
        try {
            toPrint = drive.readFile("plik1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(drive.dir.getSize("plik1"));
        System.out.println("Poczatek");
        System.out.println(toPrint);
        System.out.println("Koniec");

        /*
        try {
            drive.createFile("plik1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.createFile("plik2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.appendFile("plik1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.appendFile("plik1", " Pellentesque lobortis est sed elit vestibulum ultricies.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.appendFile("plik2", "Sed egestas sem id aliquet vulputate. Integer consectetur maximus risus et placerat. Vivamus ac pulvinar mauris, varius pretium augue.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        drive.printDrive();
        System.out.println();
        drive.printBitVec();
        System.out.println();
        System.out.println(drive.list());

        try {
            drive.openFile("plik2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.appendFile("plik2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.deleteFile("plik1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            drive.appendFile("plik2", " Pellentesque lobortis est sed elit vestibulum ultricies.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        drive.printDrive();
        System.out.println();
        drive.printBitVec();
        System.out.println();
        System.out.println(drive.list());

        String toPrint = new String();

        try {
            toPrint=drive.readFile("plik2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Poczatek");
        System.out.println(toPrint);
        System.out.println("Koniec");

        System.out.println("Poczatek");
        System.out.println(drive.dir.getFileByName("plik2").getCon());
        System.out.println("Koniec");

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