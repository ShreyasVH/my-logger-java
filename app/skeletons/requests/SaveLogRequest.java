package skeletons.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveLogRequest extends BaseRequest
{
    private String type;

    private String source;

    private String content;
}
