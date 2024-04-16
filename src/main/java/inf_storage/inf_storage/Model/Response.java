package inf_storage.inf_storage.Model;

public class Response {
    private int status;
    private String message;
    byte[] data;
    private String fileName;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status) {
        this.status = status;
    }




    public int getStatus() {
        return status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
