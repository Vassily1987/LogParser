/*
    The MIT License (MIT)
 
    Copyright (c) 2014 sinfonier-project
 
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
 
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
 
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
 
package com.sinfonier.bolts;
 
import java.net.*;
import java.io.*;
import java.util.regex.*;
 
public class LogParser extends BaseSinfonierBolt {
 
    private String datastring = "";
    private String field = "";
    
    public LogParser(String xmlFile) {
        super(xmlFile);
    }
 
    @Override
    public void userprepare() {
        
        this.field = (String)this.getParam("log");
    }
 
    @Override
    public void userexecute() {
        this.datastring = (String) this.getField(this.field);
        try{
            System.out.println(content);
            String[] dataarray = content.trim().split(",");

            for (int i=0; i<dataarray.length; i++){
               
               //Separamos el mensaje del LOG en campos y sus respectivos valores
                String[] campo = dataarray[i].trim().split(":");

                if (campo.length == 2){
                    System.out.println("Se añaden los campos: "+dataarray[i]+campo[0]+campo[1]);
                    this.addField((String) campo[0].trim(),(String) campo[1].trim());
                }
            }

        }catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
        // Mandatory. Emit the tuple to the next bolt
        this.emit();
        
    }
    public void usercleanup() {
    }
 
}
