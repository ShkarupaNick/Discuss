
package ua.com.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "self",
        "previousepisode",
        "nextepisode"
})
@Entity
public class Links implements Serializable {


    @JsonIgnore
    @Id
    @GeneratedValue( generator="uuid" )
    @GenericGenerator(
            name="uuid",
            strategy="org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name="uuid_gen_strategy_class",
                            value="org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    public UUID uuid;

    @JsonProperty("self")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="self_uuid",referencedColumnName="uuid")
    private Self self;


    @JsonProperty("previousepisode")


    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="previousepisode_uuid",referencedColumnName="uuid")
    private Previousepisode previousepisode;


    @JsonProperty("nextepisode")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="nextepisode_uuid",referencedColumnName="uuid")
    private Nextepisode nextepisode;


    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The self
     */
    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    /**
     * @param self The self
     */
    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    /**
     * @return The previousepisode
     */
    @JsonProperty("previousepisode")
    public Previousepisode getPreviousepisode() {
        return previousepisode;
    }

    /**
     * @param previousepisode The previousepisode
     */
    @JsonProperty("previousepisode")
    public void setPreviousepisode(Previousepisode previousepisode) {
        this.previousepisode = previousepisode;
    }

    /**
     * @return The nextepisode
     */
    @JsonProperty("nextepisode")
    public Nextepisode getNextepisode() {
        return nextepisode;
    }

    /**
     * @param nextepisode The nextepisode
     */
    @JsonProperty("nextepisode")
    public void setNextepisode(Nextepisode nextepisode) {
        this.nextepisode = nextepisode;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
