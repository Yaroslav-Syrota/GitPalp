package ua.com.thinkmobiles.gitpalp.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;

/**
 * Created by CAT_Caterpiller on 24.08.2016.
 */

public abstract class BaseAdapterDelegate<T, H extends BaseRecyclerHolder> implements AdapterDelegate<T> {

    @Override
    public boolean isForViewType(@NonNull T item, int position) {
        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return getHolder(LayoutInflater.from(parent.getContext()).inflate(getHolderLayoutRes(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull T item, int position, @NonNull RecyclerView.ViewHolder holder) {
        ((BaseRecyclerHolder) holder).updateContent(item);
    }

    protected abstract int getHolderLayoutRes();
    protected abstract H getHolder(final View inflatedView);
}
