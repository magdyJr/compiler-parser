/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amr magdi
 * 
 */
public class Parserproj {
    public static String[] outtxt;
    public static int i=0;
       

    public static void program(String [] txt,int i)
    {
        stmtseq(txt,i);
        System.out.println("**** program found");
    }
    public static int stmtseq(String []txt,int i)
    {
        i=stmt(txt,i);
        while(txt[i].equalsIgnoreCase(";"))
        {
            match(";",txt[i]);
            i++;
            i=stmt(txt,i);
            
        }
        return i;
        
    }
    public static int stmt(String[]txt,int i)
    {
        if(txt[i].equalsIgnoreCase("if"))
        {
            i=ifstmt(txt,i);
                }
        else if(txt[i].equalsIgnoreCase("repeat"))
                {
                   i= restmt(txt,i);
                }
        else if(txt[i].equalsIgnoreCase("read"))
                {
                   i= readstmt(txt,i);
                }
        else if(txt[i].equalsIgnoreCase("write"))
                {
                    i=wstmt(txt,i);
                }
        else if(txt[i].equalsIgnoreCase("identifier"))
                {
                  i=  assstmt(txt,i);
                }
        return i;
    }
    public static int ifstmt(String []txt,int i)
    {
        System.out.println("**** if statement found");
        match("if",txt[i]);
        i++;
        i=exp(txt,i);
        match("then",txt[i]);
        i++;
        i=stmtseq(txt,i);
        if(txt[i].equalsIgnoreCase("end"))
        {
            match("end",txt[i]);
            i++;
        }
        else
        {
            match("else",txt[i]);
            i++;
            stmtseq(txt,i);
            match("end",txt[i]);
            i++;
        }
        
        return i;
    }
    public static int restmt(String[]txt,int i)
    {
         System.out.println("**** repeat statement found");
        match("repeat",txt[i]);
        i++;
        i=stmtseq(txt, i);
        match("until",txt[i]);
        i++;
        i=exp(txt, i);
       
        return i;
    }
    public static int assstmt(String[]txt,int i)
    {
        System.out.println("**** assignment statement found");
        match("identifier",txt[i]);
        i++;
        match(":",txt[i]);
        i++;
        match("=",txt[i]);
        i++;
        i=exp(txt, i);
        
        return i;

    }
    public static int readstmt(String[]txt,int i)
    {
        System.out.println("**** read statement found");
        match("read",txt[i]);
        i++;
        match("identifier",txt[i]);
        
        i++;
        
        return i;
    }
    public static int wstmt(String[]txt,int i)
    {
        System.out.println("**** write statement found");
        match("write",txt[i]);
        i++;
        i=exp(txt, i);
        
        return i;
    }
    public static int exp(String[]txt,int i)
    {
        i=simexp(txt,i);
        if(txt[i].equalsIgnoreCase("<")|txt[i].equalsIgnoreCase("="))
                {
                    if(txt[i].equalsIgnoreCase("<"))
                    {   match("<",txt[i]);
                    i++;
                    }
                    else
                    {   match("=",txt[i]);
                    i++;
                    }
                    i=simexp(txt,i);
                }
        return i;
        
    }
    public static int simexp(String[]txt,int i)
    {
       i= term(txt,i);
        while(txt[i].equalsIgnoreCase("-")|txt[i].equalsIgnoreCase("+"))
        {
            if(txt[i].equalsIgnoreCase("+"))
                    {   match("+",txt[i]);
                    i++;
                    }
                    else
                    {   match("-",txt[i]);
                    i++;
                    }
            i=term(txt,i);
        }
        return i;
    }
    public static int term(String[]txt,int i)
    {
        i=factor(txt,i);
        while(txt[i].equalsIgnoreCase("*")|txt[i].equalsIgnoreCase("/"))
        {
            if(txt[i].equalsIgnoreCase("*"))
                    {   match("*",txt[i]);
                    i++;
                    }
                    else
                    {   match("/",txt[i]);
                    i++;
                    }
          i=  factor(txt,i);
            
        }
        return i;
    }
    public static int factor(String[]txt,int i)
    {
        if(txt[i].equalsIgnoreCase("("))
        {
            match("(",txt[i]);
                    i++;
                    i=exp(txt, i);
                    match(")",txt[i]);
                    i++;
        }
        else if(txt[i].equalsIgnoreCase("number"))
        {
            match("number",txt[i]);
                    i++;
        }
        else if(txt[i].equalsIgnoreCase("identifier"))
        {
            match("identifier",txt[i]);
                    i++;
        }
        return i;
    }
    
    public static void match(String exptoken,String token)
    {
        if(token.equalsIgnoreCase(exptoken))
            System.out.println(token+" found");
        else
        {    System.out.println("error match");
            System.exit(0);
        }
    }
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        outtxt = new String[500];// TODO code application logic here
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\amr\\projects\\JavaApplication14\\src\\javaapplication14\\parser_input.txt"));
            
            
            String[] intxt = new String[100];
            
            
            int x = 0, z = 0;
            

            while ((intxt[x] = br.readLine()) != null) {

                String[] parts = intxt[x].split(" ");
                int ff=z;
                if(!parts[0].isEmpty())
                for (; z< parts.length+ff; z++) {
                    outtxt[z]=parts[z-ff];
                   
                }
                
                x++;
                }
            program(outtxt,i);
            br.close();
            
        }catch(Exception e){
            System.out.println("null");
        }
    }
    
}

