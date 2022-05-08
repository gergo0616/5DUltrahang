package hu.mobilalkfejl.a5d_ultrahang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingItemAdapter extends RecyclerView.Adapter<BookingItemAdapter.ViewHolder> implements Filterable {
    private static final String LOG_TAG = BookingItemAdapter.class.getName();
    private ArrayList<BookingData> mBookingItemsData;
    private Context mContext;
    private int lastPosition = -1;

    BookingItemAdapter(Context context, ArrayList<BookingData> itemsData){
        this.mBookingItemsData = itemsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BookingItemAdapter.ViewHolder holder, int position) {
        BookingData currentItem = mBookingItemsData.get(position);

        holder.bindTo(currentItem);

        if(holder.getAbsoluteAdapterPosition() > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAbsoluteAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mBookingItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTime;
        private TextView mDate;
        private RadioButton mStatus;



        public ViewHolder(View itemView) {
            super(itemView);

            mTime = itemView.findViewById(R.id.time);
            mDate = itemView.findViewById(R.id.text_view_date);
            //mStatus = itemView.findViewById(R.id.status);

            itemView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View view) {
                    Log.d("Activity", "Hozzáadás sikeres");
                }
            });
        }

        public void bindTo(BookingData currentItem) {
            //mTime.setText(currentItem.getTime());
            //mDate.setText(currentItem.getDate());
            //mStatus.setText(currentItem)
        }
    }
}

