package com.genue.sseumsseum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonthlyEarnAdapter extends RecyclerView.Adapter<MonthlyEarnAdapter.ViewHolder>
{
	private ArrayList<MonthlyIOInfo> mLists;
	//아이템뷰 저장
	public class ViewHolder extends RecyclerView.ViewHolder {

		TextView et1;
		TextView et2;
		TextView et3;
		TextView et4;
		TextView et5;

		public ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			et1 = itemView.findViewById(R.id.et1);
			et2 = itemView.findViewById(R.id.et2);
			et3 = itemView.findViewById(R.id.et3);
			et4 = itemView.findViewById(R.id.et4);
			et5 = itemView.findViewById(R.id.et5);
		}
	}

	public MonthlyEarnAdapter(ArrayList<MonthlyIOInfo> lists){
		mLists = lists;
	}

	@NonNull
	@Override
	public MonthlyEarnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		Context context = parent.getContext();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

		View view = inflater.inflate(R.layout.item_view, parent, false);
		MonthlyEarnAdapter.ViewHolder vh = new MonthlyEarnAdapter.ViewHolder(view);

		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull MonthlyEarnAdapter.ViewHolder holder, int position)
	{
		holder.et1.setText(mLists.get(position).title + "");
		holder.et2.setText(mLists.get(position).money + "");
		holder.et3.setText(mLists.get(position).type + "");
		holder.et4.setText(mLists.get(position).dayType + "");
		holder.et5.setText(mLists.get(position).explain + "");
	}

	@Override
	public int getItemCount()
	{
		return mLists.size();
	}
}
