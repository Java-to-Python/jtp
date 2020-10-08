// This file will be for the reader.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Java_Parser
{
  public static void file_reader(String file)
  {
    BufferedReader reader;
    try {
      reader = new BufferedReader( new FileReader( file));
      String line = reader.readLine();
      while (line != null)
      {
        //translates code and writes code to file
        
        line = reader.readLine();
        
      }
      reader.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
