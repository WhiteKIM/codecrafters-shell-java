public enum Message {
    NOT_FOUND("command not found");

    private final String msg;

    Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
