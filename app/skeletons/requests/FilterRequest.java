package skeletons.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FilterRequest extends BaseRequest
{
    private Map<String, List<Object>> filters = new HashMap<>();
    private Map<String, Map<String, Object>> rangeFilters = new HashMap<>();
    private int offset = 0;
    private int count = 100;
}
