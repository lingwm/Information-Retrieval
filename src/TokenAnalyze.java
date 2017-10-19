import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by Lingwei Meng on 2017/10/13.
 */
public class TokenAnalyze {
    public void analyze(ArrayList<String> strlist){
        ArrayList<Token> tokenList=new ArrayList<>();
        tokenList.add(new Token(strlist.get(0),1));

        boolean add=true;
        for (int i=1;i<strlist.size();i++){
            int length=tokenList.size();
            add=true;
            for (int j=0;j<length;j++){
                if (strlist.get(i).equals(tokenList.get(j).getStr())){
                    tokenList.get(j).addCount();
                    add=false;
                }
            }
            if (add){
                tokenList.add(new Token(strlist.get(i),1));
            }
        }

//        Calculate the number of words that occur only once
        int once_words=0;
        for (Token t:tokenList){
            if (t.getCount()==1){
                once_words++;
            }
        }


//        Calculate the 30 most frequent words
        Comparator<Token> comparator=new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o1.getCount()-o2.getCount();
            }
        };
        Collections.sort(tokenList,comparator);
        Collections.reverse(tokenList);



        System.out.println("The number of word tokens in the database:"+strlist.size());
        System.out.println("The number of unique words in the database:"+tokenList.size());
        System.out.println("The number of words that occur only once in the database:"+once_words);
        System.out.println("The average number of word tokens per document:"+strlist.size()/404);

        for (int i=0;i<30;i++){
            int tf=tokenList.get(i).getCount();
            double idf=getIDF(tokenList.get(i));
            System.out.println(tokenList.get(i).getStr()+","+tf+","+idf+","+tf*idf+","+((double)tf)/((double)strlist.size()));
        }

    }

    private double getIDF(Token t){
        int df=0;
        ReadTrsc r=new ReadTrsc();

        File root=new File("transcripts");
        File[] files=root.listFiles();
        for (File file:files){
            boolean exist=false;
            ArrayList<String> strlist=r.getWords(file);
            for (String str:strlist){
                if (str.equals(t.getStr())&&!exist){
                    exist=true;
                }
            }
            if (exist){
                df++;
            }
        }
        double N=files.length;
        double DF=df;
        double idf=Math.log(N/DF);
        return idf;
    }
}
