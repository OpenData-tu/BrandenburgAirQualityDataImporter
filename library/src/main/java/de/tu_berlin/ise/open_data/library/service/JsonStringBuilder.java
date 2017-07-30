package de.tu_berlin.ise.open_data.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * Created by ahmadjawid on 7/13/17.
 *
 *  User can create a json object according to our data source schema using this utility class
 *  He/She should set the necessary fields using setter methods and then call the build method to produce json string
 */

@Service
public class JsonStringBuilder {


    private ApplicationService applicationService;

    private JsonNodeFactory nodeFactory;

    private ObjectNode mainObject;

    private ObjectNode sensors;

    private ObjectNode location;

    private ObjectNode extra;

    private String sourceId;

    private String device;

    private String timestamp;

    private String license;


    public JsonStringBuilder() {
        applicationService = new ApplicationServiceImpl();
        nodeFactory = JsonNodeFactory.instance;

        //Get an instance of object node. This is considered as the root json object (outer most) that contains all others objects and fields
        mainObject = nodeFactory.objectNode();

        //Will contains all sensors' objects (measurements)
        sensors = nodeFactory.objectNode();
        location = nodeFactory.objectNode();
    }

    public void setSourceId(String sourceId) {

        this.sourceId = sourceId;

    }

    public void setDevice(String device) {

        this.device = device;
    }

    public void setTimestamp(String timestamp) {

        this.timestamp = timestamp;
    }


    public void setLocation(String lat, String lon) {
        location.put("lat", applicationService.parseToDouble(lat));
        location.put("lon", applicationService.parseToDouble(lon));
    }

    public void setLicense(String license) {

        this.license = license;

    }

    public void setSensor(String measurement, String sensor, String observationValue) {
        ObjectNode secondLevelChild = nodeFactory.objectNode();
        secondLevelChild.put("sensor", sensor);
        secondLevelChild.put("observation_value", applicationService.parseToDouble(observationValue));
        sensors.set(measurement, secondLevelChild);
    }

    /**
     * Gets a Java Object, converts it to json, and adds, it as the extra field of our data source schema.
     * @param extra
     * */
    public void setExtra(Object extra) {

        ObjectMapper mapper = new ObjectMapper();

        //Convert extra to json object.
        ObjectNode objectNode = mapper.convertValue(extra, ObjectNode.class);

        this.extra = objectNode;

    }


    /**
    * Call this method once you set all properties.
     * Builds json an returns the value.
     * @return String
    * */
    public String build() throws JSONException {

        mainObject.put("sourceId", sourceId);
        mainObject.put("device", device);
        mainObject.put("timestamp", timestamp);
        mainObject.set("location", location);
        mainObject.put("license", license);
        mainObject.set("sensors", sensors);

        if (extra != null) {
            mainObject.set("extra", extra);
        }


        return mainObject.toString();
    }
}
