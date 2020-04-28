package skeletons.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRequest extends BaseRequest
{
    private Map<String, List<String>> filters = new HashMap<>();
    private Map<String, Map<String, String>> rangeFilters = new HashMap<>();
    private int offset = 0;
    private int count = 100;
}
