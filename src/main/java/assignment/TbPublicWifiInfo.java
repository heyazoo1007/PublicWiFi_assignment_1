package assignment;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated("jsonschema2pojo")
public class TbPublicWifiInfo {

    @SerializedName("list_total_count")
    @Expose
    private Integer listTotalCount;

    @SerializedName("RESULT")
    @Expose
    private Result result;

    @SerializedName("row")
    @Expose
    private List<Row> row = null;
}