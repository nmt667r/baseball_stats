package contoroller;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class HomeContoroller {
	
	
    public static void main(String[] args) throws IOException {
        getStats(args);
    }
    
    public static void getStats(String[] args) throws IOException {
    	Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=1&type=avg").get();

        Elements statsArray = document.getElementsByClass("bb-playerTable__row");
        for (Element str: statsArray){
        		System.out.println(str.getElementsByClass("bb-playerTable__data").text());
        }
    }

}
