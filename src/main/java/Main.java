import exception.FleetException;

import java.io.IOException;

/**
 * Created with ♥ by georgeplaton on 02.03.18.
 */
public class Main {


    public static void main(String[] args) throws IOException {

        int vehicles[] = {14, 11, 15, 13};
        int p = 9;
        int c = 5;

        FleetService fleetService = new FleetService();
        try {
            System.out.println("FE needed: " + fleetService.calculateMinFE(vehicles, p, c));
        } catch (FleetException e) {
            e.printStackTrace();
        }

        return;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Enter scooters");
//        String s = br.readLine();
//        System.out.print("Enter number of scooters managed by FM:");
//        try{
//            int i = Integer.parseInt(br.readLine());
//        } catch(NumberFormatException nfe){
//            System.err.println("Invalid Format!");
//        }
//
//        System.out.print("Enter number of scooters managed by FE:");
//        try{
//            int i = Integer.parseInt(br.readLine());
//        } catch(NumberFormatException nfe){
//            System.err.println("Invalid Format!");
//        }
    }
}
