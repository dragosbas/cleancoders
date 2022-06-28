package cleancoders.codecast;

import java.util.Objects;


public class Entity implements Cloneable {
    protected String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public boolean isSame(Entity entity) {
        if (id == null || entity.id==null) return false;
        return  Objects.equals(id, entity.id);
    }

}
