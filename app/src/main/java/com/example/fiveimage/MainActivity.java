package com.example.fiveimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Interposition{

    private static adapter adapter1;
    private RecyclerView.LayoutManager layoutManager;
    private static final int SELECT_PICTURE = 100;
    private Toolbar mTopToolbar;
    ArrayList<String> listdata2;

    private Uri uri=null;
    ImageView imageView, imageView2;
    private Context context;
    private Activity activity;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            chooseImage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);


        imageView = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        listdata2 = new ArrayList<>();
        activity = MainActivity.this;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclelyt);
        adapter1 = new adapter(listdata2, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter1);

        context = this;
        activity = MainActivity.this;


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               int position=(viewHolder.getAdapterPosition());
              listdata2.remove(position);
              adapter1.notifyDataSetChanged();

            }
        }).attachToRecyclerView(recyclerView);



    }


    private void chooseImage() {

        final CharSequence[] options = { "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);


                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }


        });
        builder.show();


    }


    @Override
    public void getposition(int position1) {

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_PICTURE) {
                    uri = data.getData();
                    String uri_to_string = uri.toString();
                    //   File finalFile = new File(getRealPathFromURI(uri));
                    if (null != uri) {

                        //  imageView.setImageURI(uri);

                        System.out.println("CHECKIMAGE_a " + uri_to_string);
                        listdata2.add("" + uri);
                        adapter1.notifyDataSetChanged();


                    }

                }}}}





}
