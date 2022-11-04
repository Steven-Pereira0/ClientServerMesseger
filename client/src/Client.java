import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket socket = null; // A socket is the endpoint of communication between 2 pcs
        InputStreamReader inputStreamReader = null; // Stream for the sequence of data. Can be byte or character
        OutputStreamWriter outputStreamWriter = null; // Output & Input are bridges from byte streams to character streams.

        BufferedReader bufferedReader = null;// flushing a sream forces any buffered bytes to be written out
        BufferedWriter bufferedWriter = null;

        try{
            socket= new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true){

                String sendMessage = scanner.nextLine();

                bufferedWriter.write(sendMessage);
                bufferedWriter.newLine();
                bufferedWriter.flush(); // flush when buffer is full.

                System.out.println(bufferedReader.readLine());

                if (sendMessage.equalsIgnoreCase("BYE"))
                    break;

            }

        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(socket != null)
                    socket.close();
                if(inputStreamReader != null)
                    inputStreamReader.close();
                if(outputStreamWriter != null)
                    outputStreamWriter.close();
                if(bufferedReader !=null)
                    bufferedReader.close();
                if(bufferedWriter !=null)
                    bufferedWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }
}
