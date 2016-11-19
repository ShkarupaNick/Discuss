
package ua.com.entity;

import java.io.Serializable;
import java.util.*;
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
    "id",
    "url",
    "name",
    "type",
    "language",
    "genres",
    "status",
    "runtime",
    "premiered",
    "schedule",
    "rating",
    "weight",
    "network",
    "webChannel",
    "externals",
    "image",
    "summary",
    "updated",
    "_links"
})


@Entity
public class Show implements Serializable {

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

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    @Column(name = "url", length = 1028)
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("language")
    private String language;


    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JsonProperty("genres")
    private List<String> genres = new ArrayList<String>();
    @JsonProperty("status")
    private String status;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("premiered")
    private String premiered;


    @JsonProperty("schedule")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    @JoinColumn(name="schedule_uuid",referencedColumnName="uuid")
    private Schedule schedule;

    @JsonProperty("rating")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    @JoinColumn(name="rating_uuid",referencedColumnName="uuid")
    private Rating rating;

    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("network")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    @JoinColumn(name="network_uuid",referencedColumnName="uuid")
    private Network network;

    @JsonIgnore
    @Transient
    @JsonProperty("webChannel")
    private Object webChannel;


    @JsonProperty("externals")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    @JoinColumn(name="externals_uuid",referencedColumnName="uuid")
    private Externals externals;


    @JsonProperty("image")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    @JoinColumn(name="image_uuid",referencedColumnName="uuid")
    private Image image;

    @JsonProperty("summary")
    @Column(name = "summary", length = 5000)
    private String summary;
    @JsonProperty("updated")
    private Integer updated;


    @JsonProperty("_links")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    @JoinColumn(name="_links_uuid",referencedColumnName="uuid")

    private Links _links;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The language
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The genres
     */
    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    @JsonProperty("genres")
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    @JsonProperty("runtime")
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    @JsonProperty("runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The premiered
     */
    @JsonProperty("premiered")
    public String getPremiered() {
        return premiered;
    }

    /**
     * 
     * @param premiered
     *     The premiered
     */
    @JsonProperty("premiered")
    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    /**
     * 
     * @return
     *     The schedule
     */
    @JsonProperty("schedule")
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * 
     * @param schedule
     *     The schedule
     */
    @JsonProperty("schedule")
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * 
     * @return
     *     The rating
     */
    @JsonProperty("rating")
    public Rating getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    @JsonProperty("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The weight
     */
    @JsonProperty("weight")
    public Integer getWeight() {
        return weight;
    }

    /**
     * 
     * @param weight
     *     The weight
     */
    @JsonProperty("weight")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 
     * @return
     *     The network
     */
    @JsonProperty("network")
    public Network getNetwork() {
        return network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    @JsonProperty("network")
    public void setNetwork(Network network) {
        this.network = network;
    }

    /**
     * 
     * @return
     *     The webChannel
     */
    @JsonProperty("webChannel")
    public Object getWebChannel() {
        return webChannel;
    }

    /**
     * 
     * @param webChannel
     *     The webChannel
     */
    @JsonProperty("webChannel")
    public void setWebChannel(Object webChannel) {
        this.webChannel = webChannel;
    }

    /**
     * 
     * @return
     *     The externals
     */
    @JsonProperty("externals")
    public Externals getExternals() {
        return externals;
    }

    /**
     * 
     * @param externals
     *     The externals
     */
    @JsonProperty("externals")
    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")

    public Image getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The summary
     */
    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The updated
     */
    @JsonProperty("updated")
    public Integer getUpdated() {
        return updated;
    }

    /**
     * 
     * @param updated
     *     The updated
     */
    @JsonProperty("updated")
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    /**
     * 
     * @return
     *     The links
     */
    @JsonProperty("_links")
    public Links getLinks() {
        return _links;
    }

    /**
     * 
     * @param links
     *     The _links
     */
    @JsonProperty("_links")
    public void setLinks(Links links) {
        this._links = links;
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
