
public class Idea {
    int id;
    String text;
    boolean hide = false;

    public Idea(int id, String text) {
        this.text = text;
        this.id = id;
    }

    public Idea(String text) {
        this.text = text;
    }

    public Idea() {
    }

    public Idea(boolean hide) {
        this.hide = hide;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
