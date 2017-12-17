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
            if(file.FILE_NAME.equals(name)){
                tempFile = file;
            }
        }

        return tempFile;
    }

}