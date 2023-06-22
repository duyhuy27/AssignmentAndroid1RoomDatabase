package huyndph30375.fpoly.huyndph30375_assignment.constant;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = space;

        // Add top margin to the first item
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
    }
}
