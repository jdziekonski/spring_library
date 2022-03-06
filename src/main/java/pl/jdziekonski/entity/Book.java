package pl.jdziekonski.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "requestedUrl",
        "items"
})
public class Book {
    @JsonProperty("requestedUrl")
    String requestedUrl;
    @JsonProperty("items")
    ArrayList<Item> items;
    @JsonProperty("requestedUrl")
    public String getRequestedUrl() {
        return requestedUrl;
    }
    @JsonProperty("requestedUrl")
    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}