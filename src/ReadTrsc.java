import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Lingwei Meng on 2017/10/13.
 * read data from filesystem
 */
public class ReadTrsc {

    /**
     * Get all texts in the dataset
     * @return  ArrayList<String> strlist
     */
    public ArrayList<String> readData(){
        ArrayList<String> strlist=new ArrayList<>();

        File root=new File("transcripts");
        File[] files=root.listFiles();

        for (File file:files){
            strlist.addAll(getWords(file));
        }
        return strlist;
    }

    /**
     * Read words from a single file
     * @param file
     * @return ArrayList<String>
     */
    public ArrayList<String> getWords(File file){
        ArrayList<String> strlist=new ArrayList<>();
        BufferedReader reader=null;
        try{
            reader=new BufferedReader(new FileReader(file));
            String str="";
            String tempstr;
            while ((tempstr=reader.readLine())!=null){
                str=str+tempstr;
            }
            StringTokenizer st=new StringTokenizer(str," .,?-");
            while (st.hasMoreElements()){
                strlist.add(st.nextToken());
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return strlist;
    }
}
