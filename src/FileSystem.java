public class FileSystem {
    DiscDrive Drive = new DiscDrive();      //Dysk
    /*private*/ Catalog dir = new Catalog();    //Katalog domyslny, w ktorym zapisywane sa wszystkie wpisy - obiekty File

    //Operacje na dysku

    public void openFile(String fileName) throws Exception {
        if (!nameExists(fileName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        else {
            String tmp = new String();
            int block = dir.getFirstBlock(fileName), i=0;
            while (tmp.length()<=dir.getSize(fileName)) {
                if (i==31) {
                    block = Drive.lastByte(block);
                    i=0;
                }
                tmp += Drive.getAt(i+block*32);
                i++;
            }
            dir.updateFileContent(fileName, tmp);
        }
    }

    public void closeFile(String fileName) throws Exception {
        if (!nameExists(fileName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        else if (!dir.open_check(fileName)) { throw new Exception("Plik o takiej nazwie nie jest otwarty."); }
        else { dir.close_file(fileName); }
    }

    public void createFile(String fileName) throws Exception {
        if (nameExists(fileName)) { throw new Exception("Plik o takiej nazwie istnieje."); }
        else if (Drive.FREE_BLOCKS==0) { throw new Exception("Za mało miejsca na dysku."); }
        else {
            int index = firstFreeBlock();
            Drive.bitVec[index] = false;
            Drive.FREE_BLOCKS--;
            Drive.putByte((char) 32 , (index+1) *32 - 1);
            dir.add(new File(fileName, index));
        }
    }

    public void appendFile(String fileName, String content) throws Exception {
        if (!nameExists(fileName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        else if (!dir.open_check(fileName)) { throw new Exception("Plik o takiej nazwie nie jest otwarty."); }
        else if (((double)content.length()/31.0)>Drive.FREE_BLOCKS) { throw new Exception("Za mało miejsca na dysku."); }
        else {
            dir.updateFileContent(fileName, content);
            int current_block, i;
            if (dir.getFileByName(fileName).FILE_SIZE==0) { current_block = dir.getFirstBlock(fileName); i = 0; }
            else { current_block = dir.getLastBlock(fileName); i = dir.getFileByName(fileName).FILE_SIZE%31; }
            dir.getFileByName(fileName).FILE_SIZE += content.length();
            while (content.length()!=0) {
                if (i==31) {
                    Drive.putByte((char) firstFreeBlock(), (current_block+1) * 32 - 1);
                    current_block=firstFreeBlock();
                    Drive.bitVec[current_block]=false;
                    Drive.FREE_BLOCKS--;
                    i=0;
                }
                Drive.putByte(getChar(content), (i + ((current_block * 32))));
                content=removeChar(content);
                if (content.length()==0) { dir.setLastBlock(fileName, current_block); Drive.putByte((char) 32 , (current_block+1) * 32 - 1); }
                i++;
            }
        }
    }

    public void deleteContent(String fileName) throws Exception {
        if (!nameExists(fileName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        else if (!dir.open_check(fileName)) { throw new Exception("Plik o takiej nazwie nie jest otwarty."); }
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
        }
    }

    public String readFile(String fileName) throws Exception {
        if (!nameExists(fileName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        else if (!dir.open_check(fileName)) { throw new Exception("Plik o takiej nazwie nie jest otwarty."); }
        else { return dir.getContent(fileName); }
    }

    public void deleteFile(String fileName) throws Exception {
        if (!nameExists(fileName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        else {
            int block = dir.getFirstBlock(fileName);
            while (block != 32){
                Drive.bitVec[block] = true;
                block = Drive.lastByte(block);
            }
            dir.deleteFile(fileName);
        }
    }

    public void renameFile(String oldName, String newName) throws Exception {
        if (!nameExists(oldName)) { throw new Exception("Plik o takiej nazwie nie istnieje."); }
        if (nameExists(newName)) { throw new Exception("Plik o takiej nazwie już istnieje."); }
        else { dir.changeName(oldName, newName); }
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