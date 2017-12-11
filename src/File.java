public class File extends FileBase {

    public String FILE_NAME;	//Nazwa pliku
    public int FILE_SIZE;		//Rozmiar pliku
    int FIRST_BLOCK;			//Pierwszy blok zajmowany przez plik

    //KONSTRUKTOR

    public File(String name, int size, int first){
        this.FILE_NAME=name;
        this.FILE_SIZE=size;
        this.FIRST_BLOCK=first;
    }
}
