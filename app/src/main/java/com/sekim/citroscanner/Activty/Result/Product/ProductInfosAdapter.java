package com.sekim.citroscanner.Activty.Result.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sekim.citroscanner.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductInfosAdapter extends RecyclerView.Adapter<ProductInfosAdapter.ViewHolder> {

    private JSONArray productInfos;

    public ProductInfosAdapter( JSONArray productInfos ){
        this.productInfos = productInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_product_info_item, parent, false);
        ProductInfosAdapter.ViewHolder viewHolder = new ProductInfosAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{

            JSONObject productInfo = (JSONObject) productInfos.getJSONObject(position);
            String productTitle = productInfo.getString("title");
            String productBody = productInfo.getString("body");

            holder.tvTitle.setText( productTitle );
            holder.tvBody.setText( productBody );

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return productInfos.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle, tvBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_rv_info_title);
            tvBody = itemView.findViewById(R.id.tv_rv_info_body);
        }
    }

}
