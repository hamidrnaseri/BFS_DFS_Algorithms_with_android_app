package com.example.artificial_intelligence_dfs_bfs;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import de.blox.graphview.*;
import io.ghyeok.stickyswitch.widget.StickySwitch;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

//    kh garepasha

    private Button button_attach;
    private File file;
    private String[][] dataset = new String[28980][2];
    private Graph graph;
    private GraphView graphView;
    private Node currentNode;
    protected BaseGraphAdapter<ViewHolder> adapter;
    private Button button_click;
    private int[][] dataset_ = new int[28980][2];
    private boolean file_is_exist = false;
    private EditText EditText_first_node;
    private EditText EditText_end_node;
    private int[] result_bfs;
    private TableLayout DetailsTableLayout;
    private CardView CardView_tablelayout;
    private StickySwitch StickySwitch_bfs_dfs_switch;
    private String string_switch_bfs_dfs="BFS";
    private int[] result_dfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        button_attach = (Button) findViewById(R.id.button_activity_main_attach);
        button_click = (Button) findViewById(R.id.button_activity_main_click);
        EditText_first_node = (EditText) findViewById(R.id.EditText_first_node);
        EditText_end_node = (EditText) findViewById(R.id.EditText_end_node);
        StickySwitch_bfs_dfs_switch=(StickySwitch)findViewById(R.id.StickySwitch_bfs_dfs_switch);

        DetailsTableLayout = (TableLayout) findViewById(R.id.details_table_layoutt);
        CardView_tablelayout = (CardView) findViewById(R.id.all_details_card);

        graphView = findViewById(R.id.graph);
//        graph = SugiyamaActivity.createGraph();
//        setupFab(graph);
//        setupAdapter(graph);

        // you can set the graph via the constructor or use the adapter.setGraph(Graph) method

        button_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setCheckPermission(true)
                        .setShowFiles(true)
                        .setShowImages(false)
                        .setShowVideos(false)
                        .setSingleClickSelection(true)
                        .setRootPath(Environment.getExternalStorageDirectory().getPath() + "/Download")
                        .build());

                File sdcard = Environment.getExternalStorageDirectory();
                file = new File(sdcard, "dataset.txt");

                startActivityForResult(intent, 1);


            }
        });
        button_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditText_first_node.getText().toString().trim().equals("") && EditText_end_node.getText().toString().trim().equals("")) {
                    Toast.makeText(Main2Activity.this, "enter First Node & End Node", Toast.LENGTH_SHORT).show();
                } else if (EditText_first_node.getText().toString().trim().equals("")) {
                    Toast.makeText(Main2Activity.this, "enter First Node", Toast.LENGTH_SHORT).show();
                } else if (EditText_end_node.getText().toString().trim().equals("")) {
                    Toast.makeText(Main2Activity.this, "enter End Node", Toast.LENGTH_SHORT).show();
                } else if (file_is_exist) {
                    Find_BFS_DFS();
                } else {
                    Toast.makeText(Main2Activity.this, "Enter DataSet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        StickySwitch_bfs_dfs_switch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Toast.makeText(Main2Activity.this, text, Toast.LENGTH_SHORT).show();


                if (Objects.equals(text, "BFS")){

                    string_switch_bfs_dfs="BFS";

                }else if (Objects.equals(text, "DFS")){
                    string_switch_bfs_dfs="DFS";

                }
            }
        });

    }

    private void Find_BFS_DFS() {


        if (string_switch_bfs_dfs.equals("BFS")){
            BFS_class bfs_class = new BFS_class(Integer.valueOf(EditText_first_node.getText().toString().trim()), Integer.valueOf(EditText_end_node.getText().toString().trim()));

            CardView_tablelayout.setVisibility(View.VISIBLE);
            result_bfs = bfs_class.main(dataset_);
            for (int i = 0; i < result_bfs.length; i++) {
                if (result_bfs[i] != 0) {
                    get_data_for_set_table(String.valueOf(result_bfs[i]));
                }
            }
        }else if (string_switch_bfs_dfs.equals("DFS")){
            DFS_class bfs_class = new DFS_class(Integer.valueOf(EditText_first_node.getText().toString().trim()), Integer.valueOf(EditText_end_node.getText().toString().trim()));

            CardView_tablelayout.setVisibility(View.VISIBLE);
            result_dfs = bfs_class.main(dataset_);
            for (int i = 0; i < result_dfs.length; i++) {
                if (result_dfs[i] != 0) {
                    get_data_for_set_table(String.valueOf(result_dfs[i]));
                    Log.i("nj", String.valueOf(i)+" : "+ String.valueOf(result_dfs[i]));
                }
            }
        }



        Toast.makeText(this, "please wait ...", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {


            case 1:


                StringBuilder text = new StringBuilder();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                } catch (IOException e) {
                }


//                get dataset and set it on the array and converting values from string format to integer format

                int dataset_i = 0;
                int dataset_j = 0;

                for (int i = 0; i < text.length(); i++) {

                    if (text.charAt(i) == '\t') {
                        dataset_j++;
                    } else if (text.charAt(i) == '\n') {
                        dataset_i++;
                        dataset_j = 0;
                    } else {
                        if (dataset[dataset_i][dataset_j] == null)
                            dataset[dataset_i][dataset_j] = "";
                        dataset[dataset_i][dataset_j] += String.valueOf(text.charAt(i));
                    }
                }

                for (int i = 0; i < dataset.length; i++) {
                    for (int j = 0; j < 2; j++) {
                        dataset_[i][j] = Integer.valueOf(dataset[i][j]);
                    }
                }


                file_is_exist = true;

//                for (int e=0;e<28900;e++) {
//                    graph.addEdge(new Node(dataset[e][0]), new Node(dataset[e][1]));
//                }

                break;

        }
    }


    public void get_data_for_set_table(String value) {


        TextView textView_bfs_dfs_value = new TextView(Main2Activity.this);
        textView_bfs_dfs_value.setText(value);

        AddDetailsToTableRow(textView_bfs_dfs_value);


    }

    private void AddDetailsToTableRow(TextView textView_bfs_dfs_value) {


        TableRow tableRow = new TableRow(Main2Activity.this);
        tableRow.setPadding(15, 15, 15, 15);
//        tableRow.setBackgroundColor(Color);


        textView_bfs_dfs_value.setPadding(20, 10, 10, 10);


        textView_bfs_dfs_value.setGravity(Gravity.CENTER);

        tableRow.addView(textView_bfs_dfs_value);

        DetailsTableLayout.addView(tableRow);


    }


    private void setupAdapter(Graph graph) {
        final GraphView graphView = findViewById(R.id.graph);

        adapter = new BaseGraphAdapter<ViewHolder>(graph) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                ((SimpleViewHolder) viewHolder).textView.setText(data.toString());
            }

            class SimpleViewHolder extends ViewHolder {
                TextView textView;

                SimpleViewHolder(View itemView) {
                    super(itemView);
                    textView = itemView.findViewById(R.id.textView);
                }
            }
        };

        SugiyamaActivity.setAlgorithm(adapter);

        graphView.setAdapter(adapter);
        graphView.setZoomEnabled(true);
        graphView.setOnItemClickListener((parent, view, position, id) -> {
            currentNode = adapter.getNode(position);
//            Snackbar.make(graphView, "Clicked on " + currentNode.getData().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private void setupFab(final Graph graph) {
//        FloatingActionButton addButton = findViewById(R.id.addNode);
//        addButton.setOnClickListener(v -> {
//            if (currentNode != null) {
//                final Node newNode = new Node(getNodeText());
//                graph.addEdge(currentNode, newNode);
//            }
//        });
//
//        addButton.setOnLongClickListener(v -> {
//            if (currentNode != null) {
//                graph.removeNode(currentNode);
//                currentNode = null;
//            }
//            return true;
//        });
    }

}



