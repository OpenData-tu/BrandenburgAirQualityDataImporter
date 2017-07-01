package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmadjawid on 6/20/17.
 */

@Service
public class HttpServiceImpl implements HttpService {
    @Override
    public UrlResource[] getUrlResources(String url, String sensorType) {

        if (!url.endsWith("/"))
            url = url + "/";


        List<UrlResource> urlResourceList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.getElementsByTag("a");

            String href;
            for (Element link : links) {
                if ((href = link.attr("href")).contains(sensorType) && href.endsWith(".csv")) {

                    urlResourceList.add(new UrlResource(new URL(url + href)));
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return urlResourceList.toArray(new UrlResource[]{});

    }
}
