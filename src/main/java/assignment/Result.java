package assignment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("CODE")
    @Expose
    private String code;
    @SerializedName("MESSAGE")
    @Expose
    private String message;

}