package android.leo.drawline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawLine electricLine;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        data.add("50");
        data.add("60");
        data.add("80");
        data.add("130");
        data.add("90");
        data.add("70");
        data.add("160");
        data.add("320");
        setData(data);
    }

    private void setData(List<String> data) {
        electricLine.setInfo(data);
    }

    private void initView() {
        electricLine = (DrawLine) findViewById(R.id.electricity_line);
    }
}
