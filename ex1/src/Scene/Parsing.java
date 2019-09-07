/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Scene;

import Utils.Edge;
import Utils.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parsing {
    /*****
     * Parse the information of the admin_details file
     */
    public static List[] parse_scn(String filePath) {
        List<Vertex> vertexList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();
        String row;
        String []info;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            row = reader.readLine();
            while(row != null) {
                //skip empty lines
                if(row.equals("")){
                    row = reader.readLine();
                    continue;
                }
                // read the vertexes from the scn
                int vertexNum = Integer.parseInt(row);
                for (int i = 0; i < vertexNum; i++) {
                    row = reader.readLine();
                    if (row == null) {
                        reader.close();
                        System.out.println("Error while reading vertexes");
                        break;
                    }
                    info = row.split(" ");
                    vertexList.add(new Vertex(Double.parseDouble(info[0]), Double.parseDouble(info[1]), Double.parseDouble(info[2]), i));
                }

                // read the edges from the scn
                row = reader.readLine();
                if (row == null) {
                    reader.close();
                    System.out.println("Error while reading edges");
                } else {
                    int edgesNum = Integer.parseInt(row);
                    for (int i = 0; i < edgesNum; i++) {
                        row = reader.readLine();
                        if (row == null) {
                            reader.close();
                            System.out.println("Error while reading edges");
                            break;
                        }
                        info = row.split(" ");
                        int firstIndex = Integer.parseInt(info[0]);
                        int secondIndex = Integer.parseInt(info[1]);
                        edgeList.add(new Edge(firstIndex, secondIndex));
                    }
                }
                row = reader.readLine();
            }
            reader.close();
            List[] lst = new List[2];
            lst[0] = vertexList;
            lst[1] = edgeList;
            return lst;
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file reader\n");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("Could not read from config file\n");
            e.printStackTrace();
            return null;
        }
    }

    public static List[] parse_viw(String filePath) {
        String row;
        String []info;
        List[] lst = new List[5];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            for(int i=0;i<5;i++) {
                List<Double> temp = new ArrayList<>();
                row = reader.readLine();
                if (row == null) {
                    reader.close();
                    System.out.println("Error while reading line");
                }
                else {
                    info = row.split(" ");
                    for (int j = 1; j < info.length; j++) {
                        temp.add(Double.parseDouble(info[j]));
                    }
                    lst[i] = temp;
                }
            }
            reader.close();
            return lst;
       } catch (FileNotFoundException e) {
           System.out.println("Could not open file reader\n");
           e.printStackTrace();
           return null;
       } catch (IOException e) {
           System.out.println("Could not read from config file\n");
           e.printStackTrace();
           return null;
       }
    }
}