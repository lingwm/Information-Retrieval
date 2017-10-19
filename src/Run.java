import java.io.File;
import java.util.ArrayList;

/**
 * entrance
 * Created by Lingwei Meng on 2017/10/13.
 */
public class Run {
    public static void main(String args[]){
        ReadTrsc r=new ReadTrsc();
        TokenAnalyze ta=new TokenAnalyze();
        ta.analyze(r.readData());
    }
}
