/**
 * Jeoff Villanueva
 * CSCE 343
 * 2 October 2015
 * Homework 2
 * Resources:
 *  - download source page: http://stackoverflow.com/questions/238547/how-do-you-
 *  programmatically-download-a-webpage-in-java
 *  - regex: http://stackoverflow.com/questions/600733/using-java-to-find-substring-
 *  of-a-bigger-string-using-regular-expression
 */

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class url {

    public static final String href = "a href=\"(.*?)\"";
    public static final Pattern hrefPattern = Pattern.compile(href);
    public static final String hrefBlank = "a target=\"_blank\" href=\"(.*?)\"";
    public static final Pattern hrefPatternBlank = Pattern.compile(hrefBlank);
    public static final String http = "http://";
    public static final Pattern httpPattern = Pattern.compile(http);
    public static final String https = "https://";
    public static final Pattern httpsPattern = Pattern.compile(https);
    public static final String ftp = "ftp://";
    public static final Pattern ftpPattern = Pattern.compile(ftp);

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0 || args.length > 1) {
            System.out.println("Usage: url [www.url.com]");
        } else {
            String theUrl = args[0];
            URL url;
            InputStream streamIn = null;
            BufferedReader br;
            String line;
            PrintWriter outfile = new PrintWriter("javaLinks.txt");
            try {
                url = new URL(theUrl);
                streamIn = url.openStream();  // throws an IOException
                br = new BufferedReader(new InputStreamReader(streamIn));
                long lineNumber = 1;
                String temp;
                while ((line = br.readLine()) != null) {
                    temp = linkType(line);
                    if(temp.length()!=0 && grabLink(line).length()!=0){
                        outfile.println(lineNumber + " " + grabLink(line) + " is absolute ");
                    }
                    else if(grabLink(line).length()!=0 && grabLink(line).compareToIgnoreCase("#")!=0){
                        outfile.printf("%d %s is relative\n", lineNumber, grabLink(line));
                    }
                    else if(temp.length()!=0 && temp.length()!=0 && grabNewTabLink(line).length()!=0){
                        outfile.println(lineNumber + " " + grabNewTabLink(line) + " is absolute ");
                    }
                    else if(temp.length()!=0 && grabLink(line).length()!=0 && grabNewTabLink(line).compareToIgnoreCase("#")!=0){
                        outfile.printf("%d %s is relative\n", lineNumber, grabNewTabLink(line));
                    }
                    lineNumber++;
                }
                outfile.close();
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (streamIn != null) streamIn.close();
                } catch (IOException ioe) {
                    // nothing to see here
                }
            }
        }//else
    }

    public static String grabLink(String line){
        Matcher m = hrefPattern.matcher(line);
        if (m.find( )) {
            String link = m.group(1);
            return link;
        }
        return "";
    }

    public static String grabNewTabLink(String line){
        Matcher m = hrefPatternBlank.matcher(line);
        if (m.find( )) {
            String link = m.group(1);
            return link;
        }
        return "";
    }

    public static String linkType(String line){
        Matcher m = httpPattern.matcher(line);
        if(m.find()){
            return "http";
        }
        m = httpsPattern.matcher(line);
        if(m.find()){
            return "https";
        }
        m = ftpPattern.matcher(line);
        if(m.find()){
            return "ftp";
        }
        return "";
    }

}
