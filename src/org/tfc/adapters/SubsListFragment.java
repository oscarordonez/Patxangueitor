package org.tfc.adapters;

import android.content.Context;
import android.content.Intent;
import android.widget.*;
import org.tfc.patxangueitor.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.tfc.patxangueitor.subslistuser;


public class SubsListFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.subslist, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.explist);
        elv.setAdapter(new SubscriptionListAdapter(getActivity().getApplicationContext()));

        //setHasOptionsMenu(true);

        // Listview on child click listener
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                /*Toast.makeText(SubsListFragment.this.getActivity(),"Hola", Toast.LENGTH_SHORT).show();
                return false;*/
                Intent myIntent = new Intent(getActivity(), subslistuser.class);
                startActivity(myIntent);
                return false;
            }
        });
        return v;

    }
    /*@Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Subscripció 1", "Subscripció 2", "Subscripció 3" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);

        lv1 = (ListView)getView().findViewById(R.id.listView1);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent myIntent = new Intent(v.getContext(), subslist.class);
                startActivity(myIntent);
            }
        });
    }*/
    public class SubscriptionListAdapter extends BaseExpandableListAdapter{

        private String[] groups = { "Subscripcions"};
        private String[] children = {"Subscripció Llista 1","Subscripció Llista 2","Subscripció Llista 3"};
        private Context context;

        public SubscriptionListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            /*return children[i].length;*/
            return children.length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            /*return children[i][i1];*/
            return children[i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View groupV, ViewGroup viewGroup) {

            if (groupV == null){
                LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                groupV = inflater2.inflate(R.layout.subslist_group,null);
            }
            TextView textView = (TextView) groupV.findViewById(R.id.grouptextview);
            textView.setText(getGroup(i).toString());

            /*TextView textView = new TextView(SubsListFragment.this.getActivity());
            textView.setText(getGroup(i).toString());*/
            return groupV;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View childV, ViewGroup viewGroup) {

            if (childV == null){
                LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                childV = inflater2.inflate(R.layout.subslist_child,null);
            }
            TextView textView = (TextView) childV.findViewById(R.id.childtextview);
            textView.setText(getChild(i, i1).toString());

            return childV;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}