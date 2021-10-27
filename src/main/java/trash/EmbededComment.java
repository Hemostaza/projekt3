package trash;

import javax.persistence.Embeddable;

@Embeddable
public class EmbededComment {

    private String user; //String później zamienić na User
    private String text; //ok,

    public void setUser(String user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}
