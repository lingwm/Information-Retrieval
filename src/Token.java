/**
 * Created by Lingwei Meng on 2017/10/13.
 * nodes of tokenList
 */
public class Token {
    String str;
    int count;

    public Token(String s,int i) {
        str=s;
        count=i;
    }

    public void addCount(){
        count++;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
