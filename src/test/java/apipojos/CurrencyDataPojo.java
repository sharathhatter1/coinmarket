package apipojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDataPojo {

		
		private Long id;
	    private String name;
	    private List<String> tags;
	    private String symbol;
		
	    @JsonProperty("id")
	    public Long getId() {
			return id;
		}
	    @JsonProperty("id")
		public void setId(Long id) {
			this.id = id;
		}
	    

	    @JsonProperty("name")
		public String getName() {
			return name;
		}
	    @JsonProperty("name")
		public void setName(String name) {
			this.name = name;
		}
	    

	    @JsonProperty("tags")
		public List<String> getTags() {
			return tags;
		}
	    @JsonProperty("tags")
		public void setTags(List<String> tags) {
			this.tags = tags;
		}

	    @JsonProperty("symbol")
		public String getSymbol() {
			return symbol;
		}

	    @JsonProperty("symbol")
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
	    

}
