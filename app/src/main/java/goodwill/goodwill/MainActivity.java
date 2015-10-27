package goodwill.goodwill;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    public enum Color {
        YELLOW("yellow", R.color.yellow),
        GREEN("green", R.color.green),
        BLUE("blue", R.color.blue),
        RED("red", R.color.red),
        WRONG("wrong", R.color.green);

        public final String name;
        public final int color;

        private Color(String name, int color) {
            this.name = name;
            this.color = color;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView colorView = (TextView)findViewById(R.id.color);

        Color thisWeeksColor = getThisWeeksColor();

        colorView.setText(thisWeeksColor.name);
        colorView.setBackgroundColor(getResources().getColor(thisWeeksColor.color));
    }

    public Color getThisWeeksColor() {
        Calendar today = Calendar.getInstance();

        Calendar knownColorDate = Calendar.getInstance();

        // 4 = may because that makes sense.
        knownColorDate.set(2015, Calendar.MAY, 24);

        int weeks = diffInWeeks(knownColorDate, today);

        switch (weeks % 4){
            case 0: return Color.YELLOW;
            case 1: return Color.GREEN;
            case 2: return Color.BLUE;
            case 3: return Color.RED;
        }
        return Color.WRONG;
    }

    public int diffInWeeks(Calendar c1, Calendar c2) {
        int diff = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 52;

        int startWeek = c1.get(Calendar.WEEK_OF_YEAR);
        int endWeek = c2.get(Calendar.WEEK_OF_YEAR);

        diff += endWeek - startWeek;
        return diff;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
