package com.byankina.bibaboba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

public class GameDescriptionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> gameNames;
    private List<String> gameDescriptions;

    public GameDescriptionAdapter(Context context, List<String> gameNames, List<String> gameDescriptions) {
        this.context = context;
        this.gameNames = gameNames;
        this.gameDescriptions = gameDescriptions;
    }

    @Override
    public int getGroupCount() {
        return gameNames.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gameNames.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gameDescriptions.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }

        TextView gameNameTextView = (TextView) convertView.findViewById(android.R.id.text1);
        gameNameTextView.setText(gameNames.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_2, null);
        }

        TextView descriptionTextView = (TextView) convertView.findViewById(android.R.id.text2);
        descriptionTextView.setText(gameDescriptions.get(groupPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
