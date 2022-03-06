package pl.jdziekonski.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="requestedUrl")
@JsonPropertyOrder({
        "id",
        "volumeInfo",
})
public class Item {

    @JsonProperty("id")
    private String id;

    @JsonProperty("volumeInfo")
    private VolumeInfo volumeInfo;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("volumeInfo")
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

}