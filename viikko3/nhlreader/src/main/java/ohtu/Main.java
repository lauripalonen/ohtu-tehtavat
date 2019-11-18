package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.http.client.fluent.Request;


public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        Date date = new Date();

        ArrayList<Player> finns = new ArrayList<>();

        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                finns.add(player);
            }
        }

        Collections.sort(finns);

        System.out.println("Players from FIN " + formatter.format(date) + "\n");

        for (Player player : finns) {
            System.out.printf("%-20s %-5s %s\n",
                    player.getName(),
                    player.getTeam(),
                    (player.getGoals() + " + " + player.getAssists() + " = " + (player.getGoals() + player.getAssists())));
        }

    }

}
