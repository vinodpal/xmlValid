package xmlParsse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check {
    public static void main(String[] args) throws IOException {

        
            /*
            <MainTag>
                <element>
                    <tag1>Key1</tag1>
                    <tag2>Not intrested</tag2>
                    <tag3>Value1</tag3>
                </element>
                <element>
                    <tag1>Key2</tag1>
                    <tag2>Not intrested</tag2>
                </element>
                <element>
                    <ag1>Key3</tag1>
                    <tag2>Not intrested</tag2>
                    <tag3>Value3</tag3>
                </element>
            </MainTag>;
            */    
            File xmlFile = new File("/home/V/info.xml");
            BufferedReader bufReader = new BufferedReader(new FileReader(xmlFile));
            StringBuilder sb = new StringBuilder();
            {
                String line = bufReader.readLine();
                while(line!=null){
                    sb.append(line);
                    line = bufReader.readLine();
                }
            }
            String xmlString = sb.toString();
            String pater = "<(.*?)>";
            Pattern p = Pattern.compile(pater);
            Matcher m = p.matcher(xmlString);
            String[] str = p.split(xmlString);
                    
            Stack<String> stk = new Stack<String>();
            while(m.find()){
                        String str1 = m.group(1);
                        System.out.println(m.group(0)+" : "+m.group(1));
                        if(str1.substring(0, 1).equals("/")){
                            
                            if(str1.substring(1).equals(stk.peek())){
                                stk.pop();
                                
                            }
                            else break;
                        }
                        else if(stk.isEmpty()==true || !stk.peek().equals(str1)){
                            stk.push(str1);
                        }
                        else break;
            }
            
            if(stk.isEmpty()== true)
                System.out.println("It's valid");
            else
                System.out.println("It's Not valid");
    }
}
