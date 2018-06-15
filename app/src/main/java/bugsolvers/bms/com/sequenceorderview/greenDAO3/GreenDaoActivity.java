
package bugsolvers.bms.com.sequenceorderview.greenDAO3;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import bugsolvers.bms.com.sequenceorderview.R;

public class GreenDaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        TextView tvName = (TextView) findViewById(R.id.tvName);

        // Put this in a different thread or use AsyncSession in greenDAO.
        // For Demo purpose, this query is made on main thread but it should in a different thread.
        User user = ((DemoApp)getApplication()).getDaoSession().getUserDao().load(1L);

        if(user != null){
            tvName.setText(user.getName());
        }
    }
}
