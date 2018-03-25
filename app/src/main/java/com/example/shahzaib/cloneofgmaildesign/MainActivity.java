package com.example.shahzaib.cloneofgmaildesign;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.RecyclerViewAdapterClickListener, SearchToolbar.OnSearchToolbarQueryTextListner {

    List<Message> messages = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ActionMode actionMode;
    ActionModeCallback callback;
    int totalMessagesSize;
    boolean isActionToolbarActivated = false;
    SearchToolbar searchToolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    SwipeRefreshLayout swipeRefreshLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Gmail");
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this,getMessages(),this);
        callback = new ActionModeCallback();
        searchToolbar = new SearchToolbar(this,this,findViewById(R.id.search_layout));
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);




        setupNavigationDrawer();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Fab Button Clicked",Snackbar.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
                // here do your networking stuff


                    // funciton parameter receive milliseconds so we should conver seconds into milliseconds
                    CountDownTimer countDownTimer = new CountDownTimer(2000,2000) {
                        @Override
                        public void onTick(long milliSecondsLeft) {
                            // currenty no need
                        }

                        @Override
                        public void onFinish() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    };
                    countDownTimer.start();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.ic_search:
                searchToolbar.openSearchToolbar();
                break;
        }

        return true;
    }

    List<Message> getMessages()
    {

        Message message1 = new Message();
        message1.setMessageFrom("From 1");
        message1.setMessageSubject("Subject");
        message1.setMessageBody("Body");
        message1.setMessageTime("10:00am");
        messages.add(message1);

        Message message2 = new Message();
        message2.setMessageFrom("From 2");
        message2.setMessageSubject("Subject");
        message2.setMessageBody("Body");
        message2.setMessageTime("10:00am");
        message2.setProfilePicture(R.drawable.profile_image);
        messages.add(message2);

        Message message3 = new Message();
        message3.setMessageFrom("From 3");
        message3.setMessageSubject("Subject");
        message3.setMessageBody("Body");
        message3.setMessageTime("10:00am");
        message3.setProfilePicture(R.drawable.profile_image);
        messages.add(message3);

        Message message4 = new Message();
        message4.setMessageFrom("From 4");
        message4.setMessageSubject("Subject");
        message4.setMessageBody("Body");
        message4.setMessageTime("10:00am");
        message4.setProfilePicture(R.drawable.profile_image);
        messages.add(message4);

        Message message5 = new Message();
        message5.setMessageFrom("From 5");
        message5.setMessageSubject("Subject");
        message5.setMessageBody("Body");
        message5.setMessageTime("10:00am");
        messages.add(message5);

        Message message6 = new Message();
        message6.setMessageFrom("From 6");
        message6.setMessageSubject("Subject");
        message6.setMessageBody("Body");
        message6.setMessageTime("10:00am");
        message6.setProfilePicture(R.drawable.profile_image);
        messages.add(message6);

        Message message7 = new Message();
        message7.setMessageFrom("From 7");
        message7.setMessageSubject("Subject");
        message7.setMessageBody("Body");
        message7.setMessageTime("10:00am");
        message7.setProfilePicture(R.drawable.profile_image);
        messages.add(message7);

        Message message8 = new Message();
        message8.setMessageFrom("From 8");
        message8.setMessageSubject("Subject");
        message8.setMessageBody("Body");
        message8.setMessageTime("10:00am");
        messages.add(message8);

        Message message9 = new Message();
        message9.setMessageFrom("From 9");
        message9.setMessageSubject("Subject ");
        message9.setMessageBody("Body ");
        message9.setMessageTime("10:00am");
        message9.setProfilePicture(R.drawable.profile_image);
        messages.add(message9);

        Message message10 = new Message();
        message10.setMessageFrom("From 10");
        message10.setMessageSubject("Subject ");
        message10.setMessageBody("Body ");
        message10.setMessageTime("10:00am");
        message10.setProfilePicture(R.drawable.profile_image);
        messages.add(message10);

        Message message11 = new Message();
        message11.setMessageFrom("From 11");
        message11.setMessageSubject("Subject ");
        message11.setMessageBody("Body ");
        message11.setMessageTime("10:00am");
        message11.setProfilePicture(R.drawable.profile_image);
        messages.add(message11);

        Message message12 = new Message();
        message12.setMessageFrom("From 12");
        message12.setMessageSubject("Subject 1");
        message12.setMessageBody("Body 1");
        message12.setMessageTime("10:00am");
        messages.add(message12);

        Message message13 = new Message();
        message13.setMessageFrom("From 13");
        message13.setMessageSubject("Subject 1");
        message13.setMessageBody("Body 1");
        message13.setMessageTime("10:00am");
        message13.setProfilePicture(R.drawable.profile_image);
        messages.add(message13);

        Message message14 = new Message();
        message14.setMessageFrom("From 14");
        message14.setMessageSubject("Subject 1");
        message14.setMessageBody("Body 1");
        message14.setMessageTime("10:00am");
        messages.add(message14);

        Message message15 = new Message();
        message15.setMessageFrom("From 15");
        message15.setMessageSubject("Subject 1");
        message15.setMessageBody("Body 1");
        message15.setMessageTime("10:00am");
        message15.setProfilePicture(R.drawable.profile_image);
        messages.add(message15);

        Message message16 = new Message();
        message16.setMessageFrom("From 16");
        message16.setMessageSubject("Subject 1");
        message16.setMessageBody("Body 1");
        message16.setMessageTime("10:00am");
        messages.add(message16);

        Message message17 = new Message();
        message17.setMessageFrom("From 17");
        message17.setMessageSubject("Subject 1");
        message17.setMessageBody("Body 1");
        message17.setMessageTime("10:00am");
        message17.setProfilePicture(R.drawable.profile_image);
        messages.add(message17);

        Message message18 = new Message();
        message18.setMessageFrom("From 18");
        message18.setMessageSubject("Subject 1");
        message18.setMessageBody("Body 1");
        message18.setMessageTime("10:00am");
        messages.add(message18);

        Message message19 = new Message();
        message19.setMessageFrom("From 19");
        message19.setMessageSubject("Subject 1");
        message19.setMessageBody("Body 1");
        message19.setMessageTime("10:00am");
        message19.setProfilePicture(R.drawable.profile_image);
        messages.add(message19);

        Message message20 = new Message();
        message20.setMessageFrom("From 20");
        message20.setMessageSubject("Subject 1");
        message20.setMessageBody("Body 1");
        message20.setMessageTime("10:00am");
        message20.setProfilePicture(R.drawable.profile_image);
        messages.add(message20);


        totalMessagesSize = messages.size();
        return messages;
    }



     /*jb message pr click ho to us message k readStatus ko true kr do*/
    @Override
    public void onMessageClick(int position) {
        messages.get(position).setRead(true);
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onMessageItemSelected(int position, int selectedItemsCount) {
        messages.get(position).setMessageSelected(true);
        actionModeToolbarUpdate(selectedItemsCount);
    }

    @Override
    public void onMessageItemDeSelected(int position, int selectedItemsCount) {
        messages.get(position).setMessageSelected(false);
        actionModeToolbarUpdate(selectedItemsCount);
    }

    @Override
    public void onMessageStarIconClicked(int position) {
        if(messages.get(position).isStared())
        {
            messages.get(position).setStared(false);
        }
        else
        {
            messages.get(position).setStared(true);
        }
        adapter.notifyDataSetChanged();
    }


    void startActionModeToolbar()
    {
        actionMode =  startSupportActionMode(callback);
    }

    void actionModeToolbarUpdate(int count)
    {

        if(!isActionToolbarActivated)
        {
            startActionModeToolbar();
            isActionToolbarActivated = true;
        }


        if(count <= 0)
        {
                actionMode.finish();
                isActionToolbarActivated = false;
        }
        else
        {
            actionMode.setTitle(""+count);
        }
    }





    /************* Search Toolbar Methods */
    @Override
    public void onQueryTextSubmit(String query) {
        Toast.makeText(this, "Query: "+query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {

    }









    private class ActionModeCallback implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_mode_toolbar_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            Toast.makeText(MainActivity.this, ""+item.getTitle(), Toast.LENGTH_SHORT).show();

            switch (item.getItemId())
            {
                case R.id.ic_delete:
                    // delete all the selected messages
                    List<Message> tempMessages = new ArrayList<>();
                    for(int i=0; i<totalMessagesSize; i++)
                    {
                        if (!messages.get(i).isMessageSelected())
                        {
                            tempMessages.add(messages.get(i));
                        }
                    }
                    messages = tempMessages;
                    totalMessagesSize = messages.size();
                    adapter.updataMessages(messages);
                    adapter.resetList();
                    actionMode.finish();
                    break;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            actionMode = null;
            isActionToolbarActivated = false;
            deSelectAllMessages();

        }
    }

    private void deSelectAllMessages() {
        SparseBooleanArray items = new SparseBooleanArray(); // for keeping the index of the items , jin ko ak sath animate krna hy


        for(int i=0; i<messages.size(); i++)
        {
            if(messages.get(i).isMessageSelected())
            {
                items.put(i,true);
            }

            messages.get(i).setMessageSelected(false);
        }
        adapter.deselectAllselectedItems();
        adapter.notifyDataSetChanged();
        adapter.setItemsForAnimationAtOnce(items);
    }





    private void setupNavigationDrawer()
    {
        ActionBarDrawerToggle toggle
                = new ActionBarDrawerToggle(this, drawerLayout,(Toolbar)findViewById(R.id.toolbar),R.string.drawer_open,R.string.drawer_close);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                closeDrawer();
                Toast.makeText(MainActivity.this, ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        toggle.syncState();
    }
    private void closeDrawer()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private boolean isNavigationDrawerOpen()
    {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }


    // jb back button press ho to drawer agr open hy to close ho jaey
    @Override
    public void onBackPressed() {

        if(isNavigationDrawerOpen())
        {
            closeDrawer();
        }
        else
        {
            super.onBackPressed();
        }

    }
}
