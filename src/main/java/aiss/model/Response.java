package aiss.model;

public class Response {

    private Image data;
    private Boolean success;
    private Integer status;

    public Response() {}

    public Response(Image data, Boolean success, Integer status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }

    public Image getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getStatus() {
        return status;
    }
}
