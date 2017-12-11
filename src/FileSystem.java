public class FileSystem {
    private DiscDrive HDD = new DiscDrive();                //Dysk
    private Catalog dir = new Catalog(); 					//Katalog domyslny, w ktorym beda zapisywane wszystkie wpisy - obiekty File

    //Operacje na dysku

    void createEmptyFile(String fileName) throws Exception{
        int index = firstFreeBlock();
        if (nameExists(fileName)){
            throw new Exception("File with this name already exists.");
        }
        else{
            if (HDD.FREE_BLOCKS>=1 && index!=-1){
                HDD.FREE_BLOCKS--;
                HDD.bitVec[index]=false;
                File temp = new File (fileName, 0, index);
                dir.add(temp);
            }
            else{
                throw new Exception("Not enough space to create this file.");
            }
        }
    }

    void appendFile(String fileName, String fileContent) throws Exception{
        if (!nameExists(fileName)){
            throw new Exception("There is no file with the provided name.");
        }
        int size = fileContent.length();
        int blocks = (int) (Math.ceil (((double) size)/31));
        if (HDD.FREE_BLOCKS < blocks){
            throw new Exception("There is not enough space left for this file.");
        }
        else{
            for (int i=0; i<size; i++){

            }
        }
    }

    void createFile(String fileName, String fileContent) throws Exception{

    }

    void deleteFile(String fileName) throws Exception{

    }

    void renameFile(String oldName, String newName) throws Exception{

    }

    String readFile(String fileName) throws Exception{
        return " ";
    }

    String[] list(){
        String[] res = new String[dir.size()];
        return res;
    }

    File getFilebyName(String fileName) throws Exception{
        return dir.getFileForName(fileName);
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
        int index=-1;
        for (int i=0; i<HDD.BLOCKS_AMOUNT; i++){
            if (HDD.bitVec[i]){
                index=i;
                break;
            }
        }
        return index;
    }

}