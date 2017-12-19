public class FileSystem {
    /*private*/ DiscDrive Drive = new DiscDrive();                //Dysk
    private Catalog dir = new Catalog(); 					//Katalog domyslny, w ktorym beda zapisywane wszystkie wpisy - obiekty File

    //Operacje na dysku

    public boolean openFile(String fileName) {
        boolean open = false;

        return open;
    }
    /*
    public boolean closeFile(String fileName) {

    }*/
    public int createFile(String fileName){
        if (nameExists(fileName)) { return 2; }
        else if (Drive.FREE_BLOCKS==0) { return 1; }
        else {
            int index = firstFreeBlock();
            Drive.bitVec[index] = false;
            Drive.putByte((char) 32 , (index+1) *32 - 1);
            dir.add(new File(fileName, index));
            return 0;
        }
    }

    public int appendFile(String fileName, String content) {
        if (!nameExists(fileName)) { return 2; }
        else if (((double)content.length()/32.0)>Drive.FREE_BLOCKS) { return 1; }
        else {
            int current_block, i;
            if (dir.getFileByName(fileName).FILE_SIZE==0) { current_block = dir.getFirstBlock(fileName); i = 0; }
            else { current_block = dir.getLastBlock(fileName); i = dir.getFileByName(fileName).FILE_SIZE%31; }
            dir.getFileByName(fileName).FILE_SIZE += content.length();
            while (content.length()!=0) {
                if (i+1==32) {
                    Drive.putByte((char) firstFreeBlock(), (current_block+1) * 32 - 1);
                    current_block=firstFreeBlock();
                    Drive.bitVec[current_block]=false;
                    i=0;
                }
                Drive.putByte(getChar(content), (i + ((current_block * 32))));
                content=removeChar(content);
                if (content.length()==0) { dir.setLastBlock(fileName, current_block); Drive.putByte((char) 32 , (current_block+1) * 32 - 1); }
                i++;
            }
            return 0;
        }
    }

    public boolean deleteContent(String fileName) {
        if (!nameExists(fileName)) { return false; }
        else {
            int block = dir.getFirstBlock(fileName);
            dir.changeLast(fileName, block);
            dir.changeSize(fileName, 0);
            for (int i = block*32; i<block*32 + 31; i++) {
                 Drive.putByte((char) 0, i);
            }
            block = Drive.lastByte(block);
            while (block != 32) {
                Drive.bitVec[block] = true;
                block = Drive.lastByte(block);
            }
            return true;
        }
    }
    /*
    public String readFile(String fileName) {

    }
    */

    public boolean deleteFile(String fileName) {
        if (!nameExists(fileName)) { return false; }
        else {
            int block = dir.getFirstBlock(fileName);
            while (block != 32){
                Drive.bitVec[block] = true;
                block = Drive.lastByte(block);
            }
            dir.deleteFile(fileName);
            return true;
        }
    }

    public int renameFile(String oldName, String newName) {
        if (!nameExists(oldName)) { return 2; }
        if (nameExists(newName)) { return 1; }
        else { dir.changeName(oldName, newName); return 0; }
    }

    public String list() {
        String dir_list = new String();
        for (int i=0; i<dir.size(); i++) {
            dir_list=dir_list + dir.get(i).FILE_NAME + " " + dir.get(i).FILE_SIZE + " " + dir.get(i).FIRST_BLOCK;
            if (dir.get(i).LAST_BLOCK!=33 && dir.get(i).FILE_SIZE!=0) { dir_list = dir_list + " " + dir.get(i).LAST_BLOCK; }
            dir_list = dir_list + "\n";
        }
        return dir_list;
    }

    //Metody pomocnicze

    private boolean nameExists(String name){
        boolean result = false;
        for (int i=0; i<dir.size(); i++){
            if (dir.get(i).FILE_NAME.equals(name)){
                result = true;
            }
        }
        return result;
    }

    private int firstFreeBlock(){
        int index=33;
        for (int i=0; i<Drive.BLOCKS_AMOUNT; i++){
            if (Drive.bitVec[i]){
                index=i;
                break;
            }
        }
        return index;
    }

    private static char getChar(String str){
        return str.charAt(0);
    }

    private static String removeChar(String s) {
        StringBuilder build = new StringBuilder(s);
        build.deleteCharAt(0);
        return build.toString();
    }

    //Testowanie

    public void printBitVec(){
        Drive.printBitVec();
    }

    public void printDrive() {
        Drive.print();
    }

}