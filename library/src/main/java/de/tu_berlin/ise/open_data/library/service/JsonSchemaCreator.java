package de.tu_berlin.ise.open_data.library.service;


import de.tu_berlin.ise.open_data.library.model.Schema;
import org.json.JSONException;

/**
 * Created by ahmadjawid on 6/9/17.
 * Used to create valid json. returns the result to writer
 */
public interface JsonSchemaCreator {

     /**
      * Get an objects which is extended from {@link de.tu_berlin.ise.open_data.library.model.Schema} class
      * and converts it to json
      * @param schema
      * @return String
      * */
     String create(Schema schema) throws JSONException;
}
