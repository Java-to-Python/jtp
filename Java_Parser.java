// This file will be for the reader.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class read
{
  public static void file_reader(string file)
  {
    BufferedReader reader;
    try {
      reader = new BufferedReader( new FileReader( file));
      String line = reader.readLiine();
      while (line != null)
      {
        //translates code and writes code to file
        
        line = reader.readLine();
        
      }
      reader.close();
    }
    catch (IOExcpetiion e)
    {
      e.printStackTrace();
    }
  }
}
