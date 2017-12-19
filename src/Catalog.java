import java.util.ArrayList;
import java.util.List;

public class Catalog extends FileBase {
    private List<File> root;

    public void add(File file){
        this.root.add(file);
    }

    public int size(){ return root.size(); }

    public Catalog(){
        root = new ArrayList<File>();
    }

    public File get(int i){
        return root.get(i);
    }

    public File getFileByName(String name) {
        File tempFile = null;
        for(File file : root){
            if(file.FILE_NAME.equals(name)){ tempFile = file; }
        }
        return tempFile;
    }

    public int getFirstBlock(String fileName) {
        return getFileByName(fileName).FIRST_BLOCK;
    }

    public int getLastBlock(String fileName) {
        return getFileByName(fileName).LAST_BLOCK;
    }

    public void setLastBlock(String fileName, int n) {
        for(File file : root){
            if(file.FILE_NAME.equals(fileName)){ file.LAST_BLOCK=n; }
        }
    }

}