package com.android.csiapp.Crime.utils.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;

import com.android.csiapp.Crime.utils.entity.ChildEntity;
import com.android.csiapp.Crime.utils.entity.ParentEntity;
import com.android.csiapp.R;

public class ParentAdapter extends BaseExpandableListAdapter {

	private Context mContext;

	private ArrayList<ParentEntity> mParents;

	private OnChildTreeViewClickListener mTreeViewClickListener;

	public ParentAdapter(Context context, ArrayList<ParentEntity> parents) {
		this.mContext = context;
		this.mParents = parents;
	}

	@Override
	public ChildEntity getChild(int groupPosition, int childPosition) {
		return mParents.get(groupPosition).getChilds().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mParents.get(groupPosition).getChilds() != null ? mParents
				.get(groupPosition).getChilds().size() : 0;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isExpanded, View convertView, ViewGroup parent) {

		final ExpandableListView eListView = getExpandableListView();

		ArrayList<ChildEntity> childs = new ArrayList<ChildEntity>();

		final ChildEntity child = getChild(groupPosition, childPosition);

		childs.add(child);

		final ChildAdapter childAdapter = new ChildAdapter(this.mContext,
				childs);

		eListView.setAdapter(childAdapter);

		eListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				if (mTreeViewClickListener != null && childAdapter.getChildrenCount(groupPosition)==0) {
					mTreeViewClickListener.onClickPosition(groupPosition,
							childPosition, -1);
				}
				return false;
			}
		});

		eListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1,
					int groupIndex, int childIndex, long arg4) {
				if (mTreeViewClickListener != null) {
					mTreeViewClickListener.onClickPosition(groupPosition,
							childPosition, childIndex);
				}
				return false;
			}
		});

		eListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {

				LayoutParams lp = new LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, (child
								.getChildNames().size() + 1)
								* (int) mContext.getResources().getDimension(
										R.dimen.parent_expandable_list_height));
				eListView.setLayoutParams(lp);
			}
		});

		eListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {

				LayoutParams lp = new LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, (int) mContext
								.getResources().getDimension(
										R.dimen.parent_expandable_list_height));
				eListView.setLayoutParams(lp);
			}
		});
		return eListView;
	}

	public ExpandableListView getExpandableListView() {
		ExpandableListView mExpandableListView = new ExpandableListView(
				mContext);
		LayoutParams lp = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, (int) mContext
						.getResources().getDimension(
								R.dimen.parent_expandable_list_height));
		mExpandableListView.setLayoutParams(lp);
		mExpandableListView.setDividerHeight(0);
		mExpandableListView.setChildDivider(null);
		mExpandableListView.setGroupIndicator(null);
		return mExpandableListView;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mParents.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mParents != null ? mParents.size() : 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.parent_group_item, null);
			holder = new GroupHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (GroupHolder) convertView.getTag();
		}
		holder.update(mParents.get(groupPosition));
		return convertView;
	}

	class GroupHolder {

		private TextView parentGroupTV;

		public GroupHolder(View v) {
			parentGroupTV = (TextView) v.findViewById(R.id.parentGroupTV);
		}

		public void update(ParentEntity model) {
			parentGroupTV.setText(model.getGroupName());
			parentGroupTV.setTextColor(Color.parseColor("#000000"));
		}
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	public void setOnChildTreeViewClickListener(
			OnChildTreeViewClickListener treeViewClickListener) {
		this.mTreeViewClickListener = treeViewClickListener;
	}

	public interface OnChildTreeViewClickListener {
		void onClickPosition(int parentPosition, int groupPosition,
				int childPosition);
	}
}
