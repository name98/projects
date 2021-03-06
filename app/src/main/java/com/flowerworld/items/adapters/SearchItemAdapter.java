package com.flowerworld.items.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flowerworld.R;
import com.flowerworld.fragments.Router;
import com.flowerworld.fragments.SearchFragmentEditMode;
import com.flowerworld.interfaces.ParentFragment;
import com.flowerworld.items.SearchItem;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemHolder>{
    private ArrayList<SearchItem> searches =new ArrayList<>();
    private ParentFragment parent;

    public void setSearches(ArrayList<SearchItem> searches) {
        this.searches = searches;
        notifyDataSetChanged();
    }

    public void setParent(ParentFragment parent) {
        this.parent = parent;
    }

    @NonNull
    @Override
    public SearchItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        return new SearchItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemHolder holder, int position) {
        SearchItem searchItem = searches.get(position);
        holder.bind(searchItem, parent);
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    static class SearchItemHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView itemTextView;

        SearchItemHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.search_item_icon_type_image_view);
            itemTextView = itemView.findViewById(R.id.search_item_item_text_view);
        }

        void bind(SearchItem item, ParentFragment parent) {
            setIcon(item.getType());
            setTextView(item.getText());
            setListener(item, parent);

        }

        private void setTextView(String text) {
            itemTextView.setText(text);
        }

        private void setIcon(String type) {
            if (type.equals("tag"))
                iconImageView.setImageResource(R.drawable.ic_dehaze);
            else
                iconImageView.setImageResource(R.drawable.ic_search_black_24dp);
        }

        private void setListener(final SearchItem item, final ParentFragment parentFragment) {
            itemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getType().equals("tag"))
                        parentFragment.sendParentMessage(SearchFragmentEditMode.GRID_FRAGMENT, item.getText(), item.getText());
                    else
                        parentFragment.sendParentMessage(SearchFragmentEditMode.FLOWER_FRAGMENT, String.valueOf(item.getId()), item.getText());
                    hideKeyboard();
                }
            });
        }

        private void hideKeyboard() {
            InputMethodManager imm = (InputMethodManager) itemView.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(itemView.getWindowToken(), 0);
        }
    }
}
