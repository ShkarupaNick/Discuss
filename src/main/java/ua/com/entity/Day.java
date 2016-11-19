package ua.com.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ShkarupaN on 14.10.2016.
 */
enum Day {

    @JsonProperty
    Sunday,
    @JsonProperty
    Monday,
    @JsonProperty
    Tuesday,
    @JsonProperty
    Wednesday,
    @JsonProperty
    Thursday,
    @JsonProperty
    Friday,
    @JsonProperty
    Saturday;



};