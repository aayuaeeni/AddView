
package bugsolvers.bms.com.sequenceorderview;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private GridView gridView;
    private View btnGo;
    private ArrayList<String> selectedStrings;
    private static final String[] numbers = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12", "13", "14"};
    private LinearLayout container;
    private String selectedItem;
    private GridViewAdapter adapter;
    int selectedIndex;

    private View genericView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid);
        btnGo = findViewById(R.id.button);
        container = (LinearLayout) findViewById(R.id.container);

        //populateView();

        selectedStrings = new ArrayList<>();

        adapter = new GridViewAdapter(numbers, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {

                selectedIndex = adapter.selectedPositions.indexOf(position);
                if (selectedIndex > - 1)
                {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((GridItemView) v).display(false);

                    selectedStrings.remove((String) parent.getItemAtPosition(position));

                    ((LinearLayout) genericView.getParent()).removeView(genericView);


                } else
                {
                    adapter.selectedPositions.add(position);
                    selectedItem = adapter.getItem(position).toString();
                    ((GridItemView) v).display(true);
                    selectedStrings.add((String) parent.getItemAtPosition(position));

                    populateView();

                }


            }

        });
        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);
        //set listener for Button event
        btnGo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SelectedItemsActivity.class);
                intent.putStringArrayListExtra("SELECTED_LETTER", selectedStrings);
                startActivity(intent);
            }
        });
    }


    public void populateView()
    {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        final View addView = layoutInflater.inflate(R.layout.sequence_row, null);
        genericView = addView;
        final TextView tvStripe = (TextView) addView.findViewById(R.id.tvStripe);
        final TextView tvItemSelected = (TextView) addView.findViewById(R.id.tvItemSelected);
        tvItemSelected.setText(selectedItem);
        // tvStripe.setText(textIn.getText().toString());
        ImageView ivDecrease = (ImageView) addView.findViewById(R.id.ivDecrease);
        ImageView ivIncrease = (ImageView) addView.findViewById(R.id.ivIncrease);
        final ImageView ivRemove = (ImageView) addView.findViewById(R.id.ivRemove);
        ivDecrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tvStripe.setText(String.valueOf(Integer.parseInt(tvStripe.getText().toString()) - 1));

            }
        });

        ivIncrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tvStripe.setText(String.valueOf(Integer.parseInt(tvStripe.getText().toString()) + 1));

            }
        });

        ivRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((LinearLayout) addView.getParent()).removeView(addView);

            }
        });

        container.addView(genericView, 0);
    }

}
