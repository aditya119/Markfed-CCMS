/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

/**
 *
 * @author Aditya Sharma
 */
public class FileType {
    public String getLastToken(String str, String separatorRegex) {
        String tokens[] = str.split(separatorRegex);
        return "application/"+tokens[tokens.length - 1];
    }
}