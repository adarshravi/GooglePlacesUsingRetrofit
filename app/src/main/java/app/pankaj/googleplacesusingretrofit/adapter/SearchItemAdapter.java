package app.pankaj.googleplacesusingretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.pankaj.googleplacesusingretrofit.R;
import app.pankaj.googleplacesusingretrofit.activity.SplashScreen;
import app.pankaj.googleplacesusingretrofit.model.ResponseData;
import app.pankaj.googleplacesusingretrofit.model.Result;



public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.MyViewHolder>{

    Context context;
    List<Result> listResult;

    public SearchItemAdapter(Context context,List<Result> listResult)
    {
        this.context=context;
        this.listResult=listResult;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.search_item_info,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Result result=listResult.get(position);
        holder.tvName.setText(result.getName());
        holder.tvType.setText(result.getTypes().get(0));
        if(result.getPhotos()!=null && result.getPhotos().size()>0)
        {
            String url="https://maps.googleapis.com/maps/api/place/photo?maxwidth=50&maxheight=50&photoreference="+result.getPhotos().get(0).getPhoto_reference()+"&key=AIzaSyDHxSsf1YQlDhyxr9Ho-jKokac2xN_pBxc";
            Log.e("icon===","icon======="+url);
            Picasso.with(context)
                    .load(url)
                    .resize(50, 50)
                    .into(holder.ivIcon);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it=new Intent(context, MapActivtyName.class);
//                Bundle bundle=new Bundle();
//                bundle.putDouble("lat",listResult.get(position).getGeometry().getLocation().getLat());
//                bundle.putDouble("lng",listResult.get(position).getGeometry().getLocation().getLng());
//                it.putExtras(bundle);
//                context.startActivity(it);
                Toast.makeText(context, "lat==="+listResult.get(position).getGeometry().getLocation().getLat()+"----"+
                        "lng==="+listResult.get(position).getGeometry().getLocation().getLng(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvType;
        ImageView ivIcon;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvType= (TextView) itemView.findViewById(R.id.tvType);
            ivIcon= (ImageView) itemView.findViewById(R.id.ivIcon);
            view=itemView;

        }
    }
}
