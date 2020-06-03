package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<New> newArrayList;

    public MyAdapter(Context context, ArrayList<New> newArrayList) {
        this.context = context;
        this.newArrayList = newArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.new_row , parent , false );
        return new ViewHolder(view , context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        New currentNew = newArrayList.get( position );
        String imageUrl_ = currentNew.getImageUrl();
        String title_ = currentNew.getTitle();
        String source_ = currentNew.getSource();
        String date_ = currentNew.getDate();


        Picasso.get().load( imageUrl_ ).fit().centerInside().into( holder.newImage );
        holder.title.setText( title_ );
        holder.source.setText( source_ );
        holder.date.setText( dateTime(  date_ ) );


    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView newImage;
        public TextView title;
        public TextView source;
        public TextView date;



        public ViewHolder(@NonNull View itemView , final Context ctx) {

            super( itemView );
            context = ctx;
            newImage = (ImageView) itemView.findViewById( R.id.new_image );
            title = (TextView) itemView.findViewById( R.id.title_text_view );
            source = (TextView) itemView.findViewById( R.id.source_text_view );
            date = (TextView) itemView.findViewById( R.id.date_text_view );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    New currentNew = newArrayList.get(getAdapterPosition() );
                    Intent intent =new Intent( context , Detailed.class );
                    intent.putExtra( "new" , currentNew );
                    ctx.startActivity( intent );


                }
            } );
        }


    }

    public String dateTime(String t)
    {
        PrettyTime prettyTime  =new PrettyTime();
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:" );
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format( date );

        }
        catch (Exception e)
        {
            e.printStackTrace( );

        }
        return time;
    }
}
