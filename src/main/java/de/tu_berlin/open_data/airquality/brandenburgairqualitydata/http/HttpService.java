package de.tu_berlin.open_data.airquality.brandenburgairqualitydata.http;

import org.springframework.core.io.UrlResource;

/**
 * Created by ahmadjawid on 6/20/17.
 */
public interface HttpService {


    UrlResource[] getUrlResources(String url, String sensorType);
}
