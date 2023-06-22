package huyndph30375.fpoly.huyndph30375_assignment.models;

import android.graphics.Bitmap;

public class News {
    private Bitmap resourceId;

    public News(Bitmap resourceId) {
        this.resourceId = resourceId;
    }

    public News() {
    }

    public Bitmap getResourceId() {
        return resourceId;
    }

    public void setResourceId(Bitmap resourceId) {
        this.resourceId = resourceId;
    }
}
