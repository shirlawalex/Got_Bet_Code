import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

class Writer {
    File output;
    FileWriter writer;
    BufferedWriter buff;

    Writer (String target) {
        output = new File(target);
        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
            output.createNewFile();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        buff = new BufferedWriter(writer);
    }

    void write (String content){
        try {
            buff.write(content);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    void close() {
            try {
                if (buff != null)
                    buff.close();
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }



}