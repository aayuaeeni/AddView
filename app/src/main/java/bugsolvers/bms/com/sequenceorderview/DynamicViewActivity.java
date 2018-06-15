
package bugsolvers.bms.com.sequenceorderview;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DynamicViewActivity extends AppCompatActivity
{

    EditText textIn;
    Button buttonAdd;
    LinearLayout container;
    Button buttonShowAll;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_view);

        textIn = (EditText) findViewById(R.id.textin);
        buttonAdd = (Button) findViewById(R.id.add);
        container = (LinearLayout) findViewById(R.id.container);

        buttonAdd.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.dynamic_row, null);
                final TextView textOut = (TextView) addView.findViewById(R.id.textout);
                textOut.setText(textIn.getText().toString());
                Button buttonRemove = (Button) addView.findViewById(R.id.remove);
                buttonRemove.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                    }
                });

                Button buttonInsert = (Button) addView.findViewById(R.id.insert);
                buttonInsert.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {
                        String text = textOut.getText().toString();
                        String newText = textIn.getText().toString() + text;
                        textIn.setText(newText);
                    }
                });

                container.addView(addView, 0);
            }
        });

        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);

        buttonShowAll = (Button) findViewById(R.id.showall);
        buttonShowAll.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {

                String showallPrompt = "";

                int childCount = container.getChildCount();
                showallPrompt += "childCount: " + childCount + "\n\n";

                for (int c = 0; c < childCount; c++)
                {
                    View childView = container.getChildAt(c);
                    TextView childTextView = (TextView) (childView.findViewById(R.id.textout));
                    String childTextViewText = (String) (childTextView.getText());

                    showallPrompt += c + ": " + childTextViewText + "\n";
                }

                Toast.makeText(DynamicViewActivity.this,
                        showallPrompt,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
