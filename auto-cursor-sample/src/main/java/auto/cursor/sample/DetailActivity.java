package auto.cursor.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import model3.Person;

public class DetailActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    Person person = getIntent().getParcelableExtra("Person");
    textView(R.id.name).setText("Name:" + person.name());
    textView(R.id.id).setText("Id:" + person.id());
  }

  private TextView textView(int id) {
    return (TextView) findViewById(id);
  }
}
