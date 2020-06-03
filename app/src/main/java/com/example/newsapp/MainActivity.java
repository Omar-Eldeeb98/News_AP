package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<New> newArrayList = new ArrayList<>();
    private RequestQueue requestQueue;
    private EditText searchEditText;
    private Button goSearchButton;

    private SwipeRefreshLayout swipeRefreshLayout;  //_____________________________________________________



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.action_bar_custom );

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById( R.id.swipe_layout ); //__________________________________________
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
            @Override
            public void onRefresh() {

                parseJson("publishedAt",""); // _______________ second call of the method __________________________
                searchEditText.setText( "" );

            }
        } );



        requestQueue = Volley.newRequestQueue( this );


        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        searchEditText = (EditText) findViewById( R.id.search_edit_text );
        goSearchButton = (Button) findViewById( R.id.confirm_search_button );


        goSearchButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s = searchEditText.getText().toString();
                newArrayList.clear();
                parseJson_2( s );  // _______________ third call of the method __________________________
                myAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                       parseJson_2(s); // _______________ second call of the method __________________________


                    }
                } );

                if (searchEditText.getText().toString().isEmpty())
                {
                    parseJson("","");   // _______________ fourth call of the method __________________________

                }

            }
        } );


        parseJson("" , "");  // _____________ this is the first method That we Called To Retrieve the F_u_c_k_e_n Data __________________________

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //_________________________CATEGORY SEARCH
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_category , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {   //_________________________CATEGORY SEARCH

        switch (item.getItemId()) {
            case R.id.general_item:

                parseJson( "", "general" );
                searchEditText.setText( "" );
                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                        parseJson("publishedAt","general"); // _______________ second call of the method __________________________
                        searchEditText.setText( "" );

                    }
                } );


                break;

            case R.id.technology_item:
                parseJson( "", "technology" );
                searchEditText.setText( "" );
                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                        parseJson("publishedAt","technology"); // _______________ second call of the method __________________________
                        searchEditText.setText( "" );

                    }
                } );

                break;

            case R.id.entertainment_item:
                parseJson( "", "entertainment" );
                searchEditText.setText( "" );
                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                        parseJson("publishedAt","entertainment"); // _______________ second call of the method __________________________
                        searchEditText.setText( "" );

                    }
                } );

                break;

            case R.id.health_item:
                parseJson( "", "health" );
                searchEditText.setText( "" );
                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                        parseJson("publishedAt","health"); // _______________ second call of the method __________________________
                        searchEditText.setText( "" );

                    }
                } );


                break;

            case R.id.sports_item:
                parseJson( "", "sports" );
                searchEditText.setText( "" );
                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                        parseJson("publishedAt","sports"); // _______________ second call of the method __________________________
                        searchEditText.setText( "" );

                    }
                } );

                break;

            case R.id.science_item:
                parseJson( "", "science" );
                searchEditText.setText( "" );
                swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() { //___________________________________
                    @Override
                    public void onRefresh() {

                        parseJson("publishedAt","science"); // _______________ second call of the method __________________________
                        searchEditText.setText( "" );

                    }
                } );

                break;


        }

        return super.onOptionsItemSelected( item );
    }

    private void parseJson(String timePublished  , String category) {

        swipeRefreshLayout.setRefreshing( true );  //________________________________________

       String url = "https://newsapi.org/v2/top-headlines?category="+category+"&country=eg&apiKey=489be6b0924447b39fa962e028b0c75a&pageSize=95&sortBy="+timePublished;
      // String url = "https://newsapi.org/v2/everything?q="+term+"&apiKey=489be6b0924447b39fa962e028b0c75a&language=ar&sortBy=publishedAt";
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray  jsonArray = response.getJSONArray( "articles" );
                    swipeRefreshLayout.setRefreshing( false );  //______________________________________________
                    newArrayList.clear();  //_____________________________________________________________________

                    for (int i =0 ; i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject( i );
                        String image__ = jsonObject.getString( "urlToImage" );
                        String title__ = jsonObject.getString( "title" );
                        String date__ = jsonObject.getString("publishedAt");
                        String description__ = jsonObject.getString( "description" );
                        String webView__ = jsonObject.getString( "url" );

                        JSONObject object = jsonObject.getJSONObject( "source" );
                        String src__ = object.getString( "name" );

                        newArrayList.add( new New( image__,title__,src__,date__ ,description__,webView__ ) );

                    }
                    myAdapter =new MyAdapter( MainActivity.this ,newArrayList );
                    recyclerView.setAdapter( myAdapter );
                    myAdapter.notifyDataSetChanged();
                }
                catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing( false );  //_______________________________________________________________________
                error.printStackTrace();
            }
        } );

        requestQueue.add( request );
    }


   private void parseJson_2(String term) {



       swipeRefreshLayout.setRefreshing( true );

       // String url = "https://newsapi.org/v2/top-headlines?country=eg&apiKey=489be6b0924447b39fa962e028b0c75a&sortBy=publishedAt";
        String url = "https://newsapi.org/v2/everything?q="+""+term+"&apiKey=489be6b0924447b39fa962e028b0c75a&country=&language=ar&pageSize=90&sortBy=publishedAt";
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray  jsonArray = response.getJSONArray( "articles" );
                    swipeRefreshLayout.setRefreshing( false );  //______________________________________________
                    newArrayList.clear();  //_____________________________________________________________________



                    for (int i =0 ; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject( i );
                        String image__ = jsonObject.getString( "urlToImage" );
                        String title__ = jsonObject.getString( "title" );
                        String date__ = jsonObject.getString("publishedAt");
                        String description__ = jsonObject.getString( "description" );
                        String webView__ = jsonObject.getString( "url" );

                        JSONObject object = jsonObject.getJSONObject( "source" );
                        String src__ = object.getString( "name" );



                        newArrayList.add( new New( image__,title__,src__,date__ ,description__,webView__ ) );


                    }


                    myAdapter =new MyAdapter( MainActivity.this ,newArrayList );
                    recyclerView.setAdapter( myAdapter );
                    myAdapter.notifyDataSetChanged();


                }
                catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
            }
        } );

        requestQueue.add( request );
    }



}
