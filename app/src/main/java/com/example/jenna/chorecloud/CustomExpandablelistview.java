package com.example.jenna.chorecloud;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomExpandablelistview extends BaseExpandableListAdapter{

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    /**
     * Creates an instance of a BaseExpandableListAdapter with the given information
     * @param context Abstract class provided by Android studio
     * @param expandableListTitle List of Titles in the list
     * @param expandableListDetail Instance of the expandablelistview class
     */
    public CustomExpandablelistview(Context context, List<String> expandableListTitle, HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    /**
     * Gets a title given a ListPosition, and then returns an object that is stored in the expandedListPosition for that title
     * @param listPosition Position within the list of titles
     * @param expandedListPosition Position within the HashMap (strings displayed as children in the list)
     * @return Object
     */
    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    /**
     * Return the position in the HashMap of a specific object
     * @param listPosition Position within the list of titles
     * @param expandedListPosition Position within the HashMap
     * @return The position of an object in the Hashmap
     */
    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    /**
     * Get the formatted string that will be displayed as a child in the expanded list
     * @param listPosition Position within the list of titles
     * @param expandedListPosition Position within the HashMap of lists (Children strings)
     * @param isLastChild Boolean value
     * @param convertView View that will be applied to the child items in the list
     * @param parent ViewGroup of parent
     * @return View with formatted string
     */
    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.namelist_chore_listitem, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    /**
     * Returns the number of child strings in the HashMap for a given title
     * @param listPosition Position of the title in the List
     * @return Number of child strings
     */
    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    /**
     * Get a specific group of child strings within the list
     * @param listPosition Position within List of Titles
     * @return The group of child strings within the list
     */
    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    /**
     * Get the number of groups (titles) in the list
     * @return Number of groups
     */
    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    /**
     * Get the ID of a group (title)
     * @param listPosition Position within a list
     * @return The Id of a group
     */
    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    /**
     * Get the formatted title that will displayed in the list when expanded or not
     * @param listPosition Position within the list of titles
     * @param isExpanded Boolean value that represents if the list is expanded or not
     * @param convertView View that will determine formatting of list
     * @param parent View of parent
     * @return View with title filled in
     */
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,View convertView, ViewGroup parent) {
       String listTitle = (String) getGroup(listPosition);
       if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.namelist_chore_listgroup, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    /**
     * Checks to see if there are stable Ids
     * @return False
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Checks to see if a Child string is selectable
     * @param listPosition Position withing the list of titles
     * @param expandedListPosition Position within the HashMap of child strings
     * @return True
     */
    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}

