package jio.com.jiogames;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class
CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> gameName;
    ArrayList<String> companyName;
    ArrayList<String> imageName;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> gameName, ArrayList<String> companyName, ArrayList<String> imageName) {
        this.context = context;
        this.gameName = gameName;
        this.companyName = companyName;
        this.imageName = imageName;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_slide_up, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.gameName.setText(gameName.get(position));
        holder.companyName.setText(companyName.get(position));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, gameName.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return gameName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gameName, companyName;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

           /* // get the reference of item view's
            gameName = (TextView) itemView.findViewById(R.id.crews_title_view);
            companyName = (TextView) itemView.findViewById(R.id.crews_title_view_1);*/


        }
    }
}
